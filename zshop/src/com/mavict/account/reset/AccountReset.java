package com.mavict.account.reset;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.mavict.account.Account;
import com.mavict.base.BaseEntity;

/**
 * 密码找回修改
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */

@Entity
public class AccountReset implements Serializable{

	private static final long serialVersionUID = 5767721779109076140L;
	private Integer id;
	private Account account;     // 用户
	private Date expireDate;     // 过期时间
	private String validateCode; // 验证字符串
	
	public AccountReset(){
		super();
	};
	
	public AccountReset(Account account, Date expireDate, String validateCode) {
		super();
		this.account = account;
		this.expireDate = expireDate;
		this.validateCode = validateCode;
	}
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "accountId")
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	public String getValidateCode() {
		return validateCode;
	}
	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}
	
}
