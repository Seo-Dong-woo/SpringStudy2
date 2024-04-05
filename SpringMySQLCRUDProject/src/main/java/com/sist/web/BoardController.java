package com.sist.web;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sist.vo.*;
import com.sist.service.*;

@Controller
public class BoardController {
	@Autowired
	private BoardService bservice;
	
	@GetMapping("board/list.do")
	public String boardListData(String page, Model model)
	{
		return bservice.boardListData(page, model);
	}
	
	@GetMapping("board/insert.do")
	public String boardInsert()
	{
		return "board/insert";
	}
	
	@PostMapping("board/insert_ok.do")
	public String boardInsertOk(BoardVO vo)
	{
		return bservice.boardInsertOk(vo);
	}
	
	@GetMapping("board/detail.do")
	public String boardDetailData(int no, Model model)
	{
		return bservice.boardDetailData(no, model);
	}
	
	@GetMapping("board/update.do")
	public String boardUpdateData(int no, Model model)
	{
		return bservice.boardUpdateData(no, model);
	}
	
	@PostMapping("board/update_ok.do")
	public String boardUpdateOk(BoardVO vo, Model model)
	{
		return "";
	}
	
}
