package com.mavict.test;

import com.mavict.category.Category;

/**
 * 
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
public class MyCategory extends Category<MyCategory> {

	private static final long serialVersionUID = 7211908974931647106L;
	private String city;
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
}
