package com.swmu.bszn.neo4j.relation;

import com.swmu.bszn.neo4j.node.Neo4jArea;
import com.swmu.bszn.neo4j.node.Things;
import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.*;

/**
 * @author seven.mu
 * @Description: 事项名称和区域间的关联关系
 * @date 2019/3/11-19:00
 */
@Setter
@Getter
@RelationshipEntity(type = "thingsRelationArea")
public class ThingsRelationArea {
    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private Things things;

    @EndNode
    private Neo4jArea neo4JArea;
}
