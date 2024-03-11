package com.sist.web.dao;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.entity.*;

public interface ReplyDAO extends JpaRepository<Reply, Integer> {
	// 데이터 읽기
	@Query(value = "SELECT * FROM myreply WHERE no=:no "
			+ "ORDER BY no DESC", nativeQuery = true)
	public List<Reply> replyListData(@Param("no") int no);
	
	public Reply findByRno(int rno);
	
	// insert, update, delete
}
