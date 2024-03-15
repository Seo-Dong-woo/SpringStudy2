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
public class GymController {
	@Autowired
	private GymService gService;
	
	@GetMapping("/gym/list_react")
	public Map gym_list(int page)
	{
		int rowSize=12;
		int start=(rowSize*page)-rowSize;
		List<Gym> list=gService.gymListData(start);
		int count=(int)gService.count();
		int totalpage=(int)(Math.ceil(count)/12.0);
		
		Map map=new HashMap();
		map.put("gym_list", list);
		map.put("totalpage", totalpage);
		
		return map;
	}
	
	@GetMapping("/gym/find_react")
	public Map gym_find(int page, String address)
	{
		int rowSize=12;
		int start=(rowSize*page)-rowSize;
		List<Gym> list=gService.gymFindData(address, start);
		int totalpage=gService.gymFindTotalPage(address);
		
		Map map=new HashMap();
		map.put("find_list", list);
		map.put("totalpage", totalpage);
		
		return map;
	}
	
	@GetMapping("/gym/detail_react")
	public Gym gym_detail(int no)
	{
		Gym gym=gService.findByNo(no);
		gym.setHit(gym.getHit()+1); // 조회수 증가
		gService.save(gym); // 조회수 증가한 값을 저장
		gym=gService.findByNo(no); // 조회수 증가한 값을 포함한 entity를 다시 불러옴
		return gym;
	}
}
