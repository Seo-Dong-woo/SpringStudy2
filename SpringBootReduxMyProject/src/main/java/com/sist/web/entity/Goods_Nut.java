package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 *  GNO int 
	KNAME text 
	ENAME text 
	POSTER text 
	PRICE int 
	BRAND1 text 
	BRAND2 text 
	EATSIZE text 
	EATUSE text 
	CAUTION text 
	TYPE text 
	JJIM int
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Goods_Nut {
	@Id
	private int gno;
	private int price;
	private String kname, ename, poster, brand1, brand2, eatsize, eatuse, caution, type;
}
