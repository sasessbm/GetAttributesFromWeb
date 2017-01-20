package getAttributesFromWeb;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetMedicineNameAndUrl {
	
	public static LinkedHashMap<String,String> getMedicineNameAndUrlHashMap(String inputUrl) throws Exception{
		
		String name_medicine = "";
		String url_medicine = "";
		LinkedHashMap<String,String> medicineNameAndUrlHashMap = new LinkedHashMap<String,String>();
		
		System.setProperty("http.proxyHost", "proxy.nagaokaut.ac.jp");
        System.setProperty("http.proxyPort", "8080");
		
        Document document = Jsoup.connect(inputUrl).get();
        
        Elements wordTags = document.select("dl.re-search-list");
        for (Element wordTag : wordTags){
        	Elements aTags = wordTag.select("dd a");
        	for (Element aTag : aTags){
        		name_medicine = aTag.text();
        		url_medicine = aTag.html();
        		medicineNameAndUrlHashMap.put(name_medicine,  aTag.attr("href"));
        	}
        }
        
		return medicineNameAndUrlHashMap;
	}

}
