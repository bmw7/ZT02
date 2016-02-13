package com.mavict.setting;

import java.io.Serializable;

/**
 * 系统设置
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
public class Setting implements Serializable{
	
	private static final long serialVersionUID = 3816390024219546412L;
	
	public static final String  CACHE_NAME = "setting";  /** 缓存名称 */
	public static final Integer CACHE_KEY  =  0;         /** 缓存Key */
	
	private String  siteName; 	      /** 网站名称 */
    private String  siteUrl; 		  /** 网站网址 */
    private String  logo; 			  /** logo */
    private String  address; 		  /** 联系地址 */
    private String  phone; 			  /** 联系电话 */
    private String  zipCode; 		  /** 邮政编码 */
    private String  email; 			  /** E-mail */
    private String  certtext; 		  /** 备案编号 */
	
	private String  smtpFromMail;     /** 发件人邮箱 */
	private String  smtpHost;         /** SMTP服务器地址 */
	private Integer smtpPort;         /** SMTP服务器端口 */
	private String  smtpUsername;     /** SMTP用户名 */
	private String  smtpPassword;     /** SMTP密码 */
	
	private String  cookiePath;       /** Cookie路径 */
    private String  cookieDomain;     /** Cookie作用域 */
    
	private String  cnzzSiteId;       /** CNZZ统计站点ID */
	private String  cnzzPassword;     /** CNZZ统计密码 */
	
	private Boolean isDevelopment;    /** 是否开启开发模式 */
	
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getSiteUrl() {
		return siteUrl;
	}
	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCerttext() {
		return certtext;
	}
	public void setCerttext(String certtext) {
		this.certtext = certtext;
	}
	public String getSmtpFromMail() {
		return smtpFromMail;
	}
	public void setSmtpFromMail(String smtpFromMail) {
		this.smtpFromMail = smtpFromMail;
	}
	public String getSmtpHost() {
		return smtpHost;
	}
	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}
	public Integer getSmtpPort() {
		return smtpPort;
	}
	public void setSmtpPort(Integer smtpPort) {
		this.smtpPort = smtpPort;
	}
	public String getSmtpUsername() {
		return smtpUsername;
	}
	public void setSmtpUsername(String smtpUsername) {
		this.smtpUsername = smtpUsername;
	}
	public String getSmtpPassword() {
		return smtpPassword;
	}
	public void setSmtpPassword(String smtpPassword) {
		this.smtpPassword = smtpPassword;
	}
	public String getCookiePath() {
		return cookiePath;
	}
	public void setCookiePath(String cookiePath) {
		this.cookiePath = cookiePath;
	}
	public String getCookieDomain() {
		return cookieDomain;
	}
	public void setCookieDomain(String cookieDomain) {
		this.cookieDomain = cookieDomain;
	}
	public String getCnzzSiteId() {
		return cnzzSiteId;
	}
	public void setCnzzSiteId(String cnzzSiteId) {
		this.cnzzSiteId = cnzzSiteId;
	}
	public String getCnzzPassword() {
		return cnzzPassword;
	}
	public void setCnzzPassword(String cnzzPassword) {
		this.cnzzPassword = cnzzPassword;
	}
	public Boolean getIsDevelopment() {
		return isDevelopment;
	}
	public void setIsDevelopment(Boolean isDevelopment) {
		this.isDevelopment = isDevelopment;
	}
}
