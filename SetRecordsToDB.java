package getAttributesFromWeb;

import java.io.IOException;
import java.util.ArrayList;

public class SetRecordsToDB {
	
	public static void main(String[] args) throws IOException {
		
		String url = "http://www.tobyo.jp/reference/1-2-1-0.php?Regist=0&keyword=%E3%82%A2%E3%83%90%E3%82%B9%E3%83%81%E3%83%B3";
		ArrayList<Record> recordList = new ArrayList<Record>();
		recordList = GetRecords.GetRecordList(url);
		System.out.println(recordList.size());
//		for(int i = 0; i < recordList.size(); i++){
//			
//			System.out.println(recordList.get(i).getSnippet());
//		}
		
		
	}

}
