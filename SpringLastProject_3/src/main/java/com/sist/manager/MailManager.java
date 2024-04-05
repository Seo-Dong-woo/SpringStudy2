package com.sist.manager;

import java.io.FileReader;
import java.util.*;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

import com.sist.vo.*;
import com.sist.service.*;

@Component
public class MailManager {
	private String style="<style>" + 
    		"body {" + 
    		"	  padding:1.5em;" + 
    		"	  background: #f5f5f5" + 
    		"	}" + 
    		"	table {" + 
    		"	  border: 1px #a39485 solid;" + 
    		"	  font-size: .9em;" + 
    		"	  box-shadow: 0 2px 5px rgba(0,0,0,.25);" + 
    		"	  width: 100%;" + 
    		"	  border-collapse: collapse;" + 
    		"	  border-radius: 5px;" + 
    		"	  overflow: hidden;" + 
    		"	}" + 
    		"	th {" + 
    		"	  text-align: left;" + 
    		"	}" + 
    		"	thead {" + 
    		"	  font-weight: bold;" + 
    		"	  color: #fff;" + 
    		"	  background: #73685d;" + 
    		"	}" + 
    		"	 td, th {" + 
    		"	  padding: 1em .5em;" + 
    		"	  vertical-align: middle;" + 
    		"	}" + 
    		"	 td {" + 
    		"	  border-bottom: 1px solid rgba(0,0,0,.1);" + 
    		"	  background: #fff;" + 
    		"	}" + 
    		"	a {" + 
    		"	  color: #73685d;" + 
    		"	}" + 
    		"	 @media all and (max-width: 768px) {" + 
    		"	  table, thead, tbody, th, td, tr {" + 
    		"	    display: block;" + 
    		"	  }" + 
    		"	  th {" + 
    		"	    text-align: right;" + 
    		"	  }" + 
    		"	  table {" + 
    		"	    position: relative; " + 
    		"	    padding-bottom: 0;" + 
    		"	    border: none;" + 
    		"	    box-shadow: 0 0 10px rgba(0,0,0,.2);" + 
    		"	  }" + 
    		"	  thead {" + 
    		"	    float: left;" + 
    		"	    white-space: nowrap;" + 
    		"	  }" + 
    		"	  tbody {" + 
    		"	    overflow-x: auto;" + 
    		"	    overflow-y: hidden;" + 
    		"	    position: relative;" + 
    		"	    white-space: nowrap;" + 
    		"	  }" + 
    		"	  tr {" + 
    		"	    display: inline-block;" + 
    		"	    vertical-align: top;" + 
    		"	  }" + 
    		"	  th {" + 
    		"	    border-bottom: 1px solid #a39485;" + 
    		"	  }" + 
    		"	  td {" + 
    		"	    border-bottom: 1px solid #e5e5e5;" + 
    		"	  }" + 
    		"	  }" + 
    		"</style>";
	
	public void mailMemberSender(MemberVO vo)
	{
		String temp="";
		try
		{
			FileReader fr=new FileReader("c:\\springDev\\mail.txt");
			int i=0;
			while((i=fr.read())!=-1)
			{
				temp+=String.valueOf((char)i);
			}
		}catch(Exception ex) {}
		
		
		
		
        String host="smtp.naver.com";
        String user="tjehddn456@naver.com";
        String password="TEDsdw1!2@";
        Properties props=new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.auth", "true");
        
        Session session=Session.getDefaultInstance(props,new javax.mail.Authenticator() {
        	  protected PasswordAuthentication getPasswordAuthentication() {
        		  return new PasswordAuthentication(user, password);
        	  }
        });
        try
        {
        	String html="<html>"
        			+ "<head>" + style + "</head>"
        			+ "<body>"
        			+ "<table width=450>"
        			+ "<tr>"
        			+ "<th width=15%>이름</th>"
        			+ "<td width=85%>"+vo.getUserName()+"</td>"
        			+ "</tr>"
        			+ "<tr>"
        			+ "<th width=15%>성별</th>"
        			+ "<td width=85%>"+vo.getSex()+"</td>"
        			+ "</tr>"
        			+ "<tr>"
        			+ "<th width=15%>이메일</th>"
        			+ "<td width=85%>"+vo.getEmail()+"</td>"
        			+ "</tr>"
        			+ "<tr>"
        			+ "<th width=15%>주소</th>"
        			+ "<td width=85%>"+vo.getAddr1()+" "+vo.getAddr2()+"</td>"
        			+ "</tr>"
        			+ "<tr>"
        			+ "<th width=15%>전화</th>"
        			+ "<td width=85%>"+vo.getPhone()+"</td>"
        			+ "</tr>"
        			+ "<tr>"
        			+ "<td colspan=2>"
        			+ "<img src=\"https://recipe1.ezmember.co.kr/img/mobile/2023/icon_h2_cate.png\" style=\"width:300px;height:300px\">"
        			+ "</td>"
        			+ "</tr>"
        			+ "</table>"
        			+ "</body>"
        			+ "</html>";
        	MimeMessage message=new MimeMessage(session);
        	message.setFrom(new InternetAddress(user));
        	
        	message.setContent(html,"text/html;charset=UTF-8");
        	message.addRecipient(Message.RecipientType.TO, new InternetAddress("tjehddn456@naver.com"));
        	message.setSubject("회원을 축하합니다");
        	Transport.send(message);
        	System.out.println("메일 전송 완료!!");
        }catch(Exception ex) {ex.printStackTrace();}
	}
	public void mailReserveSender(ReserveVO vo)
	{
		String temp="";
		try
		{
			FileReader fr=new FileReader("c:\\springDev\\mail.txt");
			int i=0;
			while((i=fr.read())!=-1)
			{
				temp+=String.valueOf((char)i);
			}
		}catch(Exception ex) {}
		
		
		
		
        String host="smtp.naver.com";
        String user="tjehddn456@naver.com";
        String password="TEDsdw1!2@";
        Properties props=new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.auth", "true");
        
        Session session=Session.getDefaultInstance(props,new javax.mail.Authenticator() {
        	  protected PasswordAuthentication getPasswordAuthentication() {
        		  return new PasswordAuthentication(user, password);
        	  }
        });
        try
        {
        	String html="<html>"
        			+ "<head>" + style + "</head>"
        			+ "<body>"
        			+ "<table width=450>"
        			+ "<tr>"
        			+ "<th width=15%>아이디</th>"
        			+ "<td width=85%>"+vo.getUserId()+"</td>"
        			+ "</tr>"
        			+ "<tr>"
        			+ "<th width=15%>업체명</th>"
        			+ "<td width=85%>"+vo.getFvo().getName()+"</td>"
        			+ "</tr>"
        			+ "<tr>"
        			+ "<th width=15%>이미지</th>"
        			+ "<td width=85%><img src=\"http://www.menupan.com"+vo.getFvo().getPoster()+"\" style=\"width:35px; height:35px\"></td>"
        			+ "</tr>"
        			+ "<tr>"
        			+ "<th width=15%>예약일</th>"
        			+ "<td width=85%>"+vo.getRDate()+"</td>"
        			+ "</tr>"
        			+ "<tr>"
        			+ "<th width=15%>예약시간</th>"
        			+ "<td width=85%>"+vo.getRTime()+"</td>"
        			+ "</tr>"
        			+ "<tr>"
        			+ "<th width=15%>인원</th>"
        			+ "<td width=85%>"+vo.getRInwon()+"</td>"
        			+ "</tr>"
        			+ "<tr>"
        			+ "<th width=15%>예약 등록일</th>"
        			+ "<td width=85%>"+vo.getDbday()+"</td>"
        			+ "</tr>"
        			+ "<tr>"
        			+ "<td colspan=2>"
        			+ "<img src=\"https://recipe1.ezmember.co.kr/img/mobile/2023/icon_h2_cate.png\" style=\"width:300px;height:300px\">"
        			+ "</td>"
        			+ "</tr>"
        			+ "</table>"
        			+ "</body>"
        			+ "</html>";
        	MimeMessage message=new MimeMessage(session);
        	message.setFrom(new InternetAddress(user));
        	
        	message.setContent(html,"text/html;charset=UTF-8");
        	message.addRecipient(Message.RecipientType.TO, new InternetAddress("tjehddn456@naver.com"));
        	message.setSubject("예약이 완료되었습니다");
        	Transport.send(message);
        	System.out.println("메일 전송 완료!!");
        }catch(Exception ex) {ex.printStackTrace();}
	}
}
