package com.swmu.bszn.neo4j.relation;

import com.swmu.bszn.neo4j.node.Item;
import com.swmu.bszn.neo4j.node.KeyWord;
import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.*;

/**
 * @author seven.mu
 * @Description: 事项关键词关联关系
 * @date 2019/3/11-19:21
 */
@Setter
@Getter
@RelationshipEntity(type = "itemRelationKeyWord")
public class ItemRelationKeyWord {
    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private Item item;

    @EndNode
    private KeyWord keyWord;
}
