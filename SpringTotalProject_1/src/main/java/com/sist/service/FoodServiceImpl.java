package com.sist.service;
// => 의존성이 낮은 프로그램
// => 기능 설정 => 구현한 모든 클래스가 에러 발생 => 구현된 메소드를 만들 수 있음
// default => 추상클래스가 사라지고 있음
/*
 *   Web
 *     Controller
 *     1. DispatcherServlet 구동 ==> tomcat에 구동 요청 => web.xml
 *        1) 업체
 *        	 1. 문서 작업 => 소스 분석
 *        	    web.xml => server.xml => application.xml => 해당 클래스 찾기...
 *     ====================================================================
 *     Model => BackEnd
 *     2. 요청 => .do
 *     3. 해당 요청 처리 메소드 찾기 => @GetMapping/@PostMapping
 *        => HandlerMappping
 *     4. 처리된 데이터를 JSP로 찾아서 전송
 *        => ViewResolver
 *     ====================================================================
 *     View => FrontEnd
 *     5. 전송 받은 값을 화면에 출력
 *     ====================================================================
 *     Model/Controller/View에 역할
 */
public interface FoodServiceImpl {

}
