package com.mavict.member;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.mavict.base.BaseEntity;
import com.mavict.member.account.MemberAccount;

/**
 * 会员
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */

@Entity
public class Member extends BaseEntity {
	
	private static final long serialVersionUID = -6011048744942636050L;
	
	/* 身份信息名称 - 用于session  */
	public static final String PRINCIPAL_NAME = Member.class.getName() + ".PRINCIPAL";
	
	/* 客户端 用户Cookie名称  */
	public static final String COOKIE_NAME = "member";
	
	/* 账号  */
	private String username;
	
	/* 密码  */
	private String password;
	
	/* 邮件  */
	private String email;
	
	/* 电话  */
	private String phone;
	
	/* 地址  */
	private String address;
	
	/* 姓名  */
	private String name;
	
	/* 可用金额  */
	private Float money;
	
	/* 冻结金额  */
	private Float freeze;
	
	/* 资金账户  */
	private List<MemberAccount> memberAccounts = new ArrayList<MemberAccount>();
	
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getMoney() {
		return money;
	}
	public void setMoney(Float money) {
		this.money = money;
	}
	public Float getFreeze() {
		return freeze;
	}
	public void setFreeze(Float freeze) {
		this.freeze = freeze;
	}
	@OneToMany(mappedBy = "member")
	public List<MemberAccount> getMemberAccounts() {
		return memberAccounts;
	}
	public void setMemberAccounts(List<MemberAccount> memberAccounts) {
		this.memberAccounts = memberAccounts;
	}
	
}
