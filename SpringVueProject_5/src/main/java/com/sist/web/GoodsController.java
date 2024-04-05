package com.sist.web;

import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sist.dao.*;

@Controller
public class GoodsController {
	@Autowired
	private GoodsDAO dao;
	
	@GetMapping("goods/all.do")
	public String goods_all()
	{
		return "goods/all";
	}
	
	@GetMapping(value = "goods/all_vue.do", produces = "text/plain; charset=UTF-8")
	@ResponseBody
	public String goods_all_vue(int page)
	{
		int rowSize=12;
		int start=(rowSize*page)-(rowSize-1);
		int end=rowSize*page;
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		List<GoodsVO> list=dao.goodsAllListData(map);
		int totalpage=dao.goodsTotalPage();
		final int BLOCK=10;
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		
		int i=0;
		JSONArray arr=new JSONArray(); // [] {}
		for(GoodsVO vo:list)
		{
			JSONObject obj=new JSONObject(); // VO를 담는 객체
			obj.put("no", vo.getNo());
			obj.put("goods_name", vo.getGoods_name());
			obj.put("goods_poster", vo.getGoods_poster()); // {"fno":1, "name":"aaa", "poster":"..jpg"}
			obj.put("goods_price", vo.getGoods_price());
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
}
