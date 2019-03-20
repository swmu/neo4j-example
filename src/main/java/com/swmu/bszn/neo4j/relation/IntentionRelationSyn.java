package com.swmu.bszn.neo4j.relation;

import com.swmu.bszn.neo4j.node.Intention;
import com.swmu.bszn.neo4j.node.IntentionSyn;
import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.*;

/**
 *
 * 意图和其同义词关联关系
 *
 * @author seven.mu
 * @date 2019/3/12-17:47
 */
@Setter
@Getter
@RelationshipEntity(type = "intentionRelationSyn")
public class IntentionRelationSyn {
    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private Intention intention;

    @EndNode
    private IntentionSyn intentionSyn;
}
