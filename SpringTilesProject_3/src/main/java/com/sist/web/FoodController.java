package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FoodController {
	@GetMapping("main/main.do")
	public String main_main()
	{
		return "main"; // tiles에 main을 가져옴(안에 있는 header와 content를 include)
	}
}
