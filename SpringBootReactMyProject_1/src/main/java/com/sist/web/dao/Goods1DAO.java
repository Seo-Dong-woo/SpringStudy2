package com.sist.web.dao;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.entity.*;

public interface Goods1DAO extends JpaRepository<Goods1, Integer> {
	@Query(value = "SELECT * FROM goods1 ORDER BY gno ASC "
			+ "LIMIT 0, 8", nativeQuery = true)
	public List<Goods1> goods1MainData();
	
	@Query(value = "SELECT * FROM goods1 ORDER BY gno ASC "
			+ "LIMIT :start, 9", nativeQuery = true)
	public List<Goods1> goods1ListData(@Param("start") int start);
	
	@Query(value = "SELECT CEIL(COUNT(*)/9.0) FROM goods1", nativeQuery = true)
	public int goods1ListTotalPage();
	
	// 상세보기
	public Goods1 findByGno(int gno);
	
	
}
