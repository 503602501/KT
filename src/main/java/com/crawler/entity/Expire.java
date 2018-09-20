package com.crawler.entity;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.alibaba.fastjson.JSONObject;


public class Expire {

	private Date date ;
	private String message;
	private boolean isExpire;
	
	public Expire(String object) {
		JSONObject jsonObject=JSONObject.parseObject(object);
		String d = (String) jsonObject.get("date");
		this.message = (String) jsonObject.get("message");
		
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMdd");
        try {
        	this.date=simpleDateFormat.parse(d);
        	
        } catch(ParseException px) {
            px.printStackTrace();
            this.isExpire = true;
            return ;
        }
        
        try {
        
		   TimeZone.setDefault(TimeZone.getTimeZone("GMT+8")); // 时区设置
		   URL url=new URL("https://bjtime.cn/");//取得资源对象
		   URLConnection uc=url.openConnection();//生成连接对象
		   uc.connect(); //发出连接
		   long ld=uc.getDate(); //取得网站日期时间（时间戳）
		   Date bjdate=new Date(ld); //转换为标准时间对象
		   
		   if(bjdate.before(this.date)){
			   isExpire = false;
		   }else{
			   isExpire = true;
		   }
		} catch (IOException e) {
			 isExpire = false; 
			e.printStackTrace();
		}
        
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public boolean isExpire() {
		return isExpire;
	}

	public void setExpire(boolean isExpire) {
		this.isExpire = isExpire;
	}

	public static void main(String[] args) throws Exception {
		Expire e = new Expire("{date:'20180907',message:'运行异常'}");
		System.out.println(e.isExpire);
	}
	
}
