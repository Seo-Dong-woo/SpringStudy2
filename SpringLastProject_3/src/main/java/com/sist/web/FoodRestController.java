package com.sist.web;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.manager.FoodRecommandManager;
import com.sist.service.*;
import com.sist.vo.*;

@RestController
@RequestMapping("food/")
public class FoodRestController {
	@Autowired
	private FoodService service;
	
	@Autowired
	private FoodRecommandManager mgr;
	
	@GetMapping(value = "find_vue.do", produces = "text/plain; charset=UTF-8")
	public String food_find(int page, String fd) throws Exception
	{
		int rowSize=20;
		int start=(rowSize*page)-(rowSize-1);
		int end=rowSize*page;
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("address", fd);
		List<FoodVO> list=service.foodFindData(map);
		
		// JSON 변경
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);		
		return json;
	}
	
	@GetMapping(value = "page_vue.do", produces = "text/plain; charset=UTF-8")
	public String food_page(int page, String fd) throws Exception
	{
		final int BLOCK=10;
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		Map map=new HashMap();
		map.put("address", fd);
		int totalpage=service.foodFindCount(map);
		if(endPage>totalpage)
			endPage=totalpage;
		
		map=new HashMap();
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(map);
		return json;
	}
	
	@GetMapping(value = "detail_vue.do", produces = "text/plain; charset=UTF-8")
	public String food_detail(int fno) throws Exception
	{
		FoodVO vo=service.foodDetailData(fno);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
		return json;
	}
	
	@GetMapping(value = "food_list_vue.do", produces = "text/plain; charset=UTF-8")
	public String food_list(int page) throws Exception
	{
		Map map=new HashMap();
		map.put("start", (20*page)-19);
		map.put("end", 20*page);
		
		List<FoodVO> list=service.foodListData(map);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		return json;
	}
	
	@GetMapping(value = "food_cookie_vue.do", produces = "text/plain; charset=UTF-8")
	public String food_cookie(HttpServletRequest request) throws Exception
	{
		Cookie[] cookies=request.getCookies();
		List<FoodVO> list=new ArrayList<FoodVO>();
		int k=0;
		if(cookies!=null)
		{
			for(int i=cookies.length-1;i>=0;i--)
			{
				if(k<9)
				{
					// new Cooie("food_" + fno, String.valueOf(fno))
					//           ======getName() ============getValue()
					if(cookies[i].getName().startsWith("food_"))
					{
						String fno=cookies[i].getValue();
						FoodVO vo=service.foodDetailData(Integer.parseInt(fno));
						list.add(vo);
					}
					k++;
				}
			}
		}
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		return json;
	}
	
	@GetMapping(value = "food_page_vue.do", produces = "text/plain; charset=UTF-8")
	public String food_list_page(int page) throws Exception
	{
		final int BLOCK=10;
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		int totalpage=service.foodListCount();
		if(endPage>totalpage)
			endPage=totalpage;
		
		Map map=new HashMap();
		map=new HashMap();
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(map);
		return json;
	}
	
	@GetMapping(value = "food_detail_vue.do", produces = "text/plain; charset=UTF-8")
	public String food_detail_vue(int fno) throws Exception
	{
		FoodVO vo=service.foodListDetailData(fno);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
		return json; // response.data
	}
	
	@GetMapping(value = "food_recommand_sub.do", produces = "text/plain; charset=UTF-8")
	public String food_recommand(int no) throws Exception
	{
		String[] menu1= {
				"퇴근길", "휴식", "휴가", "여행", "드라이브", 
				"산책", "운동"
		};
		String[] menu2= {
				"기분전환", "외로움", "슬픔", 
				"이별", "지침", "설렘", "위로", "스트레스", 
				"그리움", "우울", "행복", "불안", "기쁨"
		};
		String[] menu3= {
				"밝은", "신나는", "편안한", "따뜻한", 
				"달콤한", "시원한"
		};
		String[] menu4= {
				"봄", "여름", "가을", "겨울", "맑은날", 
				"추운날", "흐린날", "비오는날", "더운날", "눈오는날"
		};
		ObjectMapper mapper=new ObjectMapper();
		String json="";
		if(no==1)
		{
			json=mapper.writeValueAsString(menu1);
		}
		else if(no==2)
		{
			json=mapper.writeValueAsString(menu2);
		}
		else if(no==3)
		{
			json=mapper.writeValueAsString(menu3);
		}
		else if(no==4)
		{
			json=mapper.writeValueAsString(menu4);
		}
		return json;
	}
	
	@GetMapping(value="food_recommand_data.do",produces = "text/plain;charset=UTF-8")
	public String food_recommand_data(String fd) throws Exception
	{
		List<String> list=mgr.newsFind(fd);
		List<String> fList=service.foodAllData();
		   
		/*
		 *   1. 단어 => constains()
		 *   2. 기호 : * (0이상)      맛있다 , 맛있고 , 맛있는  => 맛*
		 *            + (1이상)      맛+
		 *            ? (0,1)       
		 *            | (선택) 
		 *            . 임의의 한글자  맛.
		 *            => 실제 기호 : \\+
		 *            [] => 범위 
		 *               숫자 [0-3] => [0-9] 
		 *               영문 [A-Z] [a-z] => [A-Za-z]
		 *               한글 [가-힣]
		 *            {} => 갯수 
		 *            {3} , {1,3} 
		 *            [0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3} : IP
		 *            ^ : 시작 
		 *            ^[가-힣] => 한글 시작 
		 *            [^가-힣] => 한글을 제외하고
		 *            $ : 끝 
		 *            [가-힣]$ => 한글로 끝난 ...
		 *            문자의 형태 
		 */
		   
		// 초기화 => 정규식 
		   
		List<String> rList=new ArrayList<String>();
		int[] count=new int[fList.size()];
		for(String s:list) // 추천 
		{
			//System.out.println(s);
			for(int i=0;i<fList.size();i++)
			{
				 if(s.contains(fList.get(i)))
				 {
					 //System.out.println(fList.get(i));
					 rList.add(fList.get(i));
					 count[i]++;
				 }
					
			}
		}
		   
		Set set=new HashSet();
		for(int i=0;i<rList.size();i++)
		{
			set.add(rList.get(i)); // 중복제거 
		}
		   
		List<FoodVO> dList=new ArrayList<FoodVO>();
		Iterator iter=set.iterator();
		while(iter.hasNext())
		{
			String ss=iter.next().toString();
			List<FoodVO> vo=service.foodNameInfoData(ss);
			dList.add(vo.get(0));
		}
		   
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(dList);
		return json;
	}
	
	@GetMapping(value = "food_detail_recipe.do", produces = "text/plain; charset=UTF-8")
	public String food_detail_recipe(int fno) throws Exception
	{
		FoodVO vo=service.foodListDetailData(fno);
		System.out.println("type: " + vo.getType());
		String s=vo.getType().replace("/", "|");
		List<RecipeVO> list=service.foodRecipeData(s);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		return json;
	}
}
