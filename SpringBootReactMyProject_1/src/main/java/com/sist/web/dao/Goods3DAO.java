package com.sist.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.entity.Goods1;
import com.sist.web.entity.Goods3;

public interface Goods3DAO extends JpaRepository<Goods3, Integer> {
	@Query(value = "SELECT * FROM goods3 ORDER BY gno ASC "
			+ "LIMIT :start:, 9", nativeQuery = true)
	public List<Goods1> goods3ListData(@Param("start") int start);
	
	@Query(value = "SELECT CEIL(COUNT(*)/9.0) FROM goods3", nativeQuery = true)
	public int goods3ListTotalPage();
	
	public Goods3 findByGno(int gno);
}
