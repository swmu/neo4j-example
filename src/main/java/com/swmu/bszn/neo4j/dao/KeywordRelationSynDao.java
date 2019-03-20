package com.swmu.bszn.neo4j.dao;

import com.swmu.bszn.neo4j.relation.KeywordRelationSyn;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface KeywordRelationSynDao extends Neo4jRepository<KeywordRelationSyn, Long> {
}
