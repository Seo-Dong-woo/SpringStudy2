package com.sist.service;

import java.util.*;

import com.sist.vo.*;

public interface ReserveService {
	public List<FoodVO> foodReserveData(String type);
	public void foodReserveInsert(ReserveVO vo);
	public List<ReserveVO> reserveMypageData(String userId);
	public void reserveCancel(int rno);
	public List<ReserveVO> reserveAdminpageData();
	public void reserveOk(int rno);
	public ReserveVO reserveInfoData(int rno);
}
