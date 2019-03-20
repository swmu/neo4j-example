package com.swmu.bszn.guide.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 区域
 * @descripede
 * @author seven.mu
 * @date 2019/3/12-14:28
 */
@Getter
@Setter
@Entity(name = "gzszf_data_area")
public class Area {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "data")
    private int areaId;

    @Column(name = "parent_id")
    private int parentId;
}
