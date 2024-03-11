package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
/*
 *  NO int 
	TITLE text 
	ADDRESS text 
	TIME text 
	POSTER text 
	PHONE text 
	HIT int 
	JJIMCOUNT int 
	LIKECOUNT int
 */
@Entity
@Data
public class Gym {
	@Id
	private int no;
	private String title;
	private String address;
	private String time;
	private String poster;
	private String phone;
	private int hit;
}
