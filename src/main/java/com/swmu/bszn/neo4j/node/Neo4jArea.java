package com.swmu.bszn.neo4j.node;

import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * 区域
 * @author seven.mu
 * @date 2019/3/12-10:09
 */
@Setter
@Getter
@NodeEntity(label = "area")
public class Neo4jArea {
    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "name")
    private String name;

    @Property(name = "code")
    private String code;
}
