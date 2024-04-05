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
	private FoodService fService;
	
	@Autowired
	private RecipeService rService;
	
	@GetMapping("/")
	public List<Food> foodMainData()
	{
		List<Food> list=fService.foodMainData();
		for(Food vo:list)
		{
			vo.setPoster("http://www.menupan.com"+vo.getPoster());
		}
		return list;
	}
	
	@GetMapping("/main")
	public Food foodMainMainVO()
	{
		Food food=fService.findByFno(180);
		return food;
	}
	
	@GetMapping("/recipe/main")
	public List<Recipe> recipeMainList()
	{
		return rService.recipeMainList();
	}
}
