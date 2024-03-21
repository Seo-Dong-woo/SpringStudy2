package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 *  JWNO int 
	TITLE text 
	SUBTITLE text 
	POSTER text 
	PRICE int 
	OPTION1 text 
	OPTION2 text 
	JJIMCOUNT int 
	HIT int
 */
@Entity(name = "jmwshop")
@Getter
@Setter
@NoArgsConstructor
public class Goods_Women_Clothes {
	@Id
	private int jwno;
	private int price, hit;
	private String title, subtitle, poster, option1, option2; 
}
