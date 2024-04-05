package com.sist.web.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
// ORM => 데이터베이스 컬럼 <===> 객체의 멤버변수 매칭 => 자동 SQL
@Entity(name = "food_house")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Food {
	@Id
	private int fno;
	@Column(insertable = true, updatable = false)
	private int jjimcount;
	@Column(insertable = true, updatable = false)
	private int likecount; 
	@Column(insertable = true, updatable = true)
	private int hit;
	@Column(insertable = true, updatable = false)
	private String poster;
	@Column(insertable = true, updatable = false)
	private String name;
	@Column(insertable = true, updatable = false)
	private String type;
	@Column(insertable = true, updatable = false)
	private String address;
	@Column(insertable = true, updatable = false)
	private String phone;
	@Column(insertable = true, updatable = false)
	private String theme;
	@Column(insertable = true, updatable = false)
	private String price;
	@Column(insertable = true, updatable = false)
	private String time;
	@Column(insertable = true, updatable = false)
	private String seat;
	@Column(insertable = true, updatable = false)
	private String content;
	@Column(insertable = true, updatable = false)
	private double score;
}
