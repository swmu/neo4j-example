package com.swmu.bszn.guide.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Setter
@Getter
@Entity(name = "gzszf_bszn_keyword")
public class LocalKeyword {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "keyword")
    private String keyword;

    @Column(name = "synonyms")
    private String synonyms;
}
