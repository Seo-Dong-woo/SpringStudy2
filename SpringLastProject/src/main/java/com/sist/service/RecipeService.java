package com.sist.service;

import java.util.*;

import com.sist.vo.*;

public interface RecipeService {
	public List<ChefVO> chefHome6();
	public List<RecipeVO> recipeHome6();
	public int recipeCount();
	public List<RecipeVO> recipeListData(int start, int end);
	public int recipeTotalPage();
	public List<ChefVO> chefListData(int start, int end);
	public int chefTotalPage();
	public List<RecipeVO> chefDetailData(Map map);
	public int chefDetailTotalPage(int cno);
	public List<RecipeVO> chefDetailFindData(Map map);
	public int chefDetailFindTotalPage(Map map);
	public RecipeDetailVO recipeDetailData(int no);
	public List<GoodsVO> recipeGoodsData(String goods_name);
}
