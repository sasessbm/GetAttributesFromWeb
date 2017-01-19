package getAttributesFromWeb;

import java.net.URL;

public class Attribute {
	
	private String snippet;
	private String medicineName;
	private String diseaseName;
	private String sex;
	private String blogTitle;
	private String blogArticleTitle;
	private URL url;
	private int age;
	private String blogArticle;
	
	//コンストラクタ(ALL)
	public Attribute(String snippet, String medicineName, String diseaseName,
			String sex, String blogTitle, String blogArticleTitle, URL url,
			int age, String blogArticle) {
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
	public Attribute(String snippet, String medicineName, String diseaseName,
			String sex, String blogTitle, String blogArticleTitle, URL url,
			int age) {
		this(snippet, medicineName, diseaseName, sex, blogTitle, blogArticleTitle, url, age, null);
	}

	//コンストラクタ(ブログ記事と年齢無し)
	public Attribute(String snippet, String medicineName, String diseaseName,
			String sex, String blogTitle, String blogArticleTitle, URL url) {
		this(snippet, medicineName, diseaseName, sex, blogTitle, blogArticleTitle, url, -1);
	}

}
