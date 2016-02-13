package com.mavict.article.category;

import java.util.List;

import com.mavict.base.BaseService;

/**
 * 
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */

public interface ArticleCategoryService extends BaseService<ArticleCategory, Integer> {
	
	/** 获取分类树*/
	List<ArticleCategory> getTreeService();
	
	/** 获取下级分类*/
	List<ArticleCategory> getChildren(ArticleCategory articleCategory);
	
	/** 判断是否有下级分类*/
	boolean hasChildren(ArticleCategory articleCategory);
	
	List<ArticleCategory> getCategoryList();
	
	/** 调整顺序 */
	void updateSequenceService(Integer myId,Integer rpId,Integer myOrders,Integer rpOrders);
	
}
