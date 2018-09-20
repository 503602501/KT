package com.crawler.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilterUtil {
	
	
	public static String getRegexContent(String content, String regex) {
		return getRegexContent(content, regex, "");
	}
	
	public static String getRegexContent(String content, String regex, String params) {

		if(content == null){
			return "";
		}
		if (regex.indexOf("match") == 0) { // 正则表达式匹配字符串
			String regexMatch = regex.replace("match|", "");
			Pattern p = Pattern.compile(regexMatch);
			Matcher m = p.matcher(content);
			if (m.find()) {
				content = m.group(0);
			}
		}

		if (regex.indexOf("replace") == 0) {
			String regexReplace = regex.replace("replace|", "");
			System.out.println(content);
			content = content.replaceAll(regexReplace, params);
		}

		if (regex.indexOf("prefix") == 0) {
			String prefix =  regex.replace("prefix|", "");
			if(!content.startsWith(prefix)){
				content = prefix + content;
			}
		}
		
		return content;
	}
	public static void main(String[] args) {
		String s = "2016-01-01 00:00:00 - 2099-12-31 00:00:00".split(" - ")[1];
		System.out.println(s);
		
		System.out.println(getRegexContent("//fuss10.elemecdn.com/1/f1/823a7d168c8e634ff53805175878fjpeg.jpeg?imageMogr/format/webp/thumbnail/!140x140r/gravity/Center/crop/140x140/", "match|(.+?)jpeg\\?"));
	}
}
