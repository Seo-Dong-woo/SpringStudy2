package com.sist.web.dao;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sist.web.entity.Music;

public interface MusicDAO extends JpaRepository<Music, Integer> {
	@Query(value = "SELECT * FROM music ORDER BY mno ASC", nativeQuery = true)
	public List<Music> musicListData();
	
}
