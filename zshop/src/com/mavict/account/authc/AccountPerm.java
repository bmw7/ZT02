package com.mavict.account.authc;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.mavict.base.BaseEntity;

/**
 * 
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
@Entity
public class AccountPerm extends BaseEntity{

	private static final long serialVersionUID = 8989442217358713487L;

	private String name;
	private List<AccountRole> accountRoles = new ArrayList<AccountRole>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToMany
	@JoinTable(name = "accountRoleAccountPerm", inverseJoinColumns = @JoinColumn(name = "accountRoleId"), joinColumns = @JoinColumn(name = "accountPermId"))
	public List<AccountRole> getAccountRoles() {
		return accountRoles;
	}
	public void setAccountRoles(List<AccountRole> accountRoles) {
		this.accountRoles = accountRoles;
	}
	
}
