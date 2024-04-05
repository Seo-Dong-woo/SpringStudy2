package com.sist.dao;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
import com.sist.vo.*;

@Repository
public class GoodsDAO {
	@Autowired
	private GoodsMapper mapper;
	
	public List<GoodsVO> goodsAllListData(int start, int end)
	{
		return mapper.goodsAllListData(start, end);
	}
	
	public int goodsAllTotalPage()
	{
		return mapper.goodsAllTotalPage();
	}
}
