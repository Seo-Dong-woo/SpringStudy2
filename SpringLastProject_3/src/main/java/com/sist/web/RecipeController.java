package com.sist.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
// 화면 변경
/*
 *   <route "/recipe/list" component="RecipeList"/>
 */
@Controller
public class RecipeController {
	@GetMapping("recipe/recipe_list.do")
	public String recipe_list()
	{
		return "recipe/recipe_list";
	}
	
	@GetMapping("recipe/chef_list.do")
	public String recipe_chef()
	{
		return "recipe/chef_list";
	}
	
	@GetMapping("recipe/chef_detail.do")
	public String chef_detail(int cno, Model model)
	{
		model.addAttribute("cno", cno);
		return "recipe/chef_detail";
	}
	
	@GetMapping("recipe/recipe_detail.do")
	public String recipe_detail(int no, Model model, HttpSession session)
	{
		String userId=(String)session.getAttribute("userId");
		String sessionId="";
		if(userId==null)
			sessionId="";
		else
			sessionId=userId;
		model.addAttribute("no", no);
		model.addAttribute("sessionId", sessionId);
		
		// 댓글
		
		return "recipe/recipe_detail";
	}
}
