package com.swmu.bszn.guide.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 办事指南
 * @descripede
 * @author seven.mu
 * @date 2019/3/12-14:22
 */
@Setter
@Getter
@Entity(name = "gzszf_data_guidance")
public class Guide {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "area_id")
    private  int areaId;

    @Column(name = "title")
    private String title;

    @Column(name = "url")
    private String url;

}
