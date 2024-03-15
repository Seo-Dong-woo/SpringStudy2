package com.sist.web.service;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.sist.web.entity.*;

@Service
public interface CampingService extends JpaRepository<Camping, Integer> {
	@Query(value = "SELECT * FROM camping "
			+ "ORDER BY mno ASC LIMIT 0,8", nativeQuery = true)
	public List<Camping> campingMainData();
	
	public Camping findByMno(int mno);
}
