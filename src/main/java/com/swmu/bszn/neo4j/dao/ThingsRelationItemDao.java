package com.swmu.bszn.neo4j.dao;

import com.swmu.bszn.neo4j.relation.ThingsRelationItem;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ThingsRelationItemDao extends Neo4jRepository<ThingsRelationItem, Long> {
}
