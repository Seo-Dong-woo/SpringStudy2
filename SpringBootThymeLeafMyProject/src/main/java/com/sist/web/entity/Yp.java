package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/*
 *  NO int 
	TYPE int 
	TITLE text 
	ADDRESS text 
	TIME text 
	POSTER text 
	PHONE text 
	HIT int 
	JJIMCOUNT int
 */
@Entity(name = "sportsreserve")
@Data
public class Yp {
	@Id
	private int no;
	private int hit, jjimcount;
	private String type, title, address, time, poster, phone;
}
