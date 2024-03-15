package com.sist.web.service;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.sist.web.entity.*;

@Service
public interface GymService extends JpaRepository<Gym, Integer> {
	@Query(value = "SELECT * FROM gym "
			+ "ORDER BY no ASC LIMIT 0,8", nativeQuery = true)
	public List<Gym> gymMainData();
	
	public Gym findByNo(int no);
	
	@Query(value = "SELECT * FROM gym "
			+ "ORDER BY no ASC LIMIT :start,12", nativeQuery = true)
	public List<Gym> gymListData(@Param("start") int start);
	
	@Query(value = "SELECT * FROM gym "
			+ "WHERE address LIKE CONCAT('%',:address,'%') "
			+ "ORDER BY no ASC LIMIT :start,12", nativeQuery = true)
	public List<Gym> gymFindData(@Param("address") String address, @Param("start") int start);
	
	@Query(value = "SELECT CEIL(COUNT(*)/12.0) FROM gym "
			+ "WHERE address LIKE CONCAT('%',:address,'%')", nativeQuery = true)
	public int gymFindTotalPage(@Param("address") String address);
}
