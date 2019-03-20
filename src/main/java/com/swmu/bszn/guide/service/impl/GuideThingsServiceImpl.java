package com.swmu.bszn.guide.service.impl;

import com.swmu.bszn.guide.dao.GuideThingsDao;
import com.swmu.bszn.guide.entity.GuideThings;
import com.swmu.bszn.guide.service.GuideThingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuideThingsServiceImpl implements GuideThingsService {

    @Autowired
    private GuideThingsDao guideThingsDao;

    @Override
    public List<GuideThings> queryAll() {
        List<GuideThings> all = guideThingsDao.findAll();
        return all;
    }
}
