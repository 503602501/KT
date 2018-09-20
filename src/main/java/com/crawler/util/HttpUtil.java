package com.crawler.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;




public class HttpUtil {

	private static int socketTimeout = 10000;  //读超时时间（等待数据时间）
	private static int connectTimeout = 10000; //连接超时时间  
	private static int connectionRequestTimeout = 10000;  //从连接池中获取连接的等待时间
	private static int maxConnTotal = 300;   //最大不要超过1000  
	private static int maxConnPerRoute = 200;//路由的实际的单个连接池大小，如tps定为50，那就配置50  
	
	private static final Logger logger =  Logger.getLogger(HttpUtil.class);
	private static 	CloseableHttpClient httpclient ;
	private static SSLContext sslContext ;
	static{
		
/*	 	*//*******第一种https验证******//*
		 try {
			 sslContext = SSLContexts.custom().loadTrustMaterial(null, new TrustStrategy() {

				@Override
				public boolean isTrusted( java.security.cert.X509Certificate[] arg0, String arg1) throws java.security.cert.CertificateException {
					return true;
				}
			 
			   }).build();
//			 sslContext = SSLContext.getInstance("TLS");
			 sslContext = SSLContext.getInstance("SSL");
		} catch (Exception e1) {
			e1.printStackTrace();
		} */
			sslContext = ImageUtil.createIgnoreVerifySSL();
		
		RequestConfig config = RequestConfig.custom()  
		         .setSocketTimeout(socketTimeout)  
		         .setConnectTimeout(connectTimeout)  
		         .setConnectionRequestTimeout(connectionRequestTimeout).build();  
		httpclient = HttpClients.custom().setDefaultRequestConfig(config)  
		         .setMaxConnTotal(maxConnTotal)  
		         .setSslcontext(sslContext)
		         .setMaxConnPerRoute(maxConnPerRoute).build();  
//		httpclient.getConnectionManager().shutdown(); 未做关闭不知道是否有问题
	}
	
	
	/**
	 * 描述:获取html的内容片段
	 * 
	 * @param httpPath
	 *            :请求路径
	 * @return
	 * @throws Exception
	 */
	public static String getHtmlContent(String httpPath) throws Exception {

		
		if(StringUtils.isEmpty(httpPath)){
			return "";
		}
		SslUtils.ignoreSsl();
		// 一般爬虫请求都用Get，Get请求在HTTP请求协议里代表安全的查看:这个请求对象里可以添加http的请求头等
		HttpGet httpGet = new HttpGet(httpPath.trim());

		// 设置Get请求头的 User-Agent (模拟代理浏览器信息)
		httpGet.setHeader( "User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:56.0) Gecko/20170101 Firefox/56.0");

		// 用浏览器模拟对象httpClient，发送一个Get请求:可以通过这个响应对象获得很多http的响应信息
		InputStream is = null;
		FileOutputStream outputStream = null;
		CloseableHttpResponse respond = null;
		try {
			respond = httpclient.execute(httpGet);
			respond.setHeader("Connection", "close");
			if (HttpStatus.SC_OK != respond.getStatusLine().getStatusCode()) {
				httpGet.abort();
				logger.error("请求异常:" + httpPath + "," + respond.getStatusLine().getStatusCode());
				return null;
			}
			// 获取返回的网页实体
			HttpEntity entity = respond.getEntity();
			if (entity != null) {
				is = entity.getContent();
				return IOUtils.toString(is,Charset.forName("UTF-8"));
			} else {
				logger.error("请求异常:" + httpPath + "," + respond.getStatusLine().getStatusCode());
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("请求异常：" + httpPath + "," + e.getMessage(), e);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException io) {
				logger.error("关闭异常：" + httpPath + "," + io.getMessage(), io);
			}

			try {
				if (respond != null) {
					respond.close();
				}
			} catch (Exception e) {
				logger.error("关闭异常：" + httpPath + "," + e.getMessage(), e);
			}

			try {
				if (outputStream != null) {
					outputStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("关闭异常：" + httpPath + "," + e.getMessage(), e);
			}
		}
		return null;
	}
	
	/**
	 * 描述:获取html的内容片段
	 * 
	 * @param httpPath
	 *            :请求路径
	 * @return
	 * @throws Exception
	 */
	public static String postHtmlContent(String httpPath,List<NameValuePair> urlParameters) throws Exception {
		
		// 一般爬虫请求都用Get，Get请求在HTTP请求协议里代表安全的查看:这个请求对象里可以添加http的请求头等
		HttpPost httpPost= new HttpPost(httpPath.trim());
		
		// 设置Get请求头的 User-Agent (模拟代理浏览器信息)
		httpPost.setHeader( "User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:56.0) Gecko/20170101 Firefox/56.0");
//		httpPost.setHeader("Referer", "http://www.dataoke.com/qlist/?px=zh&tm_jpmj=tm&xlqj=10000&page=1");
		// 用浏览器模拟对象httpClient，发送一个Get请求:可以通过这个响应对象获得很多http的响应信息
		InputStream is = null;
		FileOutputStream outputStream = null;
		CloseableHttpResponse respond = null;
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(urlParameters));
			respond = httpclient.execute(httpPost);
			respond.setHeader("Connection", "close");
			if (HttpStatus.SC_OK != respond.getStatusLine().getStatusCode()) {
				httpPost.abort();
				logger.error("请求异常:" + httpPath + "," + respond.getStatusLine().getStatusCode());
				return null;
			}
			// 获取返回的网页实体
			HttpEntity entity = respond.getEntity();
			if (entity != null) {
				is = entity.getContent();
				return IOUtils.toString(is);
			} else {
				logger.error("请求异常:" + httpPath + "," + respond.getStatusLine().getStatusCode());
				return null;
			}
		} catch (Exception e) {
			logger.error("请求异常：" + httpPath + "," + e.getMessage(), e);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException io) {
				logger.error("关闭异常：" + httpPath + "," + io.getMessage(), io);
			}
			
			try {
				if (respond != null) {
					respond.close();
				}
			} catch (Exception e) {
				logger.error("关闭异常：" + httpPath + "," + e.getMessage(), e);
			}
			
			try {
				if (outputStream != null) {
					outputStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("关闭异常：" + httpPath + "," + e.getMessage(), e);
			}
		}
		return null;
	}
	/**
	 * @param args
     * @throws Exception 
	 */ 
	public static void main(String[] args) throws Exception {
		LogUtil.initLog(); //初始化日志配置文件
		HashMap<String, String> map = new HashMap<>();
		map.put("data_tk_zs_id", "6683992");
		
		List<NameValuePair> urlParameters = new ArrayList<>();
//		urlParameters.add(new BasicNameValuePair("data_tk_zs_id", "6649819"));  // 6584749 6649819
		
		String url = "https://www.fanatics.com/nfl/arizona-cardinals/arizona-cardinals-nike-womens-custom-game-jersey-black/o-3516+t-47598953+p-23496419431+z-9-1146480319";
//		String content = HttpUtil.getHtmlContent("https://mp.weixin.qq.com/s?src=11&timestamp=1528246801&ver=921&signature=AmtjkNZunwl9JNtjHAOHQASobvabGG4tP3X*I*cw3SO4NZi*g42FK*RZN9Sygp62MnhLFE8ePpeFf1A46xCM72fpTijbE-ma-i9d3ySB5vC43*ah1Sl9BlhOtP0aOQU6&new=1");
		String content = HttpUtil.getHtmlContent(url);
		System.out.println(content);
//		String content = HttpUtil.getHtmlContent("https://www.designbyhumans.com/shop/sticker/baby-gpu-on-board-sticker/791282/");
		 
		
//		System.out.println(object);
	/*	for (String key : object.keySet()) {
			String value = object.getString(key);
			JSONArray array = (JSONArray) JSONArray.parse(value);
			object = (JSONObject) array.get(0);
			break;
		}
		for (String key : object.keySet()) {
			String value = object.getString(key);
			object = (JSONObject) JSONObject.parse(value) ;
			break;
		}
		
		String zoom  ="";
		for (String key : object.keySet()) {
			String value = object.getString(key);
			JSONArray array = (JSONArray) JSONObject.parse(value) ;
			object = (JSONObject) array.get(0);
			for (String k : object.keySet()) {
				value  = object.getString(k);
				if(value.indexOf("1200x1200")==-1){
					zoom=value;
					break;
				}
			}
			break;
		}
		
		zoom = zoom.substring(zoom.indexOf("https"));
		zoom = zoom.replace("}", "");
		zoom = zoom.replace("\"", "");
		
		System.out.println(zoom);
		*/
		
//		String filter = content.substring(0, content.indexOf("<span>下一页</span>"));
//		filter = filter.substring(filter.lastIndexOf("next"));
//		System.out.println(FilterUtil.getRegexContent(filter, "match|(?<=pn)(.+?)(?=/)"));
		
//		content=content.replaceAll("\r|\n", "");
//		content =content.substring(content.indexOf("numberofresults"),content.indexOf("Fitments"));
//		System.out.println(content);
		

		
		/*if(content.indexOf("dropAllAttributes")!=-1){
			content = content.substring(content.indexOf("dropAllAttributes"));
			content=content.substring(0,content.indexOf("</select>"));
			for (String item : content.split("<option")) {
			}
		}*/
//		String s  = FilterUtil.getRegexContent(content, "match|dropAllAttributes.*");
//		System.out.println(content);
//		ImageUtil.uploadImage(URL, "D:\\emp\\"+System.currentTimeMillis()+".jpg");
		/*for (int i = 0; i < 3000; i++) {
			String content = HttpUtil.getHtmlContent("http://www.dataoke.com/gettpl?gid=5530726&_="+System.currentTimeMillis());
			String s = content.substring(content.lastIndexOf("http"));
			s = FilterUtil.getRegexContent(s, "match|(.+?)(?=</a>)");
			System.out.println(i+":"+s);
			
		}*/
	} 
}
