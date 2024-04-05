package com.sist.web.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "jpaboard")
@DynamicUpdate
// update시 제외
@Data
// 추가적인 변수가 존재하면 안됨 => insert.update
public class BoardEntity {
	@Id
	private int no;
	private String name, subject, content;
	@Column(insertable = true, updatable = false) // 비밀번호는 insert는 하되, update는 하지 말라
	private String pwd;
	@Column(insertable = true, updatable = false)
	private int hit;
	@Column(insertable = true, updatable = false)
	private String regdate;
	
	@PrePersist
	public void regdate() {
		this.regdate=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
}
