package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Getter
@Setter
@NoArgsConstructor
public class Goods_Men_Clothes {
	@Id
	private int jmno;
	private int price, hit;
	private String title, subtitle, poster, option1, option2; 
}
