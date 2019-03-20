package com.swmu.bszn.neo4j.relation;

import com.swmu.bszn.neo4j.node.Intention;
import com.swmu.bszn.neo4j.node.Things;
import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.*;

/**
 * @author seven.mu
 * @Description: 事项名称和意图间关联关系
 * @date 2019/3/11-19:03
 */
@Setter
@Getter
@RelationshipEntity(type = "thingsRelationIntention")
public class ThingsRelationIntention {
    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private Things things;

    @EndNode
    private Intention intention;
}
