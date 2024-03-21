package com.sist.web.service;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.*;

@Repository
public interface GoodsNutService extends JpaRepository<Goods_Nut, Integer> {
	@Query(value = "SELECT * FROM goods_nut "
			+ "ORDER BY gno ASC LIMIT 0, 3", nativeQuery = true)
	public List<Goods_Nut> goodsNutMainList();
}
