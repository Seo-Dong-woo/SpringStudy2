package com.sist.web.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.*;
import com.sist.web.entity.*;

@RestController
@CrossOrigin("*")
public class FoodRestController {
	@Autowired
	private FoodDAO dao;
	
//	@GetMapping("food/list_react")
//	public Map foodListData(int page)
//	{
//		int rowSize=12;
//		int start=(rowSize*page)-rowSize;
//		List<FoodListVO> list=dao.foodListData(start);
//		
//		Map map=new HashMap();
//		int count=(int)dao.count();
//		int totalpage=(int)(Math.ceil(count/12.0));
//		final int BLOCK=10;
//		int startPage=((page-1)/BLOCK*BLOCK)+1;
//		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
//		if(endPage>totalpage)
//			endPage=totalpage;
//		
//		map.put("curpage", page);
//		map.put("totalpage", totalpage);
//		map.put("startPage", startPage);
//		map.put("endPage", endPage);
//		map.put("list", list);
//		
//		return map;
//	}
	
	@GetMapping("/food/detail_react")
	public Map foodDetailData(int fno)
	{
		Map map=new HashMap();
		FoodEntity vo=dao.findByFno(fno);
		map.put("vo", vo);
		return map;
	}
	
//	@RequestMapping("/food/find_react")
//	public Map foodFindData(int page, String address)
//	{
//		int rowSize=12;
//		int start=(rowSize*page)-rowSize;
//		List<FoodEntity> list=dao.foodFindData(start, address);
//		Map map=new HashMap();
//		int totalpage=dao.foodFindTotalPage(address);
//		final int BLOCK=10;
//		int startPage=((page-1)/BLOCK*BLOCK)+1;
//		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
//		if(endPage>totalpage)
//			endPage=totalpage;
//		
//		map.put("curpage", page);
//		map.put("totalpage", totalpage);
//		map.put("startPage", startPage);
//		map.put("endPage", endPage);
//		map.put("list", list);
//		return map;
//	}
	
//	@GetMapping("/food/list_react")
//	public Map food_list(int page)
//	{
//		System.out.println("page: " + page);
//		int rowSize=12;
//		int start=(rowSize*page)-rowSize;
//		List<FoodEntity> list=dao.foodListData(start);
//		Map map=new HashMap();
//		int totalpage=dao.foodListTotalPage();
//		final int BLOCK=10;
//		int startPage=((page-1)/BLOCK*BLOCK)+1;
//		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
//		if(endPage>totalpage)
//			endPage=totalpage;
//		
//		map.put("curpage", page);
//		map.put("totalpage", totalpage);
//		map.put("startPage", startPage);
//		map.put("endPage", endPage);
//		map.put("list", list);
//		
//		return map;
//	}
	
	// 03/11
	@GetMapping("/food/list_react")
	public List<FoodEntity> food_list(int page)
	{
//		System.out.println("page: " + page);
		int rowSize=12;
		int start=(rowSize*page)-rowSize;
		List<FoodEntity> list=dao.foodListData(start);
//		Map map=new HashMap();
//		int totalpage=dao.foodListTotalPage();
//		final int BLOCK=10;
//		int startPage=((page-1)/BLOCK*BLOCK)+1;
//		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
//		if(endPage>totalpage)
//			endPage=totalpage;
//		
//		map.put("curpage", page);
//		map.put("totalpage", totalpage);
//		map.put("startPage", startPage);
//		map.put("endPage", endPage);
//		map.put("list", list);
		
//		return map;
		return list;
	}
	
	@GetMapping("food/food_totalpage_react")
	public String food_totalpage()
	{
		int total=dao.foodListTotalPage();
		return String.valueOf(total);
	}
	
	@GetMapping("food/food_detail_react")
	public FoodEntity food_detail(int fno)
	{
		FoodEntity vo=dao.findByFno(fno);
		return vo;
	}
	
	@RequestMapping("/food/find_react")
	public List<FoodEntity> foodFindData(int page, String address)
	{
		int rowSize=12;
		int start=(rowSize*page)-rowSize;
		List<FoodEntity> list=dao.foodFindData(start, address);
//		Map map=new HashMap();
//		int totalpage=dao.foodFindTotalPage(address);
//		final int BLOCK=10;
//		int startPage=((page-1)/BLOCK*BLOCK)+1;
//		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
//		if(endPage>totalpage)
//			endPage=totalpage;
//		
//		map.put("curpage", page);
//		map.put("totalpage", totalpage);
//		map.put("startPage", startPage);
//		map.put("endPage", endPage);
//		map.put("list", list);
		return list;
	}
	
	@GetMapping("food/find_totalpage_react")
	public String find_totalpage(String address)
	{
		int total=dao.foodFindTotalPage(address);
		return String.valueOf(total);
	}
}
