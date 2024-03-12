package com.sist.web.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.*;
import com.sist.web.entity.*;

@RestController
@CrossOrigin("*")
public class GoodsRestController {
	@Autowired
	private Goods1DAO dao;
	
	@GetMapping("/goods1/list_react")
	public Map goods_list(int page)
	{
		System.out.println("page: " + page);
		int rowSize=9;
		int start=(rowSize*page)-rowSize;
		List<Goods1> list=dao.goods1ListData(start);
		Map map=new HashMap();
		int totalpage=dao.goods1ListTotalPage();
		final int BLOCK=10;
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("list", list);
		
		return map;
	}
	
	@GetMapping("/goods1/detail_react")
	public Map goodsDetailData(int gno)
	{
		Map map=new HashMap();
		Goods1 vo=dao.findByGno(gno);
		map.put("vo", vo);
		return map;
	}
}
