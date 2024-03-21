package com.sist.web.entity;
/*
 *  FNO int 
	NAME text 
	UNIT text 
	CAL text 
	NUT text 
	EXER text
 */
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "food_cal")
@Getter
@Setter
@NoArgsConstructor
public class Food {
	@Id
	private int fno;
	private String name, unit, cal, nut, exer;
}
