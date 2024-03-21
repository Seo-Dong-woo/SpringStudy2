package com.sist.web.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.entity.Goods_Men_Clothes;
import com.sist.web.entity.Goods_Women_Clothes;

public interface GoodsWomenService extends JpaRepository<Goods_Women_Clothes, Integer> {
	@Query(value = "SELECT * FROM jmwshop "
			+ "ORDER BY jwno ASC LIMIT :start,12", nativeQuery = true)
	public List<Goods_Women_Clothes> goods2ListData(@Param("start") int start);
	
	public Goods_Women_Clothes findByJwno(int jwno);
}
