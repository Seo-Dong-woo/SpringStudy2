package com.sist.vo;

import java.util.*;

import lombok.Data;
/*
 *  NO       NOT NULL NUMBER       
	RNO               NUMBER       
	USERID            VARCHAR2(20) 
	USERNAME NOT NULL VARCHAR2(51) 
	MSG      NOT NULL CLOB         
	REGDATE           DATE  
 */
@Data
public class ReplyVO {
	private int no, rno;
	private String userId, userName, msg, dbday;
	private Date regdate;
}
