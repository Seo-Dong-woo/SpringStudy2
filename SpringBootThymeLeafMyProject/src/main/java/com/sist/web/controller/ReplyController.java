package com.sist.web.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.web.dao.*;
import com.sist.web.entity.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class ReplyController {
	@Autowired
	private ReplyDAO dao;
	
	@PostMapping("/reply/insert")
	public String replyInsert(Reply vo, HttpSession session, RedirectAttributes ra)
	{
		String id=(String)session.getAttribute("id");
		String name=(String)session.getAttribute("name");
		vo.setId(id);
		vo.setName(name);
		dao.save(vo); // Insert
		ra.addAttribute("no", vo.getNo());
		return "redirect:/gym/detail";
	}
	
	@GetMapping("/reply/delete")
	public String replyDelete(int no, int rno, RedirectAttributes ra)
	{
		Reply vo=dao.findByRno(rno);
		dao.delete(vo);
		ra.addAttribute("no", no);
		return "redirect:/gym/detail";
	}
	
	@PostMapping("reply/update")
	public String replyUpdate(Reply vo, RedirectAttributes ra)
	{
		dao.save(vo);
		ra.addAttribute("no", vo.getNo());
		return "redirect:/gym/detail";
	}
}
