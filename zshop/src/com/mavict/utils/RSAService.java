package com.mavict.utils;

import java.security.interfaces.RSAPublicKey;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

/**
 * 
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */

public interface RSAService {

	/** 生成密钥(添加私钥至Session并返回公钥) */
	RSAPublicKey generateKey(HttpServletRequest request);

	/** 移除私钥 */
	void removePrivateKey(HttpServletRequest request);

	/** 解密参数 */
	String decryptParameter(String name, HttpServletRequest request);
	
	/** 生成前台页面js公钥所需的两个参数，并压入model*/
	void createPublicKeyModelParams(HttpServletRequest request,ModelMap model);

}