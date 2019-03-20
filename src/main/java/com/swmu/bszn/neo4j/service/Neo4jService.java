package com.swmu.bszn.neo4j.service;

import com.swmu.bszn.neo4j.node.*;

/**
 * 办事指南neo4j操作服务层接口
 * @author seven.mu
 * @date 2019/3/12-10:03
 */
public interface Neo4jService {
    /**
     * 新增意图
     * @param name
     * @return
     */
    Intention addIntention(String name);

    Neo4jArea addArea(String name, String code);

    Item addItem(String name);

    Things addThings(String title, String url);

    KeyWord addKeyWord(String name);

    IntentionSyn addIntentionSyn(String word);

    KeywordSyn addKeywordSyn(String word);

}
