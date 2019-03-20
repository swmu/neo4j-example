package com.swmu.bszn.neo4j.relation;

import com.swmu.bszn.neo4j.node.Neo4jArea;
import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.*;

@Setter
@Getter
@RelationshipEntity(value = "areaRelation")
public class AreaRelation {
    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private Neo4jArea neo4jAreaStart;

    @EndNode
    private Neo4jArea neo4jAreaEnd;
}
