package com.sist.web.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.entity.*;
import com.sist.web.service.*;

@RestController
@CrossOrigin(origins = "*")
public class GoodsController {
	@Autowired
	private GoodsMenService gmService;
	
	@Autowired
	private GoodsWomenService gwService;
	
	@GetMapping("/goods1/list_react")
	public Map goods1_list(int page)
	{
		int rowSize=12;
		int start=(rowSize*page)-rowSize;
		List<Goods_Men_Clothes> list=gmService.goods1ListData(start);
		int count=(int)gmService.count();
		int totalpage=(int)(Math.ceil(count)/12.0);
		
		Map map=new HashMap();
		map.put("goods1_list", list);
		map.put("totalpage", totalpage);
		
		return map;
	}
	
	@GetMapping("/goods2/list_react")
	public Map goods2_list(int page)
	{
		int rowSize=12;
		int start=(rowSize*page)-rowSize;
		List<Goods_Women_Clothes> list=gwService.goods2ListData(start);
		int count=(int)gwService.count();
		int totalpage=(int)(Math.ceil(count)/12.0);
		
		Map map=new HashMap();
		map.put("goods2_list", list);
		map.put("totalpage", totalpage);
		
		return map;
	}
	
	@GetMapping("/goods1/detail_react")
	public Goods_Men_Clothes goods1_detail(int jmno)
	{
		Goods_Men_Clothes gmc=gmService.findByJmno(jmno);
		gmc.setHit(gmc.getHit()+1); // 조회수 증가
		gmService.save(gmc); // 조회수 증가한 값을 저장
		gmc=gmService.findByJmno(jmno); // 조회수 증가한 값을 포함한 entity를 다시 불러옴
		return gmc;
	}
	
	@GetMapping("/goods2/detail_react")
	public Goods_Women_Clothes goods2_detail(int jwno)
	{
		Goods_Women_Clothes gwc=gwService.findByJwno(jwno);
		gwc.setHit(gwc.getHit()+1); // 조회수 증가
		gwService.save(gwc); // 조회수 증가한 값을 저장
		gwc=gwService.findByJwno(jwno); // 조회수 증가한 값을 포함한 entity를 다시 불러옴
		return gwc;
	}
}
