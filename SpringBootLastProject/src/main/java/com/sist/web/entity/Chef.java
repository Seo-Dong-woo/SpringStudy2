package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
/*
 *  CNO int 
	CHEF text 
	POSTER text 
	MEM_CONT1 int 
	MEM_CONT2 int 
	MEM_CONT3 int 
	MEM_CONT7 int
 */
@Entity
@Data
public class Chef {
	@Id
	private int cno;
	private String chef, poster;
	private int mem_cont1, mem_cont2, mem_cont3, mem_cont7;

}
