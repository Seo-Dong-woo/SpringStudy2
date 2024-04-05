package com.sist.web;

import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
// 다른 언어와 통신(자바스크립트 == 자바)
//                      JSON
/*
 *   자바 => [], {}
 *   자바스크립트 : 객체(=> VO) => {}
 *              배열(=> List) => []
 *              ================== JSON(jackson) 자동 변환
 *              1) 사용법이 편함(소스 간걸) => 소스가 복잡함
 *              2) 가독성이 낮음         =>  가독성이 높음
 *              3) Spring-Boot
 *                 public List<FoodVO> foodListData
 *                 {
 *                     List<foodVO> list=dao.listData()
 *                     return list
 *                 }
 */
// => DI는 클래스와 클래스의 관계를 설정
import com.sist.service.FoodService;
import com.sist.vo.*;
@RestController // React/Ajax/Vue => NodeJs
public class FoodRestController {
	@Autowired
	private FoodService service;
	
	@GetMapping(value = "food/list_vue.do", produces = "text/plain; charset=UTF-8")
	public String food_list_vue(int page) throws Exception
	{
		int rowSize=12;
		int start=(rowSize*page)-(rowSize-1);
		int end=rowSize*page;
		
		List<FoodVO> list=service.foodsListData(start, end);
		
		/*JSONArray arr=new JSONArray(); // [] => List
		for(FoodVO vo:list)
		{
			JSONObject obj=new JSONObject(); // {fno:1, poster:'', name:'', ...} => VO
			obj.put("fno", vo.getFno());
			obj.put("name", vo.getName());
			obj.put("poster", vo.getPoster());
			arr.add(obj);
		}
		return arr.toJSONString();*/
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		return json;
	}
	
	@GetMapping(value = "food/page_vue.do", produces = "text/plain; charset=UTF-8")
	public String food_page_vue(int page) throws Exception
	{
		int totalpage=service.foodTotalPage();
		final int BLOCK=10;
		//            1~10 => 1, 11~20 => 11
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		
		Map map=new HashMap();
		map.put("curpage", page);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("totalpage", totalpage);
		
		// Map => {curpage:1, totalpage:20, startPage:1, endPage:10}
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(map);
		return json;
	}
}
