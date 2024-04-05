package com.sist.vo;

import java.util.*;

import lombok.Data;
/*
 *  RNO        NOT NULL NUMBER        
	FNO                 NUMBER        
	USERID              VARCHAR2(20)  
	RDATE      NOT NULL VARCHAR2(100) 
	RTIME      NOT NULL VARCHAR2(20)  
	RINWON     NOT NULL VARCHAR2(20)  
	REGDATE             DATE          
	RESERVE_OK          NUMBER     
 */
@Data
public class ReserveVO {
	private int rno, fno, reserve_ok;
	private String userId, rDate, rTime, rInwon, dbday;
	private Date regdate;
	private FoodVO fvo=new FoodVO(); // 조인
}
