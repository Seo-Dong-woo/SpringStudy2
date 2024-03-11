package com.sist.web.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.web.dao.*;
import com.sist.web.entity.*;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class GoodsController {
	@Autowired
	GoodsDAO dao;
	
	@GetMapping("/goods/goods_clothes")
	public String goods_clothes(String page, Model model)
	{
		// Model => HttpServletRequest를 대체 => 전송 객체
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=12;
		int start=(rowSize*curpage)-rowSize;
		List<Goods_Clothes> list=dao.goodsClothesListData(start);
		List<Goods_Clothes> goodsList=dao.goodsClothesMainData();
		int count=dao.goodsClothesRowCount();
		int totalpage=(int)(Math.ceil(count/12.0));
		
		final int BLOCK=5;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage)
			endPage=totalpage;
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("count", count);
		model.addAttribute("list", list);
		model.addAttribute("goodsList", goodsList);
		
		model.addAttribute("main_html", "goods/goods_clothes");
		return "main";
	}
	
	@GetMapping("/goods/before_detail") // 쿠키 저장 후 디테일로 넘어가게
	public String goods_before(int jmno, RedirectAttributes ra, HttpServletResponse response)
	{
		// 쿠키에 저장
		Cookie cookie=new Cookie("goods" + jmno, String.valueOf(jmno)); // 뒤에 그냥 no하면 에러. cookie는 저장시 문자열만 저장 가능
		cookie.setPath("/");
		cookie.setMaxAge(60*60*24);
		response.addCookie(cookie); // 클라이언트 브라우저로 전송
		ra.addAttribute("jmno", jmno);
		return "redirect:../goods/detail";
		/*
		 *   RedirectAttributes: sendRedirect를 이용해서 데이터 전송
		 *   Model: forward
		 */
	}
	
	@GetMapping("/goods/detail")
	public String goods_detail(int jmno, Model model)
	{
		Goods_Clothes vo=dao.findByJmno(jmno);
		model.addAttribute("vo", vo);
		model.addAttribute("main_html", "goods/detail");
		return "main";
	}
}
