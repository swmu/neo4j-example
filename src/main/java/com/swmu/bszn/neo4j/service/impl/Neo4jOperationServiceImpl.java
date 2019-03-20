package com.swmu.bszn.neo4j.service.impl;

import com.swmu.bszn.guide.entity.*;
import com.swmu.bszn.guide.service.*;
import com.swmu.bszn.neo4j.node.*;
import com.swmu.bszn.neo4j.service.Neo4jOperationService;
import com.swmu.bszn.neo4j.service.Neo4jRelationService;
import com.swmu.bszn.neo4j.service.Neo4jService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 *
 * 操作服务实现层
 *
 * @author seven.mu
 * @date 2019/3/12-14:52
 */
@Service
public class Neo4jOperationServiceImpl implements Neo4jOperationService {
    @Autowired
    private Neo4jService neo4jService;

    @Autowired
    private Neo4jRelationService neo4jRelationService;

    @Autowired
    private GuideService guideService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private GuideThingsService guideThingsService;

    @Autowired
    private LocalIntentionService localIntentionService;

    @Autowired
    private LocalKeywordService localKeywordService;

    @Override
    public boolean buildGuideGraph(){
        List<GuideThings> guideThings = guideThingsService.queryAll();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(16, 16, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
        for(GuideThings gt : guideThings){
            BuildGuideThread buildGuideThread = new BuildGuideThread(gt);
            threadPoolExecutor.execute(buildGuideThread);
        }
        return true;
    }

    private class BuildGuideThread extends Thread {
        private GuideThings gt;
        public BuildGuideThread(GuideThings guideThings){
            this.gt = guideThings;
        }

        @Override
        public void run(){
            // 2. 根据数据创建item, keyword, intention
            String itemName = gt.getItem();
            Item item = neo4jService.addItem(itemName);

            String keywordNamesStr = gt.getKeyword();
            List<KeyWord> keyWordList = new ArrayList<>();
            if(!StringUtils.isEmpty(keywordNamesStr)){
                String[] keywordNames = keywordNamesStr.split("[,，]");
                for(String keywordName:keywordNames){
                    KeyWord keyWord = neo4jService.addKeyWord(keywordName);
                    keyWordList.add(keyWord);
                }
            }

            String intentionsStr = gt.getIntention();
            List<Intention> intentionList = new ArrayList<>();
            if(!StringUtils.isEmpty(intentionsStr)){
                String[] intentions = intentionsStr.split("[,，]");
                for(String intentionName:intentions){
                    Intention intention = neo4jService.addIntention(intentionName);
                    intentionList.add(intention);
                }
            }

            // 3. 保存item 和 keyword 之间关系
            boolean b = neo4jRelationService.addItemRelationKeyword(item, keyWordList);
            // 4. 根据读取的事项名称查询数据库中对应事项列表
            String thingsName = gt.getThings();
            List<Guide> guides = guideService.queryGuideByName(thingsName);
            for(Guide guide:guides){
                // 5. 创建事项名称节点things
                String title = guide.getTitle();
                String url = guide.getUrl();
                int areaId = guide.getAreaId();
                Things things = neo4jService.addThings(title, url);
                // 6. 根据每个things查询对应的area
                Area area = areaService.queryAreaByAreaId(areaId);
                // 7. 创建区域节点
                Neo4jArea neo4JArea = neo4jService.addArea(area.getName(), area.getAreaId()+"");
                // 8. 保存 things 与 item, intention, area间的关系
                neo4jRelationService.addThingsRelationItem(things, item);
                neo4jRelationService.addThingsRelationIntention(things, intentionList);
                neo4jRelationService.addThingsRelationArea(things, neo4JArea);
            }
        }
    }
    @Override
    public boolean buildIntentionSynonymGraph(){
        // 1. 获取意图及对应同义词列表
        List<LocalIntention> localIntentions = localIntentionService.queryAllIntention();
        for(LocalIntention localIntention:localIntentions){
            String intentionName = localIntention.getIntention();
            String synonymsStr = localIntention.getSynonyms();
            // 2. 同义词切分
            String[] synonyms = synonymsStr.split(",");
            // 3. 创建意图节点
            Intention intention = neo4jService.addIntention(intentionName);
            // 4. 创建同义词节点
            List<IntentionSyn> IntentionSyns = new ArrayList<>();
            for(String word:synonyms){
                IntentionSyn intentionSyn = neo4jService.addIntentionSyn(word);
                IntentionSyns.add(intentionSyn);
            }
            // 5. 构建意图和同义词间的关联关系
            neo4jRelationService.addIntentionRelationSyn(intention, IntentionSyns);

        }
        return true;
    }

    @Override
    public boolean buildKeywordSynonymGraph(){
        // 1. 获取关键词及对应同义词列表
        List<LocalKeyword> localKeywords = localKeywordService.queryAllKeyword();
        for(LocalKeyword keyworSyn:localKeywords){
            String keywordName = keyworSyn.getKeyword();
            String synonymsStr = keyworSyn.getSynonyms();
            // 2. 同义词切分
            String[] synonyms = synonymsStr.split(",");
            // 3. 创建关键词节点
            KeyWord keyWord = neo4jService.addKeyWord(keywordName);
            // 4. 创建同义词节点
            List<KeywordSyn> keywordSyns = new ArrayList<>();
            for(String word:synonyms){
                KeywordSyn keywordSyn = neo4jService.addKeywordSyn(word);
                keywordSyns.add(keywordSyn);
            }
            // 5. 构建关键词和同义词间的关联关系
            neo4jRelationService.addKeywordRelationSyn(keyWord, keywordSyns);
        }

        return true;
    }

    @Override
    public boolean buildAreaGraph(){
        List<Area> areas = areaService.queryAllArea();
        for(Area area : areas){
            int parendId = area.getParentId();
            String name = area.getName();
            int code = area.getAreaId();
            Neo4jArea neo4jAreaEnd = neo4jService.addArea(name, code+"");
            if(parendId != 0){
                Area startArea = areaService.queryAreaById(parendId);
                Neo4jArea neo4jAreaStart = neo4jService.addArea(startArea.getName(), startArea.getAreaId()+"");
                boolean b = neo4jRelationService.addAreaRealtion(neo4jAreaStart, neo4jAreaEnd);
            }
        }
        return true;
    }
}
