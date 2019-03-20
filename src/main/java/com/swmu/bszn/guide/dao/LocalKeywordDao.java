package com.swmu.bszn.guide.dao;

import com.swmu.bszn.guide.entity.LocalKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalKeywordDao extends JpaRepository<LocalKeyword, Integer> {
}
