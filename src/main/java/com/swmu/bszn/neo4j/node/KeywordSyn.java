package com.swmu.bszn.neo4j.node;

import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@Setter
@Getter
@NodeEntity(label = "keywordSyn")
public class KeywordSyn {
    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "word")
    private String word;
}
