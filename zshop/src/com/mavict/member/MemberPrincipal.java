package com.mavict.member;

import java.io.Serializable;

/**
 * 身份信息
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
public class MemberPrincipal implements Serializable {
	
	private static final long serialVersionUID = 1219499482736616149L;
	
	/* ID */
	private Integer id;
	
	/* 用户名 */
	private String username;
	
	/* 姓名或昵称 */
	private String name;
	
	/* 电子邮件 */
	private String email;
	
	/* 手机号码 */
	private String phone;
	
	public MemberPrincipal(Integer id, String username, String name, String email, String phone) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.phone = phone;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
