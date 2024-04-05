package com.sist.web;

import java.util.*;

import com.sist.*;
import com.sist.dao.FoodDAO;
import com.sist.dao.FoodVO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FoodController {
	@Autowired
	private FoodDAO dao;
	
	@GetMapping("food/list.do")
	public String food_list()
	{
		return "food/list";
	}
	
	@GetMapping(value = "food/list_vue.do", produces = "text/plain; charset=UTF-8")
	@ResponseBody
	// => @RestController
	public String food_list_vue(int page)
	{
		// [{fno:1, poster:'', name:"("}, {fno:1, poster:'', name:''}...{startPage:1...}]
		// VueJS => 연결 전에 초기값을 설정
		/*
		 *   data(){
		 *       return{
		 *           page:1
		 *       }
		 *   }
		 */
		int rowSize=20;
		int start=(rowSize*page)-(rowSize-1);
		int end=rowSize*page;
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		// "WHERE num BETWEEN #{start} AND #{end}" 키 값이 같아야 함
		List<FoodVO> list=dao.foodListData(map);
		int totalpage=dao.foodTotalPage();
		final int BLOCK=10;
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		
		int i=0;
		JSONArray arr=new JSONArray(); // [] {}
		for(FoodVO vo:list)
		{
			JSONObject obj=new JSONObject(); // VO를 담는 객체
			obj.put("fno", vo.getFno());
			obj.put("name", vo.getName());
			obj.put("poster", vo.getPoster()); // {"fno":1, "name":"aaa", "poster":"..jpg"}
			if(i==0)
			{
				obj.put("curpage", page);
				obj.put("totalpage", totalpage);
				obj.put("startPage", startPage);
				obj.put("endPage", endPage);
			}
			arr.add(obj);
			i++;
		}
		return arr.toJSONString();
	}
	
	@GetMapping("food/page_vue.do")
	@ResponseBody
	public String food_page_vue(int page)
	{
		// startPage endPage curpage totalpage
		return "";
	}
}
