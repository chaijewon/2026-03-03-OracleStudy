package com.sist.crawling;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
 *   https://www.menupan.com/restaurant/bestrest/bestrest.asp?pt=rt
 *   https://www.menupan.com/restaurant/bestrest/bestrest.asp?pt=wk
 *   https://www.menupan.com/restaurant/bestrest/bestrest.asp?pt=nw
 *   
 */
public class FoodCrawler {
	private static String BASE_URL="https://www.menupan.com/restaurant/bestrest/bestrest.asp?pt=";
    private static String[] category= {
    	"rt",
    	"wt",
    	"nw"
    };
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 *   <p class="listName">
		 *   <span class="restName">
		 *   <a href="/restaurant/onepage.asp?acode=D200342" target="_blank">아름드리카페</a></span></p>
		 */
		/*
		 *    <div class="areaBasic">
		<dl class="restName">
			<dt>업체명<span style="color:#ffffff;cursor:default;" ondblclick="$('#id_basicdata_reporter').toggle();">.</span></dt>
			<dd class="name">아름드리카페&nbsp;&nbsp;<span id="id_basicdata_reporter" style="display:none">[D200342 : 제휴영업팀]</span></dd>
		</dl>
		<dl class="restType">
			<dt>업종</dt>
			<dd class="type">카페/주점-카페</dd>
		</dl>

		<dl class="restTel">
			<dt>전화번호</dt>
			<dd class="tel1">(070) 8872-4418</dd>

		</dl>
		<dl class="restAdd">
			<dt>주소</dt>
			<dd class="add1"><a href="/map/restmap/map_search.asp?acode=D200342" target="_blank">강원 동해시 평릉동 487-1</a></dd>

			<dd class="add2">[새주소] <a href="/map/restmap/map_search.asp?acode=D200342" target="_blank">강원 동해시 평원5길 4</a></dd>

		</dl>

		<dl class="restGrade">
			<dt>평점</dt>
			<dd class="rate">
				<p class="point"><span class="star" style="width:0%"></span><!-- ☆☆☆☆☆ //--></p>
				<p class="score"><span class="total">0.0</span><span class="line">|</span><span class="count">0명 참여</span></p>
			</dd>
			<dd class="btnPoint">

				<a href="javascript:;" onClick="fn_Openmember();"><img src="/image/restaurant/onepage/btn_point.gif" alt="평가하기" /></a>

			</dd>
		</dl>

		<dl class="restTheme">
			<dt>테마</dt>
			<dd class="Theme">
		 */
        try
        {
        	for(int i=0;i<category.length;i++)
        	{
        		System.out.println("번호:"+(i+1));
        		Document doc=
        			Jsoup.connect(BASE_URL+category[i]).get();
        		Elements link=doc.select("p.listName span.restName a");
        		//System.out.println(link.toString());
        		for(int j=0;j<link.size();j++)
        		{
        			System.out.println(link.get(j).attr("href"));
        			String url="https://www.menupan.com"+link.get(j).attr("href");
        			Document doc2=Jsoup.connect(url).get();
        			String name=doc2.selectFirst("div.areaBasic dl.restName dd.name").ownText().trim();
        			System.out.println(name);
        			Element type=doc2.selectFirst("div.areaBasic dl.restType dd.type");
        			System.out.println(type.text());
        			
        			Element phone=doc2.selectFirst("div.areaBasic dl.restTel dd.tel1");
        			System.out.println(phone.text());
        			
        			Element address=doc2.selectFirst("div.areaBasic dl.restAdd dd.add1");
        			System.out.println(address.text());
        			
        			Element theme=doc2.selectFirst("div.areaBasic dl.restTheme dd.theme");
        			System.out.println(theme.text());
        			
        			Element score=doc2.selectFirst("div.areaBasic dl.restGrade span.total");
        			System.out.println(score.text());
        			
        			Element price=doc2.selectFirst("div.restPrice p.price");
        			System.out.println(price.text());
        		}
        	}
        }catch(Exception ex) {ex.printStackTrace();}
	}

}
