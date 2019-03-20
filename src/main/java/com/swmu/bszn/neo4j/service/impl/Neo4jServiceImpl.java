package com.swmu.bszn.neo4j.service.impl;

import com.swmu.bszn.neo4j.dao.*;
import com.swmu.bszn.neo4j.node.*;
import com.swmu.bszn.neo4j.service.Neo4jService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 办事指南neo4j操作服务类
 * @author seven.mu
 * @date 2019/3/12-10:00
 */
@Service
public class Neo4jServiceImpl implements Neo4jService {
    @Autowired
    private IntentionDao intentionDao;

    @Autowired
    private Neo4jAreaDao neo4jAreaDao;

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private ThingsDao thingsDao;

    @Autowired
    private KeyWordDao keyWordDao;

    @Autowired
    private IntentionSynDao intentionSynDao;

    @Autowired
    private KeywordSynDao keywordSynDao;

    @Override
    public Intention addIntention(String name) {
        Intention byName = intentionDao.findByName(name);
        if(byName == null){
            Intention intention = new Intention();
            intention.setName(name);
            byName = intentionDao.save(intention);
        }
        return byName;
    }

    @Override
    public Neo4jArea addArea(String name, String code) {
        Neo4jArea byName = neo4jAreaDao.findByCode(code);
        if(byName==null){
            Neo4jArea neo4JArea = new Neo4jArea();
            neo4JArea.setName(name);
            neo4JArea.setCode(code);
            byName = neo4jAreaDao.save(neo4JArea);

        }
        return byName;
    }

    @Override
    public Item addItem(String name) {
        Item byName = itemDao.findByName(name);
        if(byName==null){
            Item item = new Item();
            item.setName(name);
            byName = itemDao.save(item);
        }
        return byName;
    }

    @Override
    public Things addThings(String title, String url) {
        Things byUrl = thingsDao.findByUrl(url);
        if(byUrl == null){
            Things things = new Things();
            things.setTitle(title);
            things.setUrl(url);
            byUrl = thingsDao.save(things);
        }
        return byUrl;
    }

    @Override
    public KeyWord addKeyWord(String name) {
        KeyWord byName = keyWordDao.findByName(name);
        if(byName==null){
            KeyWord keyWord = new KeyWord();
            keyWord.setName(name);
            byName = keyWordDao.save(keyWord);

        }
        return byName;
    }

    @Override
    public IntentionSyn addIntentionSyn(String word) {
        IntentionSyn intentionSyn =intentionSynDao.findByWord(word);
        if(intentionSyn == null){
            IntentionSyn intentionSyn1 = new IntentionSyn();
            intentionSyn1.setWord(word);
            intentionSyn = intentionSynDao.save(intentionSyn1);
        }
        return intentionSyn;
    }

    @Override
    public KeywordSyn addKeywordSyn(String word) {
        KeywordSyn keywordSyn = keywordSynDao.findByWord(word);
        if(keywordSyn == null){
            KeywordSyn keywordSyn1 = new KeywordSyn();
            keywordSyn1.setWord(word);
            keywordSyn = keywordSynDao.save(keywordSyn1);
        }
        return keywordSyn;
    }
}
