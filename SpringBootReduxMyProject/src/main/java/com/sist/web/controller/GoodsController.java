package com.sist.web.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.entity.*;
import com.sist.web.service.*;

@RestController
@CrossOrigin(origins = "*")
public class GoodsController {
	@Autowired
	private GoodsService gsService;
	
	@GetMapping("/goods1/list_react")
	public Map goods1_list(int page)
	{
		int rowSize=12;
		int start=(rowSize*page)-rowSize;
		List<Goods_Clothes> list=gsService.goods1ListData(start);
		int count=(int)gsService.count();
		int totalpage=(int)(Math.ceil(count)/12.0);
		
		Map map=new HashMap();
		map.put("goods1_list", list);
		map.put("totalpage", totalpage);
		
		return map;
	}
}
