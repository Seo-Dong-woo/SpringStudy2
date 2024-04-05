package com.sist.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.vo.*;
import com.sist.dao.*;

@Service
public class ReserveServiceImpl implements ReserveService {
	@Autowired
	private ReserveDAO rDao;
	
	@Override
	public List<FoodVO> foodReserveData(String type) {
		// TODO Auto-generated method stub
		return rDao.foodReserveData(type);
	}

	@Override
	public void foodReserveInsert(ReserveVO vo) {
		// TODO Auto-generated method stub
		rDao.foodReserveInsert(vo);
	}

	@Override
	public List<ReserveVO> reserveMypageData(String userId) {
		// TODO Auto-generated method stub
		return rDao.reserveMypageData(userId);
	}

	@Override
	public void reserveCancel(int rno) {
		// TODO Auto-generated method stub
		rDao.reserveCancel(rno);
	}

	@Override
	public List<ReserveVO> reserveAdminpageData() {
		// TODO Auto-generated method stub
		return rDao.reserveAdminpageData();
	}

	@Override
	public void reserveOk(int rno) {
		// TODO Auto-generated method stub
		rDao.reserveOk(rno);
	}

	@Override
	public ReserveVO reserveInfoData(int rno) {
		// TODO Auto-generated method stub
		return rDao.reserveInfoData(rno);
	}

}
