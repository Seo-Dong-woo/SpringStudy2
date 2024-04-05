package com.sist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.dao.FoodDAO;
import com.sist.vo.FoodVO;

@Service
public class FoodServiceImpl implements FoodService {
	@Autowired // 메모리 할당된 주소를 달라
	private FoodDAO dao;

	@Override
	public List<FoodVO> foodsListData(int start, int end) {
		// TODO Auto-generated method stub
		return dao.foodsListData(start, end);
	}

	@Override
	public int foodTotalPage() {
		// TODO Auto-generated method stub
		return dao.foodTotalPage();
	}
}
