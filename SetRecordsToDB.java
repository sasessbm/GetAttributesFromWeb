package getAttributesFromWeb;

import java.io.IOException;
import java.util.ArrayList;

public class SetRecordsToDB {
	
	public static void main(String[] args) throws IOException {
		
		String url = "http://www.tobyo.jp/reference/1-2-1-0.php?Regist=0&keyword=%E3%82%A2%E3%83%90%E3%82%B9%E3%83%81%E3%83%B3";
		ArrayList<Record> recordList = new ArrayList<Record>();
		recordList = GetRecords.GetRecordList(url);
		//System.out.println(recordList.size());
		for(int i = 0; i < recordList.size(); i++){
			System.out.println("-----------------------------------------------------------------------------------------------------------------------");
			System.out.println("Id:" +recordList.get(i).getId());
			System.out.println("スニペット:" + recordList.get(i).getSnippet());
			System.out.println("薬剤名:" +recordList.get(i).getMedicineName());
			System.out.println("病名:" +recordList.get(i).getDiseaseName());
			System.out.println("性別:" +recordList.get(i).getSex());
			System.out.println("ブログタイトル:" +recordList.get(i).getTitle_blog());
			System.out.println("ブログ記事タイトル:" +recordList.get(i).getTitle_blogArticle());
			System.out.println("ブログ記事ＵＲＬ:" +recordList.get(i).getUrl_blogArticle());
			System.out.println("年齢:" +recordList.get(i).getAge());
			
		}
		
		
	}

}
