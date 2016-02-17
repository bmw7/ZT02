package com.mavict.test;

import com.jhlabs.composite.AddComposite;
import com.mavict.category.Category;

/**
 * 
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
public class TestV {

	public static void main(String[] args) {
		System.out.println("2+3+4 ="+add(2, 3, 4));
		System.out.println("2 ="+add(2));
		
		MyCategory myCategory = new MyCategory();
		myCategory.setName("zhangtao");
		myCategory.setCity("rizhao");
		
		Category<MyCategory> category = myCategory;
		System.out.println("-------"+category);
	}
	
	public static Integer add(Integer base, Integer...values){
		int sum = base;
		for (int i = 0; i < values.length; i++) {
			sum += values[i];
		}
		return sum;
	}

}
