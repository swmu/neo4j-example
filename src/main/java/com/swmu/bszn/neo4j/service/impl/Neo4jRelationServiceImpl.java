package com.swmu.bszn.neo4j.service.impl;

import com.swmu.bszn.neo4j.dao.*;
import com.swmu.bszn.neo4j.node.*;
import com.swmu.bszn.neo4j.relation.*;
import com.swmu.bszn.neo4j.service.Neo4jRelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class Neo4jRelationServiceImpl implements Neo4jRelationService {

    @Autowired
    private ItemRelationKeyWordDao itemRelationKeyWordDao;

    @Autowired
    private ThingsRelationAreaDao thingsRelationAreaDao;

    @Autowired
    private ThingsRelationItemDao thingsRelationItemDao;

    @Autowired
    private ThingsRelationIntentionDao thingsRelationIntentionDao;

    @Autowired
    private IntentionRelationSynDao intentionRelationSynDao;

    @Autowired
    private KeywordRelationSynDao keywordRelationSynDao;

    @Autowired
    private AreaRelationDao areaRelationDao;

    @Override
    public boolean addItemRelationKeyword(Item item, List<KeyWord> keyWords) {
        try {
            for(KeyWord keyWord:keyWords){
                ItemRelationKeyWord itemRelationKeyWord = new ItemRelationKeyWord();
                itemRelationKeyWord.setItem(item);
                itemRelationKeyWord.setKeyWord(keyWord);
                ItemRelationKeyWord save = itemRelationKeyWordDao.save(itemRelationKeyWord);
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public ThingsRelationArea addThingsRelationArea(Things things, Neo4jArea neo4JArea) {
        ThingsRelationArea thingsRelationArea = new ThingsRelationArea();
        thingsRelationArea.setThings(things);
        thingsRelationArea.setNeo4JArea(neo4JArea);
        ThingsRelationArea save = thingsRelationAreaDao.save(thingsRelationArea);
        return save;
    }

    @Override
    public ThingsRelationItem addThingsRelationItem(Things things, Item item) {
        ThingsRelationItem thingsRelationItem = new ThingsRelationItem();
        thingsRelationItem.setThings(things);
        thingsRelationItem.setItem(item);
        ThingsRelationItem save = thingsRelationItemDao.save(thingsRelationItem);
        return save;
    }

    @Override
    public boolean addThingsRelationIntention(Things things, List<Intention> intentions) {
        try {
            for(Intention intention:intentions){
                ThingsRelationIntention thingsRelationIntention = new ThingsRelationIntention();
                thingsRelationIntention.setThings(things);
                thingsRelationIntention.setIntention(intention);
                ThingsRelationIntention save = thingsRelationIntentionDao.save(thingsRelationIntention);
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean addIntentionRelationSyn(Intention intention, List<IntentionSyn> intentionSyns) {
        try{
            for(IntentionSyn intentionSyn:intentionSyns){
                IntentionRelationSyn intentionRelationSyn = new IntentionRelationSyn();
                intentionRelationSyn.setIntention(intention);
                intentionRelationSyn.setIntentionSyn(intentionSyn);
                IntentionRelationSyn save = intentionRelationSynDao.save(intentionRelationSyn);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            log.error("添加意图同义词关联关系错误",e);
            return false;
        }
    }

    @Override
    public boolean addKeywordRelationSyn(KeyWord keyWord, List<KeywordSyn> keywordSyns) {
        try{
            for(KeywordSyn keywordSyn:keywordSyns){
                KeywordRelationSyn keywordRelationSyn = new KeywordRelationSyn();
                keywordRelationSyn.setKeyWord(keyWord);
                keywordRelationSyn.setKeywordSyn(keywordSyn);
                KeywordRelationSyn save = keywordRelationSynDao.save(keywordRelationSyn);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            log.error("添加关键词同义词关联关系错误",e);
            return false;
        }
    }

    @Override
    public boolean addAreaRealtion(Neo4jArea neo4jAreaStart, Neo4jArea neo4jAreaEnd) {
        try{
            AreaRelation areaRelation = new AreaRelation();
            areaRelation.setNeo4jAreaStart(neo4jAreaStart);
            areaRelation.setNeo4jAreaEnd((neo4jAreaEnd));
            AreaRelation save = areaRelationDao.save(areaRelation);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
