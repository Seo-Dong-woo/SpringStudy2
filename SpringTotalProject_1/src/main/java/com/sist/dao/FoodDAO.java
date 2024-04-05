package com.sist.dao;

import org.springframework.stereotype.Repository;

@Repository // 저장소 => 테이블 한 개 연결 시 주로 사용
// 관련된 DAO 여러 개 통합 => BI @Service
// => Controller/Service
// => 전송        처리목적
/*
 *   JDBC =====> DBCP =====> ORM
 *   						 MyBatis
 *   						 Hibernate ===== DataSet(자동 SQL) =====> JPA
 *   
 *   => Oracle/MySQL 연결할 줄 알아야함
 */
public class FoodDAO {

}
