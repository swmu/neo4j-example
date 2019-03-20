package com.swmu.bszn.neo4j.node;

import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * 指南事项完整名称
 * @author seven.mu
 * @date 2019/3/12-10:05
 */
@Setter
@Getter
@NodeEntity(label = "things")
public class Things {
    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "title")
    private String title;

    @Property(name = "url")
    private String url;
}
