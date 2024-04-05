package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GoodsController {
	@GetMapping("goods/all.do")
	public String goods_all()
	{
		return "goods/all";
	}
}
