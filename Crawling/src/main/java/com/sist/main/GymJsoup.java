package com.sist.main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class GymJsoup {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
			Document doc=Jsoup.connect("http://soonwidot.co.kr/rank/search.php?si=175&stx=%EB%8F%84%EB%B4%89%EA%B5%AC%20%ED%97%AC%EC%8A%A4").get();
			Elements link=doc.select("div.webzine-item a");
			
			for(int j=0;j<link.size();j++)
			{
				try
				{
					String str=link.get(j).attr("href");
					String subLink="http://soonwidot.co.kr" + str;
					//System.out.println(subLink);
					Document doc2=Jsoup.connect(subLink).get();
					
					// 이미지
					Elements poster=doc2.select("div.webzine-img-box-in");
					System.out.println(poster.attr("src"));
					//vo.setPoster(poster.attr("src"));
					
					// 헬스장명
					Elements title=doc2.select("span.link-element");
					System.out.println(title.text());
					//vo.setTitle(title.text());
						
					//dao.RecipeDetailData(vo);
					
				}catch(Exception ex) {ex.printStackTrace();}
					
			}
			System.out.println("SAVE END");
		}catch(Exception ex) {}
	}

}
