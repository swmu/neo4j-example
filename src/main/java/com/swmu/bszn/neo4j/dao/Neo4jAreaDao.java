package com.swmu.bszn.neo4j.dao;

import com.swmu.bszn.neo4j.node.Neo4jArea;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface Neo4jAreaDao extends Neo4jRepository<Neo4jArea, Long> {
    Neo4jArea findByCode(String code);

}
