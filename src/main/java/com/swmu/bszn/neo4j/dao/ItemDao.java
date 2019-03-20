package com.swmu.bszn.neo4j.dao;

import com.swmu.bszn.neo4j.node.Item;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ItemDao extends Neo4jRepository<Item, Long> {
    public Item findByName(String name);
}
