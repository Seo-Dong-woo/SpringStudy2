package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/*
 *   객체지향 프로그램
 *   => 특성: 오버라이딩/오버로딩
 *           추상클래스/인터페이스
 *           생성자의 역할
 *           객체란?
 *           
 *   => 예외처리: 예외처리 종류, 예외처리의 시점
 *   => 컬렉션: List/Set/Map
 *            제네릭스
 *   => 쓰레드: 동기화/비동기화
 *   -------------------------------
 *   오라클: JOIN/SUBQUERY, 데이터형(BLOB,BFILE), PROCEDURE/TRIGGER
 *   JSP: GET/POST, session/cookie, MVC구조
 *        SpringMVC를 직접 구현...
 *        => 포트폴리오 => 목표/성과
 *   Spring/Spring-Boot: 차이점, AOP, ORM, DI, Transaction, WEB(MVC)
 *     ===> WebChat ===> 관리(세션 or 쿠키)
 *   AWS => EC2, S3, 로드밸런스 => 리눅스 명령어
 *   HTML/CSS => 시멘텍 => CSS적용 순위, 2/8
 *   JavaScript => Ajax/VueJs/React/Redux
 *              => 호이스팅, 클로저
 *              => ES6에서 추가
 *                 let, const =>
 *                 React/Vue의 차이점
 *   => 정의
 *   => 부연(프로젝트 인용)
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Recipedetail {
	@Id
	private int no;
	private String poster, title, chef, chef_poster, chef_profile, info1, info2, info3, 
	content, foodmake, stuff;
}
