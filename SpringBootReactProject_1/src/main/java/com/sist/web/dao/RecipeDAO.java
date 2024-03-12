package com.sist.web.dao;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.entity.*;

public interface RecipeDAO extends JpaRepository<Recipe, Integer> {
	@Query(value = "SELECT * FROM recipe2 "
			+ "ORDER BY no DESC LIMIT :start,12", nativeQuery = true)
	public List<Recipe> recipeListData(@Param("start") int start);
	
	// count() => count(*)
	// save() => insert/update
	// delete() => delete 
}
