package com.sist.web.service;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.*;

@Repository
public interface FoodService extends JpaRepository<Food, Integer> {
	@Query(value = "SELECT * FROM food_cal "
			+ "ORDER BY fno ASC LIMIT :start,12", nativeQuery = true)
	public List<Food> foodListData(@Param("start") int start);
	
	@Query(value = "SELECT * FROM food_cal "
			+ "WHERE name LIKE CONCAT('%',:name,'%') "
			+ "ORDER BY fno ASC LIMIT :start,12", nativeQuery = true)
	public List<Food> foodFindData(@Param("name") String name, @Param("start") int start);
	
	@Query(value = "SELECT CEIL(COUNT(*)/12.0) FROM food_cal "
			+ "WHERE name LIKE CONCAT('%',:name,'%')", nativeQuery = true)
	public int foodFindTotalPage(@Param("name") String name);
	
	public Food findByFno(int fno);
}
