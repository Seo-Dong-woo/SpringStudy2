package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.dao.*;

public interface GoodsMapper {
	@Select("SELECT no, goods_name, goods_sub, goods_price, goods_discount, goods_first_price, goods_delivery, goods_poster, num "
			+ "FROM (SELECT no, goods_name, goods_sub, goods_price, goods_discount, goods_first_price, goods_delivery, goods_poster, rownum as num "
			+ "FROM (SELECT no, goods_name, goods_sub, goods_price, goods_discount, goods_first_price, goods_delivery, goods_poster "
			+ "FROM goods_all ORDER BY no ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<GoodsVO> goodsAllListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM goods_all")
	public int goodsTotalPage();
}
