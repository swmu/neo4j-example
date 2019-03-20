package com.swmu.bszn.guide.service.impl;

import com.swmu.bszn.guide.dao.LocalKeywordDao;
import com.swmu.bszn.guide.entity.LocalKeyword;
import com.swmu.bszn.guide.service.LocalKeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalKeywordServiceImpl implements LocalKeywordService {

    @Autowired
    private LocalKeywordDao localKeywordDao;

    @Override
    public List<LocalKeyword> queryAllKeyword() {
        List<LocalKeyword> all = localKeywordDao.findAll();
        return all;
    }
}
