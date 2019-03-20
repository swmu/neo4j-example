package com.swmu.bszn.neo4j.dao;

import com.swmu.bszn.neo4j.relation.AreaRelation;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface AreaRelationDao extends Neo4jRepository<AreaRelation, Long> {
}
