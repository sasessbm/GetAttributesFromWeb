package getAttributesFromWeb;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetRecords {

	public static ArrayList<Record> GetRecordList(String url) throws IOException {
		
		ArrayList<Record> recordList = new ArrayList<Record>();
		
		String snippet = "";
		String medicineName = "";
		String diseaseName = "";
		String sex = "";
		String title_blog = "";
		String title_blogArticle = "";
		String url_blogArticle = "";
		String age = "";
		String blogArticle = "";
		
		System.setProperty("http.proxyHost", "proxy.nagaokaut.ac.jp");
        System.setProperty("http.proxyPort", "8080");
		
		//Document document = Jsoup.connect("http://www.tobyo.jp/reference/1-2-1-0.php?Regist=0&keyword=%E3%82%A2%E3%83%90%E3%82%B9%E3%83%81%E3%83%B3").get();
        Document document = Jsoup.connect(url).get();
		Element divTag = document.getElementById("site-sort");
		Elements dlTags = divTag.select("dl");
		for (Element dlTag:dlTags){
			//Record record = new Record();
			Elements aTags = dlTag.select("dt a.site-title");
			title_blogArticle = aTags.text();
			//System.out.println("ブログ記事タイトル:" + aTags.text());
			Elements ddTags = dlTag.select("dd");
			for (Element ddTag:ddTags){
				if(ddTag.className().equals("site-title-icon")){
					Elements aTagsInDDTag = ddTag.select("a");
					for (Element aTag:aTagsInDDTag){
						
						if(aTag.className().equals("site-title")){
							title_blog = aTag.text();
							//System.out.println("ブログタイトル:" + aTag.text());
						}
						else if(aTag.className().equals("inp-link")){
							diseaseName = aTag.text();
							//System.out.println("病名:" + aTag.text());
						}
						else if(aTag.className().equals("")){
							String href = aTag.select("[href]").text();
							if(href.length() == 2){
								sex = href;
								age = "不明";
								//System.out.println("性別:" + href);
								//System.out.println("年齢:不明");
							}else{
								sex = href;
								age = getAge(href);
								//System.out.println("性別:" + getSex(href));
								//System.out.println("年齢:" + getAge(href));
							}
						}
					}
					
				}else if(ddTag.className().equals("site-url")){
					url_blogArticle = ddTag.text();
					System.out.println("URL:" + ddTag.text());
				}else{
					snippet = ddTag.text();
					System.out.println("スニペット:" + ddTag.text());
				}
				
				Record record = new Record(snippet,medicineName,diseaseName,sex,title_blog,title_blogArticle,url_blogArticle,age,blogArticle);
				recordList.add(record);
			}
			
			System.out.println("-----------------------------------------------------------------------------------------------------------------------");
		}
		
		return recordList;

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
