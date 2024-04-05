package com.sist.web;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.vo.*;
import com.sist.service.*;

@Controller
public class MainController {
	@Autowired
	private FoodService service;
	
	@Autowired
	private RecipeService rService;
	
	@GetMapping("main/main.do")
	public String main_main(Model model)
	{
		// JSP로 값을 전송 객체 => 전송 객체 ==> Model(HttpServletRequest)
		List<FoodVO> foodList=service.foodHome12();
		List<RecipeVO> recipeList=rService.recipeHome6();
		List<ChefVO> chefList=rService.chefHome6();
		model.addAttribute("foodList", foodList);
		model.addAttribute("recipeList", recipeList);
		model.addAttribute("chefList", chefList);
		return "main";
	}
}
