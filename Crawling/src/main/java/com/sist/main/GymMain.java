package com.sist.main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class GymMain {
	public static void main(String[] args) throws Exception {
        // 크롬 드라이버 경로 설정
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\Downloads\\chromedriver-win64\\chromedriver-win64/chromedriver.exe");

        // 웹 드라이버 초기화
        WebDriver driver = new ChromeDriver();

        try {
            // 웹 페이지로 이동
        	driver.get("http://soonwidot.co.kr/rank/search.php?si=190&stx=%EC%A4%91%EB%9E%91%EA%B5%AC%20%ED%97%AC%EC%8A%A4");


        	// 원하는 요소 찾기
//            WebElement elementDesc = driver.findElement(By.className("webzine-desc"));
        	
        	// 각 정보 요소 찾기
//            WebElement nameElement = elementDesc.findElement(By.tagName("strong"));
//            WebElement addressElement = elementDesc.findElement(By.tagName("p"));
        	
        	// 텍스트 추출
//            String gymName = nameElement.getText().trim();
//            String gymAddress = addressElement.getText().trim();
            
//            System.out.println("체육관 이름: " + gymName);
//            System.out.println("체육관 주소 및 영업시간: " + gymAddress);
            
        	// 자바스크립트 실행을 위한 JavascriptExecutor 인터페이스 사용
            JavascriptExecutor js = (JavascriptExecutor) driver;
        	
            // 페이지 스크롤하기
            while (true) {
                // 현재 페이지의 높이
                long currentHeight = (Long) js.executeScript("return window.pageYOffset;");

                // 스크롤을 조금씩 내리기
                js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

                // 잠시 대기하여 페이지 로딩을 기다리기
                try {
                    TimeUnit.SECONDS.sleep(5); // 5초 대기
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 새로운 높이
                long newHeight = (Long) js.executeScript("return window.pageYOffset;");

                // 스크롤이 더 이상 내려가지 않으면 종료
                if (currentHeight == newHeight) {
                    break;
                }
            }
            
            // 웹페이지에서 webzine-item 클래스를 가진 모든 요소를 찾음
            List<WebElement> elements = driver.findElements(By.className("webzine-item"));
            
            // 각 요소에 대해 루프를 실행하여 정보를 추출
            for (WebElement element : elements) {
                // 요소에서 원하는 정보를 찾음
                WebElement nameElement = element.findElement(By.xpath(".//h4/strong[2]"));
                WebElement addressElement = element.findElement(By.xpath(".//p"));
                
                // 텍스트 추출
                String gymName = nameElement.getText().trim();
                String gymAddress = addressElement.getText().trim();
                
                // 출력
                System.out.println("체육관 이름: " + gymName);
                System.out.println("체육관 주소 및 영업시간: " + gymAddress);
                System.out.println("-------------------------------------");
            }
            
        } finally {
            // 셀레니움 종료
            driver.quit();
        }
    }
}