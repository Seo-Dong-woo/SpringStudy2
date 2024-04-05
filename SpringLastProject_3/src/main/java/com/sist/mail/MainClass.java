package com.sist.mail;

import java.io.FileReader;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String style="<style>" + 
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
        			+ "<table>"
        			+ "<tr>"
        			+ "<th width=15%>회원번호</th>"
        			+ "<td width=85%>1</td>"
        			+ "</tr>"
        			+ "<tr>"
        			+ "<th width=15%>이름</th>"
        			+ "<td width=85%>홍길동</td>"
        			+ "</tr>"
        			+ "<tr>"
        			+ "<th width=15%>성별</th>"
        			+ "<td width=85%>남자</td>"
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

}