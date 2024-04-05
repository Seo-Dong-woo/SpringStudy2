package com.sist.dao;

import java.util.*;

import com.sist.mapper.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GoodsDAO {
	@Autowired
	private GoodsMapper mapper;
	
	public List<GoodsVO> goodsAllListData(Map map)
	{
		return mapper.goodsAllListData(map);
	}
	
	public int goodsTotalPage()
	{
		return mapper.goodsTotalPage();
	}
}
