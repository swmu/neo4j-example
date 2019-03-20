package com.swmu.bszn.guide.dao;

import com.swmu.bszn.guide.entity.GuideThings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuideThingsDao extends JpaRepository<GuideThings, String> {
}
