package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// JSP와 연결 => 화면 변경 => forward(request)/redirect(request를 초기화)
// "redirect:... .do"
// Vue/React => router, include
@Controller
public class FoodController {
	@GetMapping("food/list.do")
	public String food_list()
	{
		return "food/list";
	}
}
