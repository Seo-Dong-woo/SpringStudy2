package com.sist.web.service;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.sist.web.entity.*;

@Service
public interface ChefService extends JpaRepository<Chef, Integer> {
	@Query(value = "SELECT * FROM chef "
			+ "ORDER BY cno ASC LIMIT :start,20", nativeQuery = true)
	public List<Chef> chefListData(@Param("start") int start);
	
	
}
