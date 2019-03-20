package com.swmu.bszn.guide.service.impl;

import com.swmu.bszn.guide.dao.GuideDao;
import com.swmu.bszn.guide.entity.Guide;
import com.swmu.bszn.guide.service.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * 服务层实现类
 *
 * @author seven.mu
 * @date 2019/3/12-14:50
 */
@Service
public class GuideServiceImpl implements GuideService {

    @Autowired
    private GuideDao guideDao;

    @Override
    public List<Guide> queryGuideByName(String name) {
        List<Guide> byName = guideDao.findByTitle(name);
        return byName;
    }
}
