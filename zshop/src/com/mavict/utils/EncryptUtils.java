package com.mavict.utils;

import java.security.MessageDigest;

/**
 * 
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */

public class EncryptUtils {

	private EncryptUtils(){}
	
	@SuppressWarnings("finally")
	public static String encrypt(String str){
		MessageDigest md5;
		MessageDigest sha;
		str = str+"mavictcorporation"; // 对加密数据加盐
		byte s[] = null;

		try {
			md5 = MessageDigest.getInstance("MD5");
			sha = MessageDigest.getInstance("SHA");
			md5.update(str.getBytes("UTF8"));  // md5方式加密数据
			sha.update(md5.digest());  // sha方式加密md5加密之后的数据
			s = sha.digest();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			
		} finally{
			StringBuffer sb = new StringBuffer();
			for(int i = 0;i < s.length;i++){
				sb.append(Integer.toHexString(0xff & s[i]));
			}	
			return sb.toString();
		}

	}
	
}
