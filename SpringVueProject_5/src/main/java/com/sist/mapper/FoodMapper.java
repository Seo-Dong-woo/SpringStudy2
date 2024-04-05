package com.sist.mapper;
// MyBatis에서 자동 구현
// => 메소드를 만들면 => 자동 SQL 생성
// findByNo(int no) => WHERE no=? findByNameLike => WHERE name LIKE
// insert update delete findAll(Page page)

import java.util.*;

import org.apache.ibatis.annotations.Select;
/*
 *   SELECT fno, name, poster FROM food_menu_house
 *   ORDER BY fno ASC
 *   LIMIT #{start}, 20
 */
import com.sist.dao.*;

public interface FoodMapper {
	@Select("SELECT fno, name, poster, num "
			+ "FROM (SELECT fno, name, poster, rownum as num "
			+ "FROM (SELECT fno, name, poster "
			+ "FROM food_menu_house ORDER BY fno ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<FoodVO> foodListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/20.0) FROM food_menu_house")
	public int foodTotalPage();
}
