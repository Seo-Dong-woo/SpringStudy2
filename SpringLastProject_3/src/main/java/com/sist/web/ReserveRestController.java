package com.sist.web;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.vo.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.manager.MailManager;
import com.sist.service.*;

@RestController
public class ReserveRestController {
	@Autowired
	private ReserveService rService;
	
	@Autowired
	private MailManager mm; // MailManager mm=new MailManager()
	
	@GetMapping(value = "reserve/food_list_vue.do", produces = "text/plain; charset=UTF-8")
	public String food_list(String type) throws Exception
	{
		List<FoodVO> list=rService.foodReserveData(type);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		return json;
	}
	
	@PostMapping(value = "reserve/reserve_ok.do", produces = "text/plain; charset=UTF-8")
	public String reserve_ok(ReserveVO vo, HttpSession session)
	{
		String result="no";
		try
		{
			vo.setUserId((String)session.getAttribute("userId"));
			/*System.out.println("fno: " + vo.getFno());
			System.out.println("data: " + vo.getRDate());
			System.out.println("time: " + vo.getRTime());
			System.out.println("inwon: " + vo.getRInwon());
			System.out.println("userId: " + vo.getUserId());*/
			rService.foodReserveInsert(vo);
			result="yes";
		}catch(Exception ex)
		{
			result="no";
		}
		return result;
	}
	
	@GetMapping(value = "reserve/mypage_list_vue.do", produces = "text/plain; charset=UTF-8")
	public String mypage_list(HttpSession session) throws Exception // 아이디를 불러와야 되기 때문에 세션
	{
		String userId=(String)session.getAttribute("userId");
		List<ReserveVO> list=rService.reserveMypageData(userId);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		return json;
	}
	
	@GetMapping(value = "reserve/reserve_cancel_vue.do", produces = "text/plain; charset=UTF-8")
	public String reserve_cancel(int rno)
	{
		String result="";
		try
		{
			result="yes";
			rService.reserveCancel(rno);
		}catch(Exception ex)
		{
			result="no";
		}
		return result;
	}
	
	@GetMapping(value = "reserve/reserve_admin_vue.do", produces = "text/plain; charset=UTF-8")
	public String reserve_admin() throws Exception
	{
		List<ReserveVO> list=rService.reserveAdminpageData();
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		return json;
	}
	
	@GetMapping(value = "reserve/reserve_ok_vue.do", produces = "text/plain; charset=UTF-8")
	public String reserve_ok(int rno)
	{
		String result="";
		try
		{
			result="yes";
			rService.reserveOk(rno);
			ReserveVO vo=rService.reserveInfoData(rno);
			mm.mailReserveSender(vo);
		}catch(Exception ex)
		{
			result="no";
		}
		return result;
	}
}
