package com.mavict.utils;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import com.mavict.setting.mail.Mail;


/**
 * 邮件工具类
 * 
 * 若用QQ邮箱报错: 454 Authentication failed, please open smtp flag first! 开启下QQ邮箱的smtp服务器设置即可。
 * 参考 blog.csdn.net/bruce128/article/details/8761949
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */

public final class MailUtils {
	
	/** 不可实例化  */
	private MailUtils() {
		
	}
	
	public static String send(Mail mail) {  
        // 发送email  
        HtmlEmail email = new HtmlEmail();  
        try {        
            email.setHostName(mail.getHost());    // 这里是SMTP发送服务器的名字：163的如下："smtp.163.com"   
            email.setCharset(Mail.ENCODEING);     // 字符编码集的设置
            email.addTo(mail.getReceiver());      // 收件人的邮箱              
            email.setFrom(mail.getSender(), mail.getName()); // 发送人的邮箱               
            email.setAuthentication(mail.getUsername(), mail.getPassword());  // 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码             
            email.setSubject(mail.getSubject());  // 要发送的邮件主题             
            email.setMsg(mail.getMessage());      // 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签             
            email.send();                         // 发送                
            return "发送成功！请检查您的邮箱.";          // 前台中文乱码
        } catch (EmailException e) {  
            e.printStackTrace();  
            return "发送失败!"; 
        }  
    }  
}
