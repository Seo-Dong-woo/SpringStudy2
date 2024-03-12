package com.sist.web.dao;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.entity.*;

public interface CampingDAO extends JpaRepository<Camping, Integer> {
	@Query(value = "SELECT * FROM camping ORDER BY mno ASC "
			+ "LIMIT 0, 8", nativeQuery = true)
	public List<Camping> campingMainData();
	
	@Query(value = "SELECT * FROM camping ORDER BY mno ASC "
			+ "LIMIT :start, 12", nativeQuery = true)
	public List<Camping> campingListData(@Param("start") int start);
	
	@Query(value = "SELECT CEIL(COUNT(*)/12.0) FROM camping", nativeQuery = true)
	public int campingListTotalPage();
	
	// 상세보기
	public Camping findByMno(int mno);
}
