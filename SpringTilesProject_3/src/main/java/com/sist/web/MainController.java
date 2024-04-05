package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@GetMapping("chat/chat.do")
	public String chat_chat()
	{
		return "site/chat/chat";
	}
}
