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
		
        Document document = Jsoup.connect(url).get();
		Element divTag = document.getElementById("site-sort");
		Elements dlTags = divTag.select("dl");
		for (Element dlTag:dlTags){
			Elements aTags = dlTag.select("dt a.site-title");
			title_blogArticle = aTags.text();
			Elements ddTags = dlTag.select("dd");
			for (Element ddTag:ddTags){
				if(ddTag.className().equals("site-title-icon")){
					Elements aTagsInDDTag = ddTag.select("a");
					for (Element aTag:aTagsInDDTag){
						if(aTag.className().equals("site-title")){
							//ブログタイトル
							title_blog = aTag.text();
						}
						else if(aTag.className().equals("inp-link")){
							//病名
							diseaseName = aTag.text();
						}
						else if(aTag.className().equals("")){
							String href = aTag.select("[href]").text();
							if(href.length() == 0){
								//性別
								sex = "不明";
								//年齢
								age = "不明";
							}
							else if(href.length() == 2){
								//性別
								sex = href;
								//年齢
								age = "不明";
							}else{
								//性別
								sex = href;
								//年齢
								age = getAge(href);
							}
						}
					}
					
				}else if(ddTag.className().equals("site-url")){
					url_blogArticle = ddTag.text();
				}else{
					snippet = ddTag.text();
				}
			}
			
			Record record = new Record(snippet,medicineName,diseaseName,sex,title_blog,title_blogArticle,url_blogArticle,age,blogArticle);
			recordList.add(record);
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
