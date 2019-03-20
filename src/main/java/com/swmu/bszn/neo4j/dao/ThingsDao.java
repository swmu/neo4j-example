package com.swmu.bszn.neo4j.dao;

import com.swmu.bszn.neo4j.node.Things;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ThingsDao extends Neo4jRepository<Things, Long> {
    public Things findByUrl(String url);
}
