package com.sist.web.temp;

import lombok.Data;

/*
 *  mno int AI PK 
	title varchar(200) 
	singer varchar(200) 
	album varchar(200) 
	poster varchar(260) 
	state varchar(30) 
	idcrement int
 */
@Data
public class MusicVO {
	private int mno, idcrement;
	private String title, singer, album, poster, state;
}
