package com.swmu.bszn.neo4j.dao;

import com.swmu.bszn.neo4j.node.IntentionSyn;
import org.springframework.data.neo4j.repository.Neo4jRepository;

/**
 *
 * 意图同义词
 *
 * @author seven.mu
 * @date 2019/3/12-17:42
 */
public interface IntentionSynDao extends Neo4jRepository<IntentionSyn, Long> {
    public IntentionSyn findByWord(String word);
}
