package com.sist.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.entity.Goods1;
import com.sist.web.entity.Goods4;

public interface Goods4DAO extends JpaRepository<Goods4, Integer> {
	@Query(value = "SELECT * FROM goods4 ORDER BY gno ASC "
			+ "LIMIT :start:, 9", nativeQuery = true)
	public List<Goods1> goods4ListData(@Param("start") int start);
	
	@Query(value = "SELECT CEIL(COUNT(*)/9.0) FROM goods4", nativeQuery = true)
	public int goods4ListTotalPage();
	
	public Goods4 findByGno(int gno);
}
