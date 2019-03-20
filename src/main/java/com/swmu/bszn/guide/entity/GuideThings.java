package com.swmu.bszn.guide.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Setter
@Getter
@Entity(name = "gzszf_bszn_guide")
public class GuideThings {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "things")
    private String things;

    @Column(name = "item")
    private String item;

    @Column(name = "keyword")
    private String keyword;

    @Column(name = "intention")
    private String intention;
}
