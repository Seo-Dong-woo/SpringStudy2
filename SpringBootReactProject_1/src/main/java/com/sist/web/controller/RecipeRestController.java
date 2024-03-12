package com.sist.web.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.*;
import com.sist.web.entity.*;

@RestController
@CrossOrigin("*") // 모든 ip 허용
public class RecipeRestController {
	@Autowired
	private RecipeDAO dao;
	
	@Autowired
	private RecipeDetailDAO rDao;
	
	
//	@GetMapping("/recipe/list_react")
//	public Map recipeListData(int page)
//	{
//		int rowSize=12;
//		int start=(rowSize*page)-rowSize;
//		List<Recipe> list=dao.recipeListData(start);
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
//		map.put("count", count);
//		map.put("list", list);
//		
//		return map;
//		// Boot는 list에 자동으로 json이 만들어짐
//	}
	
//	@GetMapping("/recipe/detail_react")
//	public Map recipeDetailData(int no)
//	{
//		Map map=new HashMap();
//		Recipedetail r=rDao.findByNo(no);
//		String[] fm=r.getFoodmake().split("\n");
//		List<String> make=new ArrayList<String>();
//		List<String> image=new ArrayList<String>();
//		for(String food:fm)
//		{
//			StringTokenizer st=new StringTokenizer(food, "^");
//			make.add(st.nextToken());
//			image.add(st.nextToken());
//		}
//		map.put("recipe", r);
//		map.put("make", make);
//		map.put("images", image);
//		return map;
//	}
	
	////////////////////////////// 03/11
	@GetMapping("/recipe/list_react")
	public List<Recipe> recipeListData(int page)
	{
		int rowSize=12;
		int start=(rowSize*page)-rowSize;
		List<Recipe> list=dao.recipeListData(start);
		
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
//		map.put("count", count);
//		map.put("list", list);
		
		return list;
		// Boot는 list에 자동으로 json이 만들어짐
	}
	
	@GetMapping("/recipe/count_react")
	public String recipe_count()
	{
		int count=(int)dao.count();
		return String.valueOf(count);
	}
	
	@GetMapping("recipe/page_react")
	public String recipe_page()
	{
		int count=(int)dao.count();
		int total=(int)(Math.ceil(count/12.0));
		return String.valueOf(total);
	}
	
	@GetMapping("/recipe/image_react")
	public List<String> recipeImageData(int no)
	{
		Recipedetail r=rDao.findByNo(no);
		List<String> images=new ArrayList<String>();
		String[] fm=r.getFoodmake().split("\n");
		// ~~~~~ ^ 이미지
		for(String s:fm)
		{
			String ss=s.substring(s.indexOf("^")+1);
			images.add(ss);
		}
		
		return images;
	}
	
	@GetMapping("/recipe/make_react")
	public List<String> recipeMakeData(int no)
	{
		Recipedetail r=rDao.findByNo(no);
		List<String> makes=new ArrayList<String>();
		String[] fm=r.getFoodmake().split("\n");
		// ~~~~~ ^ 이미지
		for(String s:fm)
		{
			String ss=s.substring(0, s.indexOf("^"));
			makes.add(ss);
		}
		
		return makes;
	}
	
	@GetMapping("/recipe/detail_react")
	public Recipedetail recipeDetailData(int no)
	{
		Map map=new HashMap();
		Recipedetail r=rDao.findByNo(no);
		String[] fm=r.getFoodmake().split("\n");
		List<String> make=new ArrayList<String>();
		List<String> image=new ArrayList<String>();
		for(String food:fm)
		{
			StringTokenizer st=new StringTokenizer(food, "^");
			make.add(st.nextToken());
			image.add(st.nextToken());
		}
		map.put("recipe", r);
		map.put("make", make);
		map.put("posters", image);
		return r;
	}
}
