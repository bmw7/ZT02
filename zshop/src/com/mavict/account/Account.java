package com.mavict.account;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Table;
import org.springframework.format.annotation.DateTimeFormat;

import com.mavict.account.authc.AccountRole;
import com.mavict.base.BaseEntity;

/**
 * Account 管理员实体
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */ 
@Entity
@DynamicUpdate(value = true)
public class Account extends BaseEntity{
	private static final long serialVersionUID = -760532366266114347L;
    
	private String username;   // 用户名
	private String password;   // 密码 
	private String email;      // E-mail 
	private String name;       // 姓名 
	private String department; // 部门 
	private String loginIp;    // 最后登录IP 
	private Integer loginCount; //登陆次数
	private Integer loginFailureCount; // 连续登录失败次数 
	private Boolean isEnabled; // 是否启用 
	private Boolean isLocked;  // 是否锁定
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lockedDate;   // 锁定日期 
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createDate;   // 创建日期 
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date modifyDate;   // 修改日期 
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date loginDate;    // 最后登录日期 
	
	private AccountRole accountRole; //拥有的权限
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "accountRoleId")
	public AccountRole getAccountRole() {
		return accountRole;
	}
	public void setAccountRole(AccountRole accountRole) {
		this.accountRole = accountRole;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public Integer getLoginCount() {
		return loginCount;
	}
	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}
	public Integer getLoginFailureCount() {
		return loginFailureCount;
	}
	public void setLoginFailureCount(Integer loginFailureCount) {
		this.loginFailureCount = loginFailureCount;
	}
	public Boolean getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	public Boolean getIsLocked() {
		return isLocked;
	}
	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}
	public Date getLockedDate() {
		return lockedDate;
	}
	public void setLockedDate(Date lockedDate) {
		this.lockedDate = lockedDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

}
