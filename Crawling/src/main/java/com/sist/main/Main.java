package com.sist.main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
    	try {
            // 크롤링할 파일 경로 설정
            String filePath = "C:\\springDev\\springStudy\\Crawling\\src\\main\\webapp\\crawling\\crawling.jsp";

            // Jsoup을 사용하여 HTML 파일을 파싱
            File input = new File(filePath);
            Document doc = Jsoup.parse(input, "UTF-8");
            
            // 필요한 요소 추출
            Elements webzineItems = doc.select("div.webzine-item");

            // 반복문을 통해 각 웹진 아이템에 대한 정보 추출
            for (Element webzineItem : webzineItems) {
                // 이름 추출
                String name = webzineItem.selectFirst("div.webzine-desc strong").text();

                // 주소, 영업일, 연락처 추출
                String address = webzineItem.selectFirst("p.webzine-desc").text();

                // 출력
                System.out.println("이름: " + name);
                System.out.println("주소, 영업일, 연락처: " + address);
                System.out.println();
            
            
            }
    	}
    	catch(IOException e) {
    		e.printStackTrace();
    	}
    }
}
