package com.mavict.setting.mail;

import java.io.Serializable;

/**
 * 邮件
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */

public class Mail implements Serializable{
	
	private static final long serialVersionUID = -2947183390407008617L;
	   
	public static final String ENCODEING = "UTF-8";  
	protected String host;     // 服务器地址  
	protected String sender;   // 发件人的邮箱  
	protected String receiver; // 收件人的邮箱  
	protected String name;     // 发件人昵称  
	protected String username; // 账号  
	protected String password; // 密码  
	protected String subject;  // 主题  
	protected String message;  // 信息(支持HTML)  
	   
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
