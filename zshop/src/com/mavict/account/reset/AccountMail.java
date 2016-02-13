package com.mavict.account.reset;

import com.mavict.setting.mail.Mail;


/**
 * 管理员找回密码的专用邮件类
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */

public class AccountMail extends Mail{

	private static final long serialVersionUID = 1522384106244104013L;  
	   
	/** 构造方法 */
	public AccountMail(String receiver, String message) {
		super();
		this.receiver = receiver;              // 收件人的邮箱 
		this.message  = message;               // 信息(支持HTML) 
		
		this.sender   = "51556526@qq.com";    // 发件人的邮箱 
		this.name     = "沧海软件";            // 发件人昵称
		this.username = "51556526@qq.com";    // 账号  
		this.password = "vtsinghua1979";      // 密码  
		this.subject  = "管理密码修改";        // 主题  
		this.host     = "smtp.qq.com";
	}
	
}
