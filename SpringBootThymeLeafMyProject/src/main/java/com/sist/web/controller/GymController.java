package com.sist.web.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.web.dao.*;
import com.sist.web.entity.*;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class GymController {
	@Autowired
	private GymDAO dao;
	
	@Autowired
	private ReplyDAO rDao;
	
	@GetMapping("/gym/main")
	public String recipe_main(String page, HttpServletRequest request, Model model)
	{
		// Model => HttpServletRequest를 대체 => 전송 객체
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=20;
		int start=(rowSize*curpage)-rowSize;
		List<Gym> list=dao.gymListData(start);
		int count=dao.gymRowCount();
		int totalpage=(int)(Math.ceil(count/20.0));
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage)
			endPage=totalpage;
		
		Cookie[] cookies=request.getCookies();
		List<Gym> cList=new ArrayList<Gym>();
		int k=0;
		if(cookies!=null)
		{
			for(int i=cookies.length-1;i>=0;i--)
			{
				if(cookies[i].getName().startsWith("gym")) // getName="gym" + no
				{
					if(k>8)
						break;
					String no=cookies[i].getValue();
					Gym r=dao.findByNo(Integer.parseInt(no));
					cList.add(r);
					k++;
				}
			}
		}
		
		model.addAttribute("cList", cList);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("count", count);
		model.addAttribute("list", list);
		
		model.addAttribute("main_html", "gym/main");
		return "main";
	}
	
	@GetMapping("/gym/before_detail") // 쿠키 저장 후 디테일로 넘어가게
	public String gym_before(int no, RedirectAttributes ra, HttpServletResponse response)
	{
		// 쿠키에 저장
		Cookie cookie=new Cookie("gym" + no, String.valueOf(no)); // 뒤에 그냥 no하면 에러. cookie는 저장시 문자열만 저장 가능
		cookie.setPath("/");
		cookie.setMaxAge(60*60*24);
		response.addCookie(cookie); // 클라이언트 브라우저로 전송
		ra.addAttribute("no", no);
		return "redirect:../gym/detail";
		/*
		 *   RedirectAttributes: sendRedirect를 이용해서 데이터 전송
		 *   Model: forward
		 */
	}
	
	@GetMapping("/gym/detail")
	public String gym_detail(int no, Model model)
	{
		List<Reply> list=rDao.replyListData(no);
		Gym vo=dao.findByNo(no);
		model.addAttribute("vo", vo);
		model.addAttribute("list", list);
		model.addAttribute("main_html", "gym/detail");
		return "main";
	}
	
	@RequestMapping("/gym/find")
	public String gym_find(String page, String address, HttpServletRequest request, Model model)
	{
		if(address==null)
			address="강남";
		
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=20;
		int start=(rowSize*curpage)-rowSize;
		List<GymListVO> list=dao.gymFindData(start, address);
		int totalpage=dao.gymFindTotalpage(address);
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage)
			endPage=totalpage;
		
		Cookie[] cookies=request.getCookies();
		List<Gym> cList=new ArrayList<Gym>();
		int k=0;
		if(cookies!=null)
		{
			for(int i=cookies.length-1;i>=0;i--)
			{
				if(cookies[i].getName().startsWith("gym")) // getName="gym" + no
				{
					if(k>8)
						break;
					String no=cookies[i].getValue();
					Gym r=dao.findByNo(Integer.parseInt(no));
					cList.add(r);
					k++;
				}
			}
		}
		
		model.addAttribute("cList", cList);
		
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("list", list);
		model.addAttribute("address", address);
		model.addAttribute("main_html", "gym/find");
		return "main";
	}
}
