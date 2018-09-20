package com.crawler.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	public static boolean isEmpty(Object object) {
		if(object==null|| "".equals(object.toString().trim())){
			return true;
		}
		return false;

	}
 
	public static String delHtml(String text) {
		   String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式 
	        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式 
	        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式 
	         
	        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
	        Matcher m_script=p_script.matcher(text); 
	        text=m_script.replaceAll(""); //过滤script标签 
	         
	        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
	        Matcher m_style=p_style.matcher(text); 
	        text=m_style.replaceAll(""); //过滤style标签 
	         
	        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
	        Matcher m_html=p_html.matcher(text); 
	        text=m_html.replaceAll(""); //过滤html标签 
	        text=text.replaceAll("\r|\n", "");
	        text=text.replaceAll("&nbsp;", "");
	        return text.replaceAll(" +"," "); //返回文本字符串 
	}
}
