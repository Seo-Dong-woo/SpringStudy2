package com.sist.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.entity.Goods1;
import com.sist.web.entity.Goods2;

public interface Goods2DAO extends JpaRepository<Goods2, Integer> {
	@Query(value = "SELECT * FROM goods2 ORDER BY gno ASC "
			+ "LIMIT :start:, 9", nativeQuery = true)
	public List<Goods1> goods2ListData(@Param("start") int start);
	
	@Query(value = "SELECT CEIL(COUNT(*)/9.0) FROM goods2", nativeQuery = true)
	public int goods2ListTotalPage();
	
	public Goods2 findByGno(int gno);
}
