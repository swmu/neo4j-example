package com.swmu.bszn.neo4j.service;

import com.swmu.bszn.neo4j.node.*;
import com.swmu.bszn.neo4j.relation.ThingsRelationArea;
import com.swmu.bszn.neo4j.relation.ThingsRelationItem;

import java.util.List;

/**
 * 办事指南关联关系的服务层接口
 * @descripede
 * @author seven.mu
 * @date 2019/3/12-10:35
 */
public interface Neo4jRelationService {

    boolean addItemRelationKeyword(Item item, List<KeyWord> keyWords);

    ThingsRelationArea addThingsRelationArea(Things things, Neo4jArea neo4JArea);

    ThingsRelationItem addThingsRelationItem(Things things, Item item);

    boolean addThingsRelationIntention(Things things, List<Intention> intentions);

    boolean addIntentionRelationSyn(Intention intention, List<IntentionSyn> intentionSyns);

    boolean addKeywordRelationSyn(KeyWord keyWord, List<KeywordSyn> keywordSyns);

    boolean addAreaRealtion(Neo4jArea neo4jAreaStart, Neo4jArea neo4jAreaEnd);
}
