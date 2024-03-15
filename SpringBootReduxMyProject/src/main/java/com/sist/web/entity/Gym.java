package com.sist.web.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
@Getter
@Setter
@NoArgsConstructor
public class Gym {
	@Id
	private int no;
	@Column(insertable = true, updatable = false)
	private String title;
	@Column(insertable = true, updatable = false)
	private String address;
	@Column(insertable = true, updatable = false)
	private String time;
	@Column(insertable = true, updatable = false)
	private String poster;
	@Column(insertable = true, updatable = false)
	private String phone;
	@Column(insertable = true, updatable = true)
	private int hit;
}
