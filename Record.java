package getAttributesFromWeb;

import java.net.URL;

public class Record {
	
	private static int idCount = 0;
	private int id;
	private String snippet;
	private String medicineName;
	private String diseaseName;
	private String sex;
	private String blogTitle;
	private String blogArticleTitle;
	private String url;
	private int age;
	private String blogArticle;
	
	//コンストラクタ(ALL)
	public Record(String snippet, String medicineName, String diseaseName,
			String sex, String blogTitle, String blogArticleTitle, String url,
			int age, String blogArticle) {
		idCount ++;
		id = idCount;
		this.snippet = snippet;
		this.medicineName = medicineName;
		this.diseaseName = diseaseName;
		this.sex = sex;
		this.blogTitle = blogTitle;
		this.blogArticleTitle = blogArticleTitle;
		this.url = url;
		this.age = age;
		this.blogArticle = blogArticle;
	}

	//コンストラクタ(ブログ記事無し)
	public Record(String snippet, String medicineName, String diseaseName,
			String sex, String blogTitle, String blogArticleTitle, String url,
			int age) {
		this(snippet, medicineName, diseaseName, sex, blogTitle, blogArticleTitle, url, age, null);
	}

	//コンストラクタ(ブログ記事と年齢無し)
	public Record(String snippet, String medicineName, String diseaseName,
			String sex, String blogTitle, String blogArticleTitle, String url) {
		this(snippet, medicineName, diseaseName, sex, blogTitle, blogArticleTitle, url, -1);
	}

}
