package com.swmu.bszn.guide.service;

import com.swmu.bszn.guide.entity.Area;

import java.util.List;

/**
 *
 * 操作mysql数据库区域的服务层
 *
 * @author seven.mu
 * @date 2019/3/12-14:42
 */
public interface AreaService {
    /**
     * 根据areaId 查询对应区域
     * areaId 为表格对应的data值
     * @param areaId
     * @return
     */
    Area queryAreaByAreaId(int areaId);
    /**
     * 根据parentId 查询对应层级
     * @param parentId
     * @return
     */
    Area queryAreaById(int parentId);


    List<Area> queryAllArea();
}
