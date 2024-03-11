package com.sist.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.web.dao.*;
import com.sist.web.entity.*;

@Controller
public class MainController {
	@Autowired
	private GymDAO dao;
	
	@Autowired
	private GoodsDAO goodsDao;
	
	@GetMapping("/")
	public String main_page(Model model)
	{
		List<Gym> list=dao.gymMainData();
		List<Goods_Clothes> goodsList=goodsDao.goodsClothesMainData();
		model.addAttribute("list", list);
		model.addAttribute("goodsList", goodsList);
		model.addAttribute("main_html", "main/home");
		return "main";
	}
}
