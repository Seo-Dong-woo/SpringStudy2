package com.sist.main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sist.dao.GymDAO;
import com.sist.dao.GymVO;

import java.io.File;
import java.io.IOException;

public class CrawlingController {
    public static void main(String[] args) {
    	GymDAO dao=new GymDAO();
		GymVO vo=new GymVO();
        try {
            // 크롤링할 파일 경로 설정
            String filePath = "C:\\springDev\\springStudy\\Crawling\\src\\main\\webapp\\crawling\\crawling.jsp";

            // Jsoup을 사용하여 HTML 파일을 파싱
            File input = new File(filePath);
            Document doc = Jsoup.parse(input, "UTF-8");

            // 주소
            Elements elements = doc.select("p.webzine-cont");
            // 두 번째 strong 태그 선택(헬스장명)
            Elements elements1 = doc.select("div.webzine-desc strong:eq(1)");
            // 이미지
            Elements elements2 = doc.select("div.webzine-img-box-in img");
            
            
            ///////////////////////////////////////////////////////////
            for(int i=0;i<elements1.size();i++) // 주소나 연락처는 데이터가 없을 수도 있기 때문에 헬스장 명으로 크기를 잡음
            {
            	String text1 = elements1.get(i).text();
            	System.out.println("헬스장명: " + text1);
            	vo.setTitle(text1);
            	
            	
            	String text2 = elements2.get(i).attr("data-src");
            	System.out.println("이미지: " + text2);
            	vo.setPoster(text2);
            	
             	//String crawlingData = elements.get(i).text();
             	//System.out.println("주소:" + crawlingData);
            	
            	
            	
                String text = elements.get(i).html(); // 해당 요소의 HTML을 가져옴
                String[] parts = text.split("<br>"); // <br/> 태그를 기준으로 텍스트를 분리
                
                String address = null;
                String operatingHours = null;
                String contactInfo = null;
                
                

                if (parts.length > 0) {
                    address = parts[0].trim(); // 첫 번째 줄은 주소로 저장
                }
                if (parts.length > 1) {
                    operatingHours = parts[1].trim(); // 두 번째 줄은 영업 시간으로 저장
                }
                if (parts.length > 2) {
                    contactInfo = parts[2].trim(); // 세 번째 줄은 연락처로 저장
                }
                
                //System.out.println("Address: " + address);
                System.out.println("운영 시간: " + operatingHours);
                vo.setTime(operatingHours);
                //System.out.println("Contact Info: " + contactInfo);
                // 정규식 패턴을 이용하여 HTML 주석 제거
                String addressWithoutComment = address.replaceAll("<!--.*?-->", "");
                String InfoWithoutComment = contactInfo.replaceAll("<!--.*?-->", "");

                System.out.println("주소: " + addressWithoutComment);
                vo.setAddress(addressWithoutComment);
                //System.out.println("연락처: " + InfoWithoutComment);
                
                // 연락처 정보가 없거나 공백인 경우를 처리하기 위해 추가합니다.
                if (InfoWithoutComment != null && !InfoWithoutComment.trim().isEmpty()) {
                    String contactInfo2 = InfoWithoutComment.substring(6).trim();
                    System.out.println("연락처=> " + contactInfo2);
                    vo.setPhone(contactInfo2);
                } else {
                    System.out.println(InfoWithoutComment);
                    vo.setPhone(" ");
                } 
                dao.GymDetailData(vo);
                System.out.println("===================================");
            }
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}