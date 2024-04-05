package com.sist.service;

import java.util.List;

import com.sist.vo.GoodsVO;

public interface GoodsService {
	public List<GoodsVO> goodsAllListData(int start, int end);
	public int goodsAllTotalPage();
}
