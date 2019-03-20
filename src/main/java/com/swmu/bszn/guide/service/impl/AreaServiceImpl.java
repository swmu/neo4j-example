package com.swmu.bszn.guide.service.impl;

import com.swmu.bszn.guide.dao.AreaDao;
import com.swmu.bszn.guide.entity.Area;
import com.swmu.bszn.guide.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaDao areaDao;

    @Override
    public Area queryAreaByAreaId(int areaId) {
        Area byAreaId = areaDao.findByAreaId(areaId);
        return byAreaId;
    }

    @Override
    public List<Area> queryAllArea() {
        List<Area> all = areaDao.findAll();
        return all;
    }

    @Override
    public Area queryAreaById(int parentId) {
        Optional<Area> byId = areaDao.findById(parentId);
        return byId.get();
    }
}
