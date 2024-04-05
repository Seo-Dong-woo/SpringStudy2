package com.sist.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.FoodMapper;
import com.sist.vo.FoodVO;

// 스프링에 의해 메모리 할당 => 필요 시마다 사용 가능 => 클래스 관리자
// 클래스: 컴포넌트, => 여러 개 묶어서 관리(컨테이너) ==> 컨테이너
// Vue.createApp: 컨테이너, components
/*
 *   컴퓨터
 *     CPU/하드디스크 ... 메모리 => 컴포넌트
 *     --------------------
 *       조립: 메인보드(스프링)
 */
@Repository
public class FoodDAO {
	@Autowired
	private FoodMapper mapper; // new FoodMapper()
	
	public List<FoodVO> foodsListData(int start, int end)
	{
		return mapper.foodsListData(start, end);
	}
	
	public int foodTotalPage()
	{
		return mapper.foodTotalPage();
	}
}
