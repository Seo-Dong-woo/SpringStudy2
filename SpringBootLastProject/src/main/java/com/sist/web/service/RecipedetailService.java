package com.sist.web.service;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sist.web.entity.*;

public interface RecipedetailService extends JpaRepository<Recipedetail, Integer> {
	public Recipedetail findByNo(int no);
	
	
}
