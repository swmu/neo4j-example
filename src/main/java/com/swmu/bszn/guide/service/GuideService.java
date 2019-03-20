package com.swmu.bszn.guide.service;

import com.swmu.bszn.guide.entity.Guide;

import java.util.List;

/**
 *
 * 办事指南 操作mysql 服务层
 *
 * @author seven.mu
 * @date 2019/3/12-14:40
 */
public interface GuideService {
    /**
     * 根据办事指南名称获取对应所有的办事指南列表
     * @param name
     * @return
     */
    List<Guide> queryGuideByName(String name);
}
