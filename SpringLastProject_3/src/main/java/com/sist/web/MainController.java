package com.sist.web;

import java.security.Principal;
import java.util.*;

import javax.servlet.http.HttpSession;

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
	
	@Autowired
	private MemberService mService;
	
	@GetMapping("main/main.do")
	public String main_main(Model model, Principal p, HttpSession session)
	{
		// 자동 로그인 일 때 세션에 저장을 하고 들어가는 부분
		if(p!=null) // p!=null을 해야 함 why? p값에 세션이 다 들어가 있기 때문. 그래서 로그인이 안된 경우 실행하면 시작하자마자 바로 에러남
		{
			MemberVO vo=mService.memberSessionInfoData(p.getName());
			session.setAttribute("userId", vo.getUserId());
			session.setAttribute("userName", vo.getUserName());
			session.setAttribute("sex", vo.getSex());
			session.setAttribute("address", vo.getAddr1() + " " + vo.getAddr2());
			session.setAttribute("phone", vo.getPhone());
			session.setAttribute("email", vo.getEmail());
		}
		
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
