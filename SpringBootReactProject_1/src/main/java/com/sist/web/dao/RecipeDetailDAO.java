package com.sist.web.dao;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sist.web.entity.*;

public interface RecipeDetailDAO extends JpaRepository<Recipedetail, Integer> {
	public Recipedetail findByNo(int no);
}
