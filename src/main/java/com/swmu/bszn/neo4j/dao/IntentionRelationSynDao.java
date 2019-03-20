package com.swmu.bszn.neo4j.dao;

import com.swmu.bszn.neo4j.relation.IntentionRelationSyn;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface IntentionRelationSynDao extends Neo4jRepository<IntentionRelationSyn, Long> {
}
