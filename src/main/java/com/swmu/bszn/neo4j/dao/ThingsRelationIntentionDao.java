package com.swmu.bszn.neo4j.dao;

import com.swmu.bszn.neo4j.relation.ThingsRelationIntention;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ThingsRelationIntentionDao extends Neo4jRepository<ThingsRelationIntention, Long> {
}
