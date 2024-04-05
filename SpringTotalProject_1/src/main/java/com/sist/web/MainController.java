package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.service.FoodService;

/*
 *   @Controller/@RestController => 면접(80%)
 *   =======================================
 *   => Spring/Spring MVC/Spring-Boot의 차이점
 *   => SpringFramework => 생명 주기
 *   => Bean
 *   => Interceptor/Filter의 차이점
 *   				----------- WebContext ----------------
 *   client -----   |
 *   		-----   |------ SpringContext --------------
 *   				| Filter --- DispatcherServlet
 *   				|-------------------------
 *   				---------------------------------------
 *   		요청을 전달 받으면 전/후 => url 패턴에 맞게 모든 요청에 대한 부가적인 작업
 *   		  => web.xml <filter>
 *   		  ex) 보안 처리(인가), 한글 변환
 *   
 *   	Interceptor
 *   	=> 중간에 처리
 *   	   client ===== DispatcherServlet ===== HandlerMapping ===== ViewResolver ===== JSP
 *   										|					 |					|
 *   									 preHandle()		  postHandle()		 afterCompletion()	
 *   									  요청처리 전				요청처리 후 데이터 전송		완료
 *   										| 자동 로그인
 *   	Filter
 *   	  => 용도
 *   		 공통된 보안 및 인증/인가 관련 작업
 *   		 요청에 대한 로깅/검사
 *   		 이미지/데이터 압축, 문자열 인코딩
 *   		 ------------- docker
 *   		 => 스프링과 무관하게 전역으로 처리 하는 작업
 *   
 *   	Interceptor
 *   	  => 용도
 *   		 세부적인 보안 및 인가/인증 => 자동 로그인 (remember-re)
 *   		 API 호출 시에 대한 로깅/감사
 *   		 Controller로 넘겨주는 데이터 가공
 *    
 *   => Ioc/DI
 *   => Container의 종류/Container란
 *   => VO/DTO/Entity ===> 100%
 *   => MVC 구조에 대한 설명
 *   	=> servlet/jsp
 *   => Spring MVC에서 Http요청이 들어왔을 경우 흐름에 대해 설명
 *   => 기타
 *   	=> Spring-Security의 구조
 *   	*** AOP 이용하는 방법
 *   	모든 프로그램은
 *   		= 핵심
 *   		= 공통: getConnection, disConnection => 자동 호출
 *   	JoinPoint: 어떤 위치에서 호출
 *   			   Before
 *   			   After
 *   			   After-Returning
 *   			   After-Throwing
 *   			   Arround
 *   	PointCut: 어떤 메소드에서 적용 => 메소드 지정
 *   		public String display()
 *   		{
 *   			=> Before => 전처리기 => 크롤링/파일 읽기
 *   			try
 *   			{
 *   				---------- Around
 *   
 *   				----------
 *   			}catch(Exception ex)
 *   			{
 *   				=> 에러 처리 => After-Throwing
 *   			}
 *   			finally
 *   			{
 *   				=> 자동화 처리 => After
 *   			}
 *   			return 값 => After-Returning
 *   		}
 *   	Advice: JoinPint + PointCut
 *   	Aspect: Advice => 공통 모듈
 *   	Weaving: 메소드가 합쳐지는 과정
 *   
 *   		@Before("disp()")
 *   		public void aaa(){}
 *   		@After("disp()")
 *   		public void bbb(){}
 *   		@AfterThrowing("disp()")
 *   		=> Proxy패턴
 *   		public void ccc(){}
 *   		
 *   		public void disp()
 *   		{
 *   			aaa()
 *   			try
 *   			{
 *   			}catch(Exception ex)
 *   			{
 *   				ccc()
 *   			}
 *   			finally
 *   			{
 *   				bbb()
 *   			}
 *   		}
 *   
 *   Java
 *   	=> Java/Python => 차이점
 *   	=> Java에서 접근지정어
 *   	=> JVM의 구조
 *   	=> GC
 *   	=> ***** Call By Reference / Call By Value
 *   	=> ***** 리플렉션
 *   	=> for/foreach의 차이점
 *   	=> Wrapper의 사용처 => 만들어진 배경
 *   	=> 클래스, 객체, 인스턴스
 *   	=> 제네릭스
 *   	=> 직렬화/역직렬화 => IO
 *   	=> equals/ ==
 *   	=> 추상 클래스/인터페이의 차이점
 *   	=> 오버로딩/오버라이딩
 *   	=> 싱글턴/팩토리 패턴
 *   	=> String/StringBuffer/StringBuilder
 *   	=> 예외처리의 종류와 예외처리의 정의
 *   	=> List/Set/Map 등 Collection Class
 *   Oracle
 *   	=> JOIN/SubQuery
 *   	=> 프로시저/트리거
 *   	=> 데이터형(BLOB/BFile)
 *   	=> View
 *   	=> DML/DDL/TCL
 *   Front
 *   	=> HTML
 *   		GET/POST => http/https
 *   	=> JSP: 생명주기/Session/Cookie, JSTL/EL, DBCP, MVC
 *   	=> Servlet => JSP/Servlet 비교
 *   	=> JavaScript => ES6 => let/var/const 차이점, =>(이 연산자가 무슨 뜻인지) , ...(이 연산자가 무슨 뜻인지), 호이스팅/클로저
 *   																							   ----------
 *   	   JSON/JSONP 차이점 => Cross Origin
 *   	=> Vue.js: 장단점(특징)
 *   	=> React.js/Redux: 장단점(특징)
 *   	=> Ajax: XML, JSON, 문자열 처리 방법
 *   ======================================= 40%는 이 중 나오고, 60%는 프로젝트
 *   	솔루션: 쓰레드, 네트워크, 자료구조 => 코딩테스트
 *   	*** 정의 => 부여(프로그램 안에서) => 모르는 내용은 말하기
 *   =======================================
 *   	@ Controller	@RestController => 문자열, JSON
 *   					React/Vue...
 *   					=> 독립적/브라우저 => cdn
 *   # 파일 관리 => 다른 프로그램 연동
 *   			  | 자바스크립트/코틀린(모바일)
 *   			  | Spring-Boot
 *      			   | 자바/코틀린 => 라이센스
 *   사용할 클래스 제작
 *   -------------
 *   	클래스와 클래스의 연관 관계 => DI
 *   	프레임워크: 틀 => 메인보드(CPU, 메모리... => 클래스)
 *   	   | 라이브러리의 집합 => 레고/완제품
 *   						 === 전자정부 프레임워크 / Any프레임워크(삼성)
 *   						 === WAS(제우스, 웹 로직, 웹스페어)
 *   						 웹서버 == 미들웨어 == 톰캣
 *   => 클래스를 등록: XML/Annotation
 *   					   | @Controller, @Repository, @Component,
 *   						 @Service, @RestController, @ControllerAdvice
 *   						 => 스테레오 타입 => 메모리 할당 => 스프링에서 관리
 *   			   <bean> => Component(한 개의 기능을 가지고 있음) => 모아서 관리(Container)
 *   			   => 기능(메소드) => 여러 개 => 컴포넌트 여러 개 => 컨테이너
 *      => 요청 클래스를 메모리 할당 => Container에 저장
 *      							 | BeanFactory
 *      									|
 *      							ApplicationContext
 *      									|
 *      							WebApplicationContxt
 *         요청
 *           DispatcherServlet <===> WebApplicationContxt(처리 클래스와 연결)
 *   
 *   1. 클래스 구분자 => 필요한 클래스를 찾기
 *   @ => TYPE => class위에 설정
 *   class ClassName
 *   {
 *   	// Field
 *   	@Autowired
 *   	private B b;
 *   	
 *   	// 생성자
 *   	@Autowired		CONSTRUCTOR
 *   	public ClassName(B b)
 *   	{
 *   		this.b=b;
 *   	}
 *   
 *   	// 메소드
 *   	@Autowired
 *   	public void setB(PARAMETER B b)
 *   	{
 *   		this.b=b;
 *   	}
 *   }
 *   CONSTRUCTOR, METHOD, PARAMETER, FIELD, ANNOTAION_TYPE
 *   
 *   => Spring/Spring Boot 차이점
 *   	=> 핵심모듈을 모아서 만든 프레임워크
 *   	   ------ 동작을 위한 라이브러리 집합
 *   	   ------ 단점: 설정파일이 어려움
 *   			  pom.xml/web.xml/application-*.xml
 *   			  ------- 호환성
 *   	=> 설정파일을 자동으로 생성 => spring 프레임워크를 쉽게 사용 가능하게 만든 것
 *   		xml => properties => yml(파이썬) => 들여쓰기
 *   
 */
@Controller
// Target(value={TYPE})
// @RequestMapping("main/")
public class MainController {
	private FoodService fService; // 통합 => BI
	
	@Autowired // 권장 사항 => 자동 주입
	// @Qualifier => 여러 개가 중복된 경우에 특정 객체를 주입받는 경우
	// @Qualifier @Resource => @Autowired + @Qualifier
	// @Resource => 1.8지원 => 1.8이 가장 호환성이 좋음
	// 스프링에 등록된 클래스 객체의 주소값을 찾아서 대입
	// interface => 중복이 있는 경우에는 @Autowired는 사용 불가
	/*
	 *   interface A
	 *   {
	 *   }
	 *   class B implements A
	 *   {
	 *   }
	 *   class C implements A
	 *   {
	 *   }
	 *   
	 *   @Autowired => 오류 발생 => b, c
	 *   A a;
	 */
	// Target(value={CONSTRUCTOR, METHOD, PARAMETER, FIELD, ANNOTAION_TYPE})
	public MainController(FoodService fService)
	{
		this.fService=fService;
	}
	
	@GetMapping("main/main.do")
	// Target(value={METHOD}) 메소드만 찾음
	public String main_main()
	{
		return "";
	}
	
	@PostMapping("main/main.do")
	// Target(value={METHOD}) 메소드만 찾음
	public String main_main1()
	{
		return "";
	}
	
	@RequestMapping("main/main.do")
	// Target(value={METHOD, TYPE}) 메소드 및 클래스 가능. TYPE=클래스
	public String main_main2()
	{
		return "";
	}
}
