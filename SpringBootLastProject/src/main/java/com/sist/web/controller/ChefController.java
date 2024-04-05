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
public class ChefController {
	@Autowired
	private ChefService cService;
	
	@GetMapping("/chef/list_react")
	public Map chefListData(int page)
	{
		Map map=new HashMap();
		int rowSize=20;
		int start=(rowSize*page)-rowSize;
		List<Chef> list=cService.chefListData(start);
		int count=(int)cService.count();
		int totalpage=(int)(Math.ceil(count/20.0));
		
		map.put("chef_list", list);
		map.put("totalpage", totalpage);
		
		return map;
	}
}
