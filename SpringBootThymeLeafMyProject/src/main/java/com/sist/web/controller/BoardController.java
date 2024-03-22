package com.sist.web.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sist.web.dao.*;
import com.sist.web.entity.*;

@Controller
public class BoardController {
	@Autowired
	private BoardDAO dao;
	
	@GetMapping("/board/list")
	public String boardList(String page, Model model)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=10;
		int start=(rowSize*curpage)-rowSize; // Limit => 0
		List<BoardVO> list=dao.boardListData(start);
		int count=(int)dao.count();
		int totalpage=(int)(Math.ceil(count/10.0));
		
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("list", list);
		model.addAttribute("main_html", "board/list");
		return "main";
	}
	
	@GetMapping("/board/insert")
	public String boardInsert(Model model)
	{
		model.addAttribute("main_html", "board/insert");
		return "main";
	}
	
	@PostMapping("/board/insert_ok")
	public String boardInsertOk(Board vo)
	{
		dao.save(vo);
		return "redirect:/board/list";
	}
	
	@GetMapping("/board/detail")
	public String boardDetail(int no, Model model)
	{
		Board vo=dao.findByNo(no);
		vo.setHit(vo.getHit()+1);
		dao.save(vo); // insert, update를 동시에 처리
		// 조회수 증가
		vo=dao.findByNo(no);
		model.addAttribute("vo", vo);
		model.addAttribute("main_html", "board/detail");
		return "board/detail";
		// return "main";
	}
	
	// 상세보기
	// 수정
	// 실제 수정 => redirect => detail
	// 삭제
	// 실제 삭제 => redirect => list
}
