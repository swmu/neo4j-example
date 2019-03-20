package com.swmu.bszn.neo4j.node;

import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * @author seven.mu
 * @Description: 事项对应关键词
 * @date 2019/3/11-19:09
 */

@Setter
@Getter
@NodeEntity(label = "keyword")
public class KeyWord {
    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "name")
    private String name;
}
