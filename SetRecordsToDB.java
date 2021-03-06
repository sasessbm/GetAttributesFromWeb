package getAttributesFromWeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SetRecordsToDB {

	//プロキシ設定
	private static Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy.nagaokaut.ac.jp", 8080));

	public static void main(String[] args) throws Exception {
		
		LinkedHashMap<String,String> medicineNameAndUrlHashMap = new LinkedHashMap<String,String>();
		medicineNameAndUrlHashMap = GetMedicineNameAndUrl.getMedicineNameAndUrlHashMap("http://www.tobyo.jp/reference/1-2-1.php?KEY_INDEX=2&key=1");

		Connection con = null;
		try {
			// JDBCドライバのロード - JDBC4.0（JDK1.6）以降は不要
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// MySQLに接続
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb?useSSL=false", "root", "databasetest86");
			System.out.println("MySQLに接続できました。");

			Statement stm = con.createStatement();

			/* データ挿入　*/
			
			String sql = "update student1 set old = 20 where name = 'taro';";
			
			for(String name_medicine : medicineNameAndUrlHashMap.keySet()){

				String url_medicine = medicineNameAndUrlHashMap.get(name_medicine);
				int startNum = 0;
				while(true){
					ArrayList<Record> recordList = new ArrayList<Record>();
					recordList = GetRecords.GetRecordList(url_medicine);

					for(int i = 0; i < recordList.size(); i++){
						recordList.get(i).setMedicineName(name_medicine);
						//sql = "insert into testTable values(" 
						sql = "insert into testTable values(" 
								+ recordList.get(i).getId() + "," 
								+ "\"" + recordList.get(i).getSnippet() + "\"" + ","
								+ "\"" + recordList.get(i).getMedicineName() + "\"" + ","
								+ "\"" + recordList.get(i).getDiseaseName() + "\"" + ","
								+ "\"" + recordList.get(i).getSex() + "\"" + ","
								+ "\"" + recordList.get(i).getTitle_blog() + "\"" + ","
								+ "\"" + recordList.get(i).getTitle_blogArticle() + "\"" + ","
								+ "\"" + recordList.get(i).getUrl_blogArticle() + "\"" + ","
								+ "\"" + recordList.get(i).getAge() + "\"" + ","
								+ "\"" + recordList.get(i).getBlogArticle() + "\""
								+ ");";
						
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
						
						stm.executeUpdate(sql);
					}
					
					startNum += 10;
					url_medicine = url_medicine + "&start=" + startNum + "&SelectST=1";
					break;
					//if(!isExsistMedicinePage(url_medicine)){break;}
				}

			}
			
			

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			System.out.println("JDBCドライバのロードに失敗しました。");
		} catch (SQLException e) {
			System.out.println("MySQLに接続できませんでした。" + e);
		} finally {
			System.out.println("test");
			if (con != null) {
				try {
					con.close();
					System.out.println("MySQLのクローズに成功しました。");
				} catch (SQLException e) {
					System.out.println("MySQLのクローズに失敗しました。");
				}
			}
		}

	}

	//urlの中身を取得する関数
	public static StringBuffer getUrlContents(String page_url) throws Exception{

		//アクセスしたいページpage_url
		URL url = new URL(page_url);
		URLConnection conn = new URL(url.toString()).openConnection(proxy);

		//文字コード指定
		String charset = Arrays.asList(conn.getContentType().split(";") ).get(1);
		String encoding = Arrays.asList(charset.split("=") ).get(1);

		//url読み込み？
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), encoding )); 
		StringBuffer response = new StringBuffer();
		String line;
		while ((line= in.readLine()) != null) {response.append(line+"\n");}
		in.close();

		//中身を返す
		return response;
	}

	//url存在チェック
	public static boolean isExsistMedicinePage(String page_url) throws Exception{

		//正規表現で探索・該当部分をリターン
		StringBuffer response = new StringBuffer();
		response = getUrlContents(page_url);
		Pattern title_pattern1 = Pattern.compile("に一致する情報は見つかりませんでした。",Pattern.CASE_INSENSITIVE);
		Matcher matcher = title_pattern1.matcher(response.toString());

		return !matcher.find();

	}

}
