package com.sist.web.dao;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.*;

@Repository
public interface GoodsDAO extends JpaRepository<Goods_Clothes, Integer> {
	@Query(value = "SELECT jmno, title, subtitle, poster, price, option1, option2, hit "
			+ "FROM jmmshop ORDER BY jmno ASC "
			+ "LIMIT 0, 3", nativeQuery = true)
	public List<Goods_Clothes> goodsClothesMainData();
	
	// 목록 출력
	@Query(value = "SELECT jmno, title, subtitle, poster, price, option1, option2, hit "
			+ "FROM jmmshop ORDER BY jmno ASC "
			+ "LIMIT :start, 12", nativeQuery = true)
	public List<Goods_Clothes> goodsClothesListData(@Param("start") int start);
		
	@Query(value = "SELECT COUNT(*) FROM jmmshop", nativeQuery = true)
	public int goodsClothesRowCount();
	
	// 상세보기
	public Goods_Clothes findByJmno(int jmno); // 상세보기
}
