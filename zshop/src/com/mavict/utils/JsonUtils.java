package com.mavict.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Json 工具
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
public final class JsonUtils {
	
	/** 不可实例化  */
	private JsonUtils() {

	}
	
	/** 通过json网址获得json字符串数据 */
	public static String getGson(String url){
		StringBuilder json = new StringBuilder();  
		try {  
		    URL urlObject = new URL(url);  
		    URLConnection uc = urlObject.openConnection();  
		    BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));  
		    String inputLine = null;  
		    while ( (inputLine = in.readLine()) != null) {  
		        json.append(inputLine);  
		    }  
		    in.close();  
		} catch (MalformedURLException e) {  
		    e.printStackTrace();  
		} catch (IOException e) {  
		    e.printStackTrace();  
		}  
		return json.toString();  
	}
}
