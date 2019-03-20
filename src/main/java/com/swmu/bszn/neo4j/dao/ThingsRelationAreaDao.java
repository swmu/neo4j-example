package com.swmu.bszn.neo4j.dao;

import com.swmu.bszn.neo4j.relation.ThingsRelationArea;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ThingsRelationAreaDao extends Neo4jRepository<ThingsRelationArea, Long> {
}
