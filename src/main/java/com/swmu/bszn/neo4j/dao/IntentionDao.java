package com.swmu.bszn.neo4j.dao;

import com.swmu.bszn.neo4j.node.Intention;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface IntentionDao extends Neo4jRepository<Intention, Long> {
    public Intention findByName(String name);

}
