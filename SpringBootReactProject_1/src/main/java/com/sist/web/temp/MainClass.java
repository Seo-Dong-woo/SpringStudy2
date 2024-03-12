package com.sist.web.temp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
/*
 *   <table class="list-wrap">
 *   <tr class="list" songid="105464107">
                                <td class="check"><input type="checkbox" class="select-check" title="밤양갱" /></td>
                                <td class="number">1
                                        
                                    <span class="rank">
                                        
                                            
                                            
                                                <span class="rank"><span class="rank-none"><span
                                                        class="hide">유지</span></span></span>
                                            
                                            
                                            
                                        
                                    </span>
                                </td>
                                <td><a href="#" class="cover" onclick="fnViewAlbumLayer('84783137');return false;"><span class="mask"></span><img src="//image.genie.co.kr/Y/IMAGE/IMG_ALBUM/084/783/137/84783137_1707790321067_1_140x140.JPG/dims/resize/Q_80,0" onerror="this.src='//image.genie.co.kr/imageg/web/common/blank_68.gif';" alt="밤양갱" /></a></td>
                                <td class="link"><a href="#" class="btn-basic btn-info" onclick="fnViewSongInfo('105464107');return false;">곡 제목 정보 페이지</a></td>
                                <td class="info">
                                    
                                    
                                    <a href="#" class="title ellipsis" title="재생" onclick="fnPlaySong('105464107','1');return false;">
                                        
                                            
                                        
                                        
                                        
                                        
                                            
                                                밤양갱</a>

                                        <a href="#" class="artist ellipsis" onclick="fnViewArtist('80667991');return false;">비비 (BIBI)</a>
                                        
                                        <div class="toggle-button-box" >
                                            <button type="button" class="btn artist-etc" onclick="fnRelationArtistList('105464107');">외</button>
                                            <ul class="list" id="RelationArtist_105464107"></ul>
                                        </div>
                                        
                                        <i class="bar">|</i>
                                        <a href="#" class="albumtitle ellipsis" onclick="fnViewAlbumLayer('84783137');return false;">밤양갱</a>
                                </td>
 */
public class MainClass {
	public static void main(String[] args) {
		try
		{
			MusicDAO dao=new MusicDAO();
			Document doc=Jsoup.connect("https://www.genie.co.kr/chart/top200").get();
			Elements title=doc.select("table.list-wrap td.info a.title");
			Elements singer=doc.select("table.list-wrap td.info a.artist");
			Elements album=doc.select("table.list-wrap td.info a.albumtitle");
			Elements poster=doc.select("table.list-wrap a.cover img");
			Elements etc=doc.select("table.list-wrap span.rank");
			
			for(int i=0;i<title.size();i++)
			{
				System.out.println("순위: " + (i+1));
				System.out.println("제목: " + title.get(i).text());
				System.out.println("가수: " + singer.get(i).text());
				System.out.println("앨범: " + album.get(i).text());
				System.out.println("상태: " + etc.get(i).text());
				System.out.println("이미지: " + poster.get(i).attr("src"));
				System.out.println("===================================");
				MusicVO vo=new MusicVO();
				vo.setTitle(title.get(i).text());
				vo.setSinger(singer.get(i).text());
				vo.setAlbum(album.get(i).text());
				vo.setPoster(poster.get(i).attr("src"));
				String s=etc.get(i).text();
				String id="";
				String state="";
				if(s.equals("유지"))
				{
					id="0";
					state="유지";
				}
				else
				{
					id=s.replaceAll("[^0-9]", ""); // 숫자 제외
					state=s.replaceAll("[^가-힣]", ""); // 한글 제외
				}
				vo.setIdcrement(Integer.parseInt(id.trim()));
				vo.setState(state);
				
				dao.musicInsert(vo);
			}
			System.out.println("저장 완료");
		}catch(Exception ex) {}
	}
}
