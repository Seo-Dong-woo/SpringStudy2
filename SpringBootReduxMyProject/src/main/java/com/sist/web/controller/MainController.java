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
public class MainController {
	@Autowired
	private GymService gService;
	
	@Autowired
	private GoodsService gsService;
	
	@GetMapping("/")
	public List<Gym> gymMainData()
	{
		List<Gym> list=gService.gymMainData();
		return list;
	}
	
	@GetMapping("/main")
	public Gym gymMainMainVO()
	{
		Gym gym=gService.findByNo(180);
		return gym;
	}
	
	@GetMapping("/goods/main")
	public List<Goods_Clothes> goodsMainList()
	{
		return gsService.goodsMainList();
	}
}
