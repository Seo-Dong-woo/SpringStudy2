package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 *  MNO int 
	TITLE text 
	SUBTITLE text 
	POSTER text 
	LOC text 
	NUM text 
	ENVIR text 
	CATEGORY text 
	SEASON text 
	OPENCLOSE text 
	HOMEPAGE text 
	RESERVE text 
	FACILITY text 
	PIC1 text 
	PIC2 text 
	PIC3 text 
	EXPLAIN text 
	HIT int 
	PICC1 text 
	PICC2 text 
	PICC3 text 
	PICC4 text 
	PICC5 text 
	PICC6 text 
	PICC7 text 
	PICC8 text 
	PICC9 text 
	PICC10 text 
	PICC11 text 
	PICC12 text 
	PICC13 text 
	PICC14 text 
	PICC15 text 
	PICC16 text 
	PICC17 text 
	PICC18 text 
	PICC19 text 
	PICC20 text
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Camping {
	@Id
	private int mno;
	private int hit;
	private String title, subtitle, poster, loc, num, envir, category, season, openclose, homepage, reserve;
	private String facility, pic1, pic2, pic3, explain, picc1, picc2, picc3, picc4, picc5, picc6, picc7;
	private String picc8, picc9, picc10, picc11, picc12, picc13, picc14, picc15, picc16, picc17;
	private String picc18, picc19, picc20;
}
