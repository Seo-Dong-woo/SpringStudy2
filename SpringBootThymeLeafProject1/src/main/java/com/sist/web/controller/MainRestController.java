package com.sist.web.controller;

import java.util.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainRestController {
	@GetMapping("/food")
	public Map food_list() throws Exception {
		Map map=new HashMap();
		map.put("name", "홍길동");
		map.put("sex", "남자");
		return map; // 자동으로 JSON 됨  objectMapper 사용할 필요 없음
	}
}
