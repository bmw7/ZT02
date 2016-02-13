package com.mavict.guestbook;

import java.util.Date;

import javax.persistence.Entity;

import com.mavict.base.BaseEntity;

/**
 * 访客留言
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
@Entity
public class Guestbook extends BaseEntity{
	private static final long serialVersionUID = 626781306855638708L;

	private String phone;    // 手机号码
	private String email;    // 电子邮件
	private Date createDate; // 留言时间
	private String ip;       // 留言IP
	private String message;  // 留言内容
	private String reply;    // 回复内容

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
