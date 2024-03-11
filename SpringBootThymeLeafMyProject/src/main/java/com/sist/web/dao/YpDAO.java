package com.sist.web.dao;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.entity.*;

public interface YpDAO extends JpaRepository<Yp, Integer> {
	@Query(value = "SELECT no, title, poster "
			+ "FROM sportsreserve ORDER BY no ASC "
			+ "LIMIT :start, 20", nativeQuery = true)
	public List<YpListVO> ypListData(@Param("start") int start);
	
	@Query(value = "SELECT CEIL(COUNT(*)/20.0) FROM sportsreserve", nativeQuery = true)
	public int ypTotalpage();
	
	public Yp findByNo(int no);
}
