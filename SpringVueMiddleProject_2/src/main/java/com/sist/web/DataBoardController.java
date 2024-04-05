package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DataBoardController {
	@GetMapping("databoard/list.do")
	public String databoard_list()
	{
		return "databoard/list";
	}
	
	@GetMapping("databoard/insert.do")
	public String databoard_insert()
	{
		return "databoard/insert";
	}
	
	@GetMapping("databoard/detail.do")
	public String databoard_detail(int no, Model model)
	{
		/*
		 *   no, name, subject, content, TO_CHAR(regdate, 'YYYY-MM-DD') as dbday, hit, filename, filesize, filecount
		 *   
		 *   response.data={"no":1, "name":"", "subject":"", ...}
		 */
		model.addAttribute("no", no);
		return "databoard/detail";
	}
}
