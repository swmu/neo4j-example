package com.swmu.bszn.neo4j.dao;

import com.swmu.bszn.neo4j.relation.ItemRelationKeyWord;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ItemRelationKeyWordDao extends Neo4jRepository<ItemRelationKeyWord, Long> {
}
