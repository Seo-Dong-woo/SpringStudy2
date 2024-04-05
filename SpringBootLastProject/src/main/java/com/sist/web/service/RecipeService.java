package com.sist.web.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.entity.Recipe;

public interface RecipeService extends JpaRepository<Recipe, Integer> {
	@Query(value = "SELECT * FROM recipe2 "
			+ "ORDER BY no ASC LIMIT 0, 5", nativeQuery = true)
	public List<Recipe> recipeMainList();
	
	@Query(value = "SELECT * FROM recipe2 "
			+ "ORDER BY no ASC LIMIT :start,12", nativeQuery = true)
	public List<Recipe> recipeListData(@Param("start") int start);
	
	@Query(value = "SELECT * FROM recipe2 "
			+ "WHERE chef LIKE CONCAT('%',:chef,'%') "
			+ "LIMIT :start,12", nativeQuery = true)
	public List<Recipe> chefRecipeList(@Param("chef") String chef, @Param("start") int start);
	
	@Query(value = "SELECT CEIL(COUNT(*)/12.0) FROM recipe2 "
			+ "WHERE chef LIKE CONCAT('%',:chef,'%')", nativeQuery = true)
	public int chefTotalPage(@Param("chef") String chef);
}
