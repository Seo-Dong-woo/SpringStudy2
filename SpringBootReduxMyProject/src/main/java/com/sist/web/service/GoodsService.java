package com.sist.web.service;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.*;

@Repository
public interface GoodsService extends JpaRepository<Goods_Clothes, Integer> {
	@Query(value = "SELECT * FROM jmmshop "
			+ "ORDER BY jmno ASC LIMIT 0, 5", nativeQuery = true)
	public List<Goods_Clothes> goodsMainList();
	
	@Query(value = "SELECT * FROM jmmshop "
			+ "ORDER BY jmno ASC LIMIT :start,12", nativeQuery = true)
	public List<Goods_Clothes> goods1ListData(@Param("start") int start);
	
	
}
