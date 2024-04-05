package com.sist.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.dao.*;
import com.sist.vo.*;

@Service
public class FoodServiceImpl implements FoodService {
	@Autowired
	private FoodDAO dao;
	
	@Autowired
	private MemberDAO mDao;
	
	@Autowired
	private ReplyDAO rDao;

	@Override
	public List<FoodVO> foodListdata(int start, int end) {
		// TODO Auto-generated method stub
		return dao.foodListdata(start, end);
	}

	@Override
	public int foodTotalPage() {
		// TODO Auto-generated method stub
		return dao.foodTotalPage();
	}

	@Override
	public FoodVO foodDetailData(int fno) {
		// TODO Auto-generated method stub
		return dao.foodDetailData(fno);
	}

	@Override
	public MemberVO isLogin(String id, String pwd) {
		// TODO Auto-generated method stub
		return mDao.isLogin(id, pwd);
	}

	@Override
	public List<ReplyVO> replyListdata(int fno) {
		// TODO Auto-generated method stub
		return rDao.replyListdata(fno);
	}

	@Override
	public void replyInsert(ReplyVO vo) {
		// TODO Auto-generated method stub
		rDao.replyInsert(vo);
	}

	@Override
	public void replyDelete(int rno) {
		// TODO Auto-generated method stub
		rDao.replyDelete(rno);
	}

	@Override
	public void replyUpdate(ReplyVO vo) {
		// TODO Auto-generated method stub
		rDao.replyUpdate(vo);
	}
}
