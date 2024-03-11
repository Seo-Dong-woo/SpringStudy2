package com.sist.web.dao;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.*;

@Repository
public interface GymDAO extends JpaRepository<Gym, Integer> {
	@Query(value = "SELECT no, title, poster, phone, address, time, hit "
			+ "FROM gym ORDER BY no ASC "
			+ "LIMIT 0, 3", nativeQuery = true)
	public List<Gym> gymMainData();
	
	// 목록 출력
	@Query(value = "SELECT no, title, poster, phone, address, time, hit "
			+ "FROM gym ORDER BY no ASC "
			+ "LIMIT :start, 20", nativeQuery = true)
	public List<Gym> gymListData(@Param("start") int start);
		
	@Query(value = "SELECT COUNT(*) FROM gym", nativeQuery = true)
	public int gymRowCount();
	
	// 상세보기
	public Gym findByNo(int no); // 상세보기
	
	// 찾기
	@Query(value = "SELECT no, title, poster "
			+ "FROM gym WHERE address LIKE CONCAT('%',:address,'%') "
			+ "LIMIT :start, 20", nativeQuery = true)
	public List<GymListVO> gymFindData(@Param("start") Integer start, @Param("address") String address);
	
	@Query(value = "SELECT CEIL(COUNT(*)/20.0) FROM gym "
			+ "WHERE address LIKE CONCAT('%',:address,'%')", nativeQuery = true)
	public int gymFindTotalpage(@Param("address") String address);
}
