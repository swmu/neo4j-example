package com.swmu.bszn.guide.dao;

import com.swmu.bszn.guide.entity.Guide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * 办事指南持久类
 * @author seven.mu
 * @date 2019/3/12-14:33
 */
public interface GuideDao extends JpaRepository<Guide, Integer>, JpaSpecificationExecutor<Guide> {
    /**
     * 通过指南名称获取所有列表
     * @param title
     * @return
     */
    public List<Guide> findByTitle(String title);
}
