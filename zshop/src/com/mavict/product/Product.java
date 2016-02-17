package com.mavict.product;

import javax.persistence.Entity;

import com.mavict.base.BaseEntity;

/**
 * 
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */

@Entity
public class Product extends BaseEntity {

	private static final long serialVersionUID = -1868294604972755215L;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
