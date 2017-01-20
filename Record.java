package getAttributesFromWeb;

public class Record {
	
	private static int idCount = 0;
	private int id;
	private String snippet;
	private String medicineName;
	private String diseaseName;
	private String sex;
	private String title_blog;
	private String title_blogArticle;
	private String url_blogArticle;
	private String age;
	private String blogArticle;
	
	//コンストラクタ
	public Record(String snippet, String medicineName,
			String diseaseName, String sex, String title_blog,
			String title_blogArticle, String url_blogArticle, String age,
			String blogArticle) {
		idCount ++;
		this.id = id;
		this.snippet = snippet;
		this.medicineName = medicineName;
		this.diseaseName = diseaseName;
		this.sex = sex;
		this.title_blog = title_blog;
		this.title_blogArticle = title_blogArticle;
		this.url_blogArticle = url_blogArticle;
		this.age = age;
		this.blogArticle = blogArticle;
	}
	
}
