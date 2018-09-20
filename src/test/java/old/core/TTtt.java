package old.core;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class TTtt {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		
		
		String html = "<p>An <a href='http://example.com/'><b>example</b></a> link.</p>";
		Document doc = Jsoup.parse(html);//解析HTML字符串返回一个Document实现
		Element link = doc.select("a").first();//查找第一个a元素
		String text = doc.body().text(); // "An example link"//取得字符串中的文本
		System.out.println(text);
		
//		String result = Xsoup.compile("//a/@href").evaluate(doc).get();
		   
//		HashMap map =new HashMap();
//		ThreadsTest test = new ThreadsTest();
//		test.start();
//		Thread.sleep(3000);
//		test.wait(5000);
//		test.notify();
//		System.out.println("---结束--");
	}

}
