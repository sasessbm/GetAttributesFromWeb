package getAttributesFromWeb;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetAttributes {

	public static void main(String[] args) throws IOException {
		System.setProperty("http.proxyHost", "proxy.nagaokaut.ac.jp");
        System.setProperty("http.proxyPort", "8080");
		
		Document document = Jsoup.connect("http://www.tobyo.jp/reference/1-2-1-0.php?Regist=0&keyword=%E3%82%A2%E3%83%90%E3%82%B9%E3%83%81%E3%83%B3").get();
		Element divTag = document.getElementById("site-sort");
		Elements dlTags = divTag.select("dl");
		for (Element dlTag:dlTags){
			Elements aTags = dlTag.select("dt a.site-title");
			System.out.println("ブログ記事タイトル:" + aTags.text());
			Elements ddTags = dlTag.select("dd");
			for (Element ddTag:ddTags){
				if(ddTag.className().equals("site-title-icon")){
					Elements aTagsInDDTag = ddTag.select("a");
					for (Element aTag:aTagsInDDTag){
						
						if(aTag.className().equals("site-title")){
							System.out.println("ブログタイトル:" + aTag.text());
						}
						else if(aTag.className().equals("inp-link")){
							System.out.println("病名:" + aTag.text());
						}
						else if(aTag.className().equals("")){
							String href = aTag.select("[href]").text();
							if(href.length() == 2){
								System.out.println("性別:" + href);
								System.out.println("年齢:不明");
							}else{
								System.out.println("性別:" + getSex(href));
								System.out.println("年齢:" + getAge(href));
							}
						}
					}
					
				}else if(ddTag.className().equals("site-url")){
					System.out.println("URL:" + ddTag.text());
				}else{
					System.out.println("スニペット:" + ddTag.text());
				}
				
			}
			
			System.out.println("-----------------------------------------------------------------------------------------------------------------------");
		}
		

	}
	
	public static String getAge(String hrefText){
		String age = "";
		age = hrefText.substring(0, hrefText.length()-3);
		return age;
	}
	
	public static String getSex(String hrefText){
		String sex = "";
		sex = hrefText.substring(hrefText.length()-2, hrefText.length());
		return sex;
	}

}
