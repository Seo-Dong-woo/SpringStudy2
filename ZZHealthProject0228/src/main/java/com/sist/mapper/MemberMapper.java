package com.sist.mapper;
/*
 *  USERID                                    NOT NULL VARCHAR2(20)
 USERNAME                                  NOT NULL VARCHAR2(51)
 USERPWD                                   NOT NULL VARCHAR2(300)
 ENABLED                                            NUMBER(1)
 SEX                                                VARCHAR2(6)
 BIRTHDAY                                  NOT NULL VARCHAR2(20)
 EMAIL                                              VARCHAR2(100)
 POST                                      NOT NULL VARCHAR2(10)
 ADDR1                                     NOT NULL VARCHAR2(500)
 ADDR2                                              VARCHAR2(500)
 PHONE                                              VARCHAR2(20)
 CONTENT                                            CLOB
 REGDATE                                            DATE
 MODIFYDATE                                         DATE
 LASTLOGIN                                          DATE
 * 
 */

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.MemberVO;

public interface MemberMapper {
	//1.회원가입
	@Select("SELECT COUNT(*) FROM hhfinalMember "
			+"WHERE userid=#{id}")
	public int memberIdCount(String userid);
	// => ID중복체크
	@Insert("INSERT INTO hhfinalMember(userid,username,userpwd,sex,birthday,email,post,"
			+"addr1,addr2,phone,content,nickname) VALUES(#{userId},#{userName},"
			+"#{userPwd},#{sex},#{birthday},#{email},#{post},"
			+"#{addr1},#{addr2},#{phone},#{content},#{nickname})")
	public void memberInsert(MemberVO vo);
	
	@Insert("INSERT INTO hhfinalAuthority VALUES(#{userId},'ROLE_USER')")
	public void memberAuthorityInsert(String userId);
	//2. 로그인
	//1=>ID존재여부 확인
	// memberIdCount() 재사용
	//2=> 비밀번호 검색
	@Select("SELECT hfm.userId,userName,userPwd,enabled,authority "
	     +"FROM hhfinalMember hfm,hhfinalAuthority ha "
	     +"WHERE hfm.userId=ha.userId "
	     +"AND hfm.userId=#{userId}")
	public MemberVO memberLogin(String userId);
	
	@Select("SELECT hfm.userId,userName,userPwd,enabled,authority "
			  +"FROM hhfinalMember hfm,hhfinalAuthority ha "
			  +"WHERE hfm.userId=ha.userId "
			  +"AND hfm.userId=#{userId}")
	public MemberVO memberInfo(String userId);
  
    @Select("SELECT hfm.userId,userName,nickname,sex,email,phone,addr1,addr2,enabled,authority "
		  +"FROM hhfinalMember hfm,hhfinalAuthority ha "
		  +"WHERE hfm.userId=ha.userId "
		  +"AND hfm.userId=#{userId}")
    public MemberVO memberSessionData(String userId);
  
    @Update("UPDATE hhfinalMember SET "
		  +"lastlogin=SYSDATE "
		  +"WHERE userId=#{userId}")
    public void lastLoginUpdate(String userId);
    
    @Select("SELECT userId,userName,userPwd,sex,email,phone,addr1,addr2 "
			  +"FROM hhfinalMember "
			  +"WHERE userId=#{userId}")
	public MemberVO memberSessionInfoData(String userId);
    
   //선미 추가 부분 (관리자 메뉴) 회원목록
    @Select("SELECT userId, username, sex, birthday, email, addr1, addr2, phone, TO_CHAR(regdate, 'YYYY-MM-DD HH24:MI:SS') as reg_dbday, somoimno, nickname, num "
		    + "FROM (SELECT userId, username, sex, birthday, email, addr1, addr2, phone, regdate, somoimno, nickname, rownum as num "
		    + "FROM (SELECT userId, username, sex, birthday, email, addr1, addr2, phone, regdate, somoimno, nickname "
		    + "FROM hhfinalMember ORDER BY userid ASC)) "
		    + "WHERE num BETWEEN #{start} AND #{end}")
	public List<MemberVO> memberListData(Map map);
	
	@Select("SELECT COUNT(*) FROM hhfinalmember")
	public int memberTotalData();
	
	@Select("SELECT userId,userName,userPwd,enabled,sex,birthday,email,post,addr1,addr2,phone,content,"
		   +"TO_CHAR(regdate, 'YYYY-MM-DD HH24:MI:SS') as reg_dbday,TO_CHAR(modifydate, 'YYYY-MM-DD HH24:MI:SS') as mod_dbday,"
		   +"TO_CHAR(lastLogin, 'YYYY-MM-DD HH24:MI:SS') as last_dbday,somoimno,somoimadmin,poster,nickname "
		   +"FROM hhfinalmember WHERE userid=#{userid}")
	public MemberVO memberDetailData(String userid);
}















