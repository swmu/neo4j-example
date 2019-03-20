package com.swmu.bszn.neo4j.relation;

import com.swmu.bszn.neo4j.node.Item;
import com.swmu.bszn.neo4j.node.Things;
import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.*;

/**
 * @author seven.mu
 * @Description: 事项名称和事项间关联关系
 * @date 2019/3/11-19:05
 */
@Getter
@Setter
@RelationshipEntity(type = "thingsRelationItem")
public class ThingsRelationItem {
    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private Things things;

    @EndNode
    private Item item;
}
