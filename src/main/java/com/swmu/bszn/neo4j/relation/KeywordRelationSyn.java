package com.swmu.bszn.neo4j.relation;

import com.swmu.bszn.neo4j.node.KeyWord;
import com.swmu.bszn.neo4j.node.KeywordSyn;
import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.*;

/**
 *
 * 关键词和其同义词关系
 *
 * @author seven.mu
 * @date 2019/3/12-17:49
 */
@Setter
@Getter
@RelationshipEntity(type = "keywordRelationSyn")
public class KeywordRelationSyn {
    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private KeyWord keyWord;

    @EndNode
    private KeywordSyn keywordSyn;
}
