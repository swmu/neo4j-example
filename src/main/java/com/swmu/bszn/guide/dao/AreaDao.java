package com.swmu.bszn.guide.dao;

import com.swmu.bszn.guide.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 持久化层
 * @descripede
 * @author seven.mu
 * @date 2019/3/12-14:32
 */
public interface AreaDao extends JpaRepository<Area, Integer>, JpaSpecificationExecutor<Area> {
    /**
     * 根据areaId查询对应area
     * @param areaId
     * @return
     */
    Area findByAreaId(int areaId);
}
