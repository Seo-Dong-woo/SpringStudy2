package com.sist.web;

import java.security.Principal;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.service.*;
import com.sist.vo.*;

@Controller
public class ChatController {
	@Autowired
	private ReplyService service;
	
	@GetMapping("chat/chat.do")
	public String chat_chat(Model model)
	{
		//MemberVO vo=service.memberInfoData(p.getName());
		return "site/chat/chat"; // ViewResolver      tiles(x)
	}
}
