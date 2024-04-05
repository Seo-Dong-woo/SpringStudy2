package com.sist.web.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.entity.*;
import com.sist.web.service.*;

@RestController
@CrossOrigin(origins = "*")
public class BoardController {
	@Autowired
	private BoardService bService;
	
	@GetMapping("/board/list_react")
	public Map board_list(int page)
	{
		Map map=new HashMap();
		int rowSize=10;
		int start=(rowSize*page)-rowSize;
		List<Board> list=bService.boardListData(start);
		int count=(int)bService.count();
		int totalpage=(int)(Math.ceil(count/10.0));
		
		map.put("board_list", list);
		map.put("totalpage", totalpage);
		
		return map;
	}
	
	@PostMapping("/board/insert_react")
	public String board_insert(Board vo)
	{
		String result="";
		try
		{
			result="yes";
			bService.save(vo);
		}catch(Exception ex)
		{
			//System.out.println(ex.getMessage());
			result="no";
		}
		return result;
	}
	
	@GetMapping("/board/detail_react")
	public Board boardDetail(int no)
	{
		Board vo=bService.findByNo(no);
		vo.setHit(vo.getHit()+1);
		bService.save(vo);
		vo=bService.findByNo(no);
		return vo;
	}
	
	@GetMapping("/board/update_react")
	public Board boardUpdate(int no)
	{
		Board vo=bService.findByNo(no);
		return vo;
	}
	
	@PostMapping("/board/update_ok_react")
	public String boardUpdateOk(Board vo)
	{
		Board dbVO=bService.findByNo(vo.getNo());
		String result="";
		if(vo.getPwd().equals(dbVO.getPwd()))
		{
			result="yes";
			vo.setHit(dbVO.getHit()); // 기존에 hit를 넣어줘야 함. 안넣어주면 0으로 변함
			bService.save(vo);
		}
		else
		{
			result="no";
		}
		return result;
	}
	
	@PostMapping("/board/delete_react")
	public String boardDelete(int no, String pwd)
	{
		String result="";
		Board vo=bService.findByNo(no);
		if(vo.getPwd().equals(pwd))
		{
			result="yes";
			bService.delete(vo);
		}
		else
		{
			result="no";
		}
		return result;
	}
}
