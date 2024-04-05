package com.sist.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.vo.*;
import com.sist.dao.*;

@Service
public class GoodsServiceImpl implements GoodsService {
	@Autowired
	private GoodsDAO dao;
	
	@Override
	public List<GoodsVO> goodsAllListData(int start, int end) {
		// TODO Auto-generated method stub
		return dao.goodsAllListData(start, end);
	}

	@Override
	public int goodsAllTotalPage() {
		// TODO Auto-generated method stub
		return dao.goodsAllTotalPage();
	}

}
