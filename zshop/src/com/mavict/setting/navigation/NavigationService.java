package com.mavict.setting.navigation;

import java.util.List;

import com.mavict.article.category.ArticleCategory;
import com.mavict.base.BaseService;

/**
 * 
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */

public interface NavigationService extends BaseService<Navigation, Integer> {
	/** 获取分类树*/
	List<Navigation> getTreeService();
	
	/** 获取下级分类*/
	List<Navigation> getChildren(List<Navigation> navigations,Navigation navigation);
	
	/** 判断是否有下级分类*/
	boolean hasChildren(List<Navigation> navigations,Navigation navigation);
	
	/** 判断navigation是否有下级分类*/
	boolean doIsChildService(Integer parentId);
	
	/** 将文章目录放入导航目录*/
	void saveCategoryService(Navigation navigation,ArticleCategory articleCategory);
	
	/** 将文章子栏目放入导航目录*/
	void saveChildService(Navigation navigation,Navigation parent,ArticleCategory articleCategory);
	
	/** 调整顺序 */
	void updateSequenceService(Integer myId,Integer rpId,Integer myOrders,Integer rpOrders);
	
	/** 得到按照塞满各级子栏目的所有实体 */
	List<Navigation> getChildedAllService();
}
