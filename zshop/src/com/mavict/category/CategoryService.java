package com.mavict.category;

import java.util.List;

import com.mavict.base.BaseService;

/**
 * 
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
public interface CategoryService<T> extends BaseService<T, Integer> {
	/** 获取分类树*/
	List<T> getTreeService();
	
	/** 获取下级分类*/
	List<T> getChildren(T category);
	
	/** 判断是否有下级分类*/
	boolean hasChildren(T category);
	
	List<T> getCategoryList();
	
	/** 调整顺序 */
	void updateSequenceService(Integer myId,Integer rpId,Integer myOrders,Integer rpOrders);
}
