package com.sist.web.service;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.sist.web.entity.FoodEntity;
@Service
public interface FoodService extends JpaRepository<FoodEntity, Integer> {
	@Query(value = "SELECT * FROM food_house "
			+ "ORDER BY fno ASC LIMIT :start, 12", nativeQuery = true)
	public List<FoodEntity> foodListData(@Param("start") Integer start);
	
	@Query(value = "SELECT CEIL(COUNT(*)/12.0) FROM food_house")
	public int foodTotalpage();
	
	// 상세보기 SELECT * FROM food_house WHERE fno=
	//       SELECT * FROM food_house WHERE name=? AND address=?
	//                                findByNameANDAddress
	//       WHERE name LIKE   => findByNameLike
	public FoodEntity findByFno(int fno); // findBy는 WHERE문장이 생성
}
