package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.FoodMapper;

@Repository
public class FoodDAO {
	@Autowired // 스프링에서 자동 주입
	private FoodMapper mapper;
	
	public List<FoodVO> foodListData(Map map)
	{
		return mapper.foodListData(map);
	}
	
	public int foodTotalPage()
	{
		return mapper.foodTotalPage();
	}
}
