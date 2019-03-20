package com.swmu.bszn.neo4j.service;

/**
 * 办事指南服务层操作接口
 * @descripede
 * @author seven.mu
 * @date 2019/3/12-10:38
 */
public interface Neo4jOperationService {
    /**
     * 构建主要结构知识图谱
     * 含 事项名称及属性， 事项， 关键字， 意图， 区域
     * @return
     */
    boolean buildGuideGraph();

    /**
     * 构建意图对应的同义词图谱
     * @return
     */
    boolean buildIntentionSynonymGraph();

    /**
     * 构建意图对应的同义词图谱
     * @return
     */
    boolean buildKeywordSynonymGraph();

    /**
     * 构建地址的图谱
     * @return
     */
    boolean buildAreaGraph();
}
