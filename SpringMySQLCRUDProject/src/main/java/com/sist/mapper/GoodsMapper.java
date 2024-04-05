package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;

public interface GoodsMapper {
	@Select("SELECT no, goods_price, goods_name, goods_poster "
			+ "FROM goods_all "
			+ "ORDER BY no ASC "
			+ "LIMIT #{start}, 20")
	public List<GoodsVO> goodListData(int start);
	
	@Select("SELECT CEIL(COUNT(*)/20) FROM goods_all")
	public int goodsTotalPage();
}
