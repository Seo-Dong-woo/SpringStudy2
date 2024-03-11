package com.sist.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.entity.Board;
import com.sist.web.entity.BoardVO;

public interface BoardDAO extends JpaRepository<Board, Integer> {
	// => insert, update, delete
	// 상세보기
	public Board findByNo(int no);
	@Query(value = "SELECT * FROM myboard ORDER BY no DESC "
			+ "LIMIT :start, 10", nativeQuery = true)
	public List<BoardVO> boardListData(@Param("start") int start);
}
