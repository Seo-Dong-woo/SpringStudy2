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
public class CampingRestController {
	@Autowired
	private CampingDAO dao;
	
	@GetMapping("/camping/list_react")
	public Map camping_list(int page)
	{
		System.out.println("page: " + page);
		int rowSize=12;
		int start=(rowSize*page)-rowSize;
		List<Camping> list=dao.campingListData(start);
		Map map=new HashMap();
		int totalpage=dao.campingListTotalPage();
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
	
	@GetMapping("/camping/detail_react")
	public Map campingDetailData(int mno)
	{
		Map map=new HashMap();
		Camping vo=dao.findByMno(mno);
		map.put("vo", vo);
		return map;
	}
}
