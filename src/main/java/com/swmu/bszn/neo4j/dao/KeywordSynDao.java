package com.swmu.bszn.neo4j.dao;

import com.swmu.bszn.neo4j.node.KeywordSyn;
import org.springframework.data.neo4j.repository.Neo4jRepository;

/**
 *
 * 关键词同义词
 *
 * @author seven.mu
 * @date 2019/3/12-17:44
 */
public interface KeywordSynDao extends Neo4jRepository<KeywordSyn, Long> {

    KeywordSyn findByWord(String word);
}
