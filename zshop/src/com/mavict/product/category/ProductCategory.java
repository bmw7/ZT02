package com.mavict.product.category;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.mavict.category.Category;

/**
 * 
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */

@Entity
@Table(name = "product_category")
public class ProductCategory extends Category<ProductCategory> {

	private static final long serialVersionUID = 4270353841489090714L;
	
	private String city;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
