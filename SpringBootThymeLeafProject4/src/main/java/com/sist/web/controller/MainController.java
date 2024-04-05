package com.sist.web.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.web.entity.*;
import com.sist.web.service.FoodService;

@Controller
public class MainController {
	@Autowired
	private FoodService fService;
	
	@GetMapping("/")
	public String main_main(String page, Model model)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=12;
		int start=(rowSize * curpage)-rowSize;
		List<FoodEntity> list=fService.foodListData(start);
		int totalpage=fService.foodTotalpage();
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("list", list);
		model.addAttribute("main_html", "main/home");
		return "main";
	}
	
	@GetMapping("/food_detail")
	public String food_detail(int fno, Model model)
	{
		FoodEntity vo=fService.findByFno(fno);
		model.addAttribute("vo", vo);
		model.addAttribute("main_html", "food/food_detail");
		//                  추가되는 영역     include
		return "main";
	}
	
	@GetMapping("/recipe/recipe_list")
	public String recipe_list(Model model)
	{
		model.addAttribute("main_html", "recipe/recipe_list");
		return "main";
	}
	
	@GetMapping("/recipe/chef_list")
	public String chef_list(Model model)
	{
		model.addAttribute("main_html", "recipe/chef_list");
		return "main";
	}
	
	@GetMapping("/board/board_list")
	public String board_list(Model model)
	{
		model.addAttribute("main_html", "board/board_list");
		return "main";
	}
}
