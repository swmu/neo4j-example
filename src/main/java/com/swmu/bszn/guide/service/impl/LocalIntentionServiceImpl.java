package com.swmu.bszn.guide.service.impl;

import com.swmu.bszn.guide.dao.LocalIntentionDao;
import com.swmu.bszn.guide.entity.LocalIntention;
import com.swmu.bszn.guide.service.LocalIntentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalIntentionServiceImpl implements LocalIntentionService {

    @Autowired
    private LocalIntentionDao localIntentionDao;


    @Override
    public List<LocalIntention> queryAllIntention() {
        List<LocalIntention> all = localIntentionDao.findAll();
        return all;
    }
}
