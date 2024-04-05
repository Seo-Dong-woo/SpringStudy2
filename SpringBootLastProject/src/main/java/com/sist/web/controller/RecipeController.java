package com.sist.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.entity.Recipe;
import com.sist.web.entity.Recipedetail;
import com.sist.web.service.RecipeService;
import com.sist.web.service.RecipedetailService;

@RestController
@CrossOrigin(origins = "*")
public class RecipeController {
	@Autowired
	private RecipeService rService;
	
	@Autowired
	private RecipedetailService rsService;
	
	@GetMapping("/recipe/list_react")
	public Map recipe_list(int page)
	{
		int rowSize=12;
		int start=(rowSize*page)-rowSize;
		List<Recipe> list=rService.recipeListData(start);
		int count=(int)rService.count();
		int totalpage=(int)(Math.ceil(count)/12.0);
		
		Map map=new HashMap();
		map.put("recipe_list", list);
		map.put("totalpage", totalpage);
		
		return map;
	}
	
	@GetMapping("/recipe/detail_react")
	public Map recipe_detail(int no)
	{
		Map map=new HashMap();
		Recipedetail rd=rsService.findByNo(no);
		List<String> iList=new ArrayList<String>();
		List<String> mList=new ArrayList<String>();
		String make=rd.getFoodmake();
		String[] makes=make.split("\n");
		for(String s:makes)
		{
			StringTokenizer st=new StringTokenizer(s,"^");
			mList.add(st.nextToken());
			iList.add(st.nextToken());
		}
		String stuff=rd.getStuff();
		stuff=stuff.replaceAll("구매", "");
		rd.setStuff(stuff);
		map.put("detail", rd);
		map.put("make", mList);
		map.put("images", iList);
		
		return map;	
	}
	
	@GetMapping("/recipe/chef_recipe_react")
	public Map chef_recipe(int page, String chef)
	{
		Map map=new HashMap();
		int rowSize=12;
		int start=(rowSize*page)-rowSize;
		List<Recipe> list=rService.chefRecipeList(chef, start);
		int totalpage=rService.chefTotalPage(chef);
		map.put("crList", list);
		map.put("totalpage", totalpage);
		
		return map;
	}
}
