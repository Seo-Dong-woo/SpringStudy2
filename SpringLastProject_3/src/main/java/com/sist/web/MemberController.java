package com.sist.web;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.manager.MailManager;
import com.sist.service.*;
import com.sist.vo.*;

@Controller
public class MemberController {
	// security => 4, 5(반드시 비밀번호 암호화)
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private MemberService mService;
	
	@Autowired
	private MailManager mm;
	
	@GetMapping("member/join.do")
	public String member_join()
	{
		return "member/join";
	}
	
	@PostMapping("member/join_ok.do")
	public String member_join_ok(MemberVO vo)
	{
		System.out.println(encoder.encode("hong1234"));
		vo.setPhone(vo.getPhone1() + "-" + vo.getPhone2());
		String enPwd=encoder.encode(vo.getUserPwd()); // 암호화
		vo.setUserPwd(enPwd);
		mService.memberInsert(vo);
		mService.memberAuthorityInsert(vo.getUserId());
		mm.mailMemberSender(vo);
		return "main";
	}
	
	@RequestMapping("member/login.do") // RequestMapping은 get, post 둘 다 처리 가능
	public String member_login()
	{
		return "member/login";
	}
	
	/*@GetMapping("member/logout.do")
	public String member_logout(HttpSession session)
	{
		session.invalidate();
		return "redirect:../main/main.do";
	}*/

}
