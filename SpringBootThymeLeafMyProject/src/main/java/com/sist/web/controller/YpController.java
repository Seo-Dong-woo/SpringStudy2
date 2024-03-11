package com.sist.web.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.web.dao.*;
import com.sist.web.entity.*;

@Controller
public class YpController {
	@Autowired
	private YpDAO dao;
	
	
	@GetMapping("/yp/list")
	public String yp_list(String page, Model model)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=20;
		int start=(rowSize*curpage)-rowSize;
		List<YpListVO> list=dao.ypListData(start);
		int totalpage=dao.ypTotalpage();
		
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
		
		model.addAttribute("main_html", "yp/list");
		return "main";
	}
	
	@GetMapping("/yp/detail")
	public String yp_detail(int no, Model model)
	{
		Yp vo=dao.findByNo(no);
		model.addAttribute("vo", vo);
		model.addAttribute("main_html", "yp/detail");
		return "main";
	}
}
