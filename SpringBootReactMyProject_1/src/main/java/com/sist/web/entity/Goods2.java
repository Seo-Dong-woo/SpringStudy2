package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 *  CNO int 
	GNO int 
	TITLE text 
	POSTER text 
	SELLING_PRICE int 
	ORIGINAL_PRICE int 
	MILEAGE int 
	BRAND text 
	DELIVERY_PRICE text 
	AFTER_SERVICE text 
	DETAIL_POSTER text 
	HIT int
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Goods2 {
	@Id
	private int gno;
	private int cno, selling_price, original_price, mileagel, hit;
	private String title, poster, brand, delivery_price, after_service, detail_poster;
}
