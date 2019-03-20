package com.swmu.bszn.neo4j.dao;

import com.swmu.bszn.neo4j.node.KeyWord;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface KeyWordDao extends Neo4jRepository<KeyWord, Long> {
    public KeyWord findByName(String name);
}
