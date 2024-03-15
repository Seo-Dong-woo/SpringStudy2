package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/*
 *  JMNO int 
	TITLE text 
	SUBTITLE text 
	POSTER text 
	PRICE int 
	OPTION1 text 
	OPTION2 text 
	JJIMCOUNT int 
	HIT int
 */
@Entity(name = "jmmshop")
@Data
public class Goods_Clothes {
	@Id
	private int jmno;
	private int price, hit;
	private String title, subtitle, poster, option1, option2; 
}
