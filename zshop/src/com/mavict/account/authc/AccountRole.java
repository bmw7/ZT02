package com.mavict.account.authc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.mavict.account.Account;
import com.mavict.base.BaseEntity;

/**
 * [角色] 不同角色拥有不同的管理权限。不同用户属于不同的角色，因而拥有不同的权限。
 * 
 * @accounts - 一对多关系
 * @accountPerms - 多对多关系
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
@Entity
public class AccountRole extends BaseEntity{

	private static final long serialVersionUID = 2430878808297080665L;
	private String name;
	private String description;
	private Date createDate;
	
	private List<Account> accounts = new ArrayList<Account>();
	private List<AccountPerm> accountPerms = new ArrayList<AccountPerm>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(mappedBy = "accountRole")
	public List<Account> getAccounts() {
		return accounts;
	}
	
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL) /** 会全删除perm待研*/
	@JoinTable(name = "accountRoleAccountPerm", inverseJoinColumns = @JoinColumn(name = "accountPermId"), joinColumns = @JoinColumn(name = "accountRoleId"))
	public List<AccountPerm> getAccountPerms() {
		return accountPerms;
	}
	public void setAccountPerms(List<AccountPerm> accountPerms) {
		this.accountPerms = accountPerms;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
