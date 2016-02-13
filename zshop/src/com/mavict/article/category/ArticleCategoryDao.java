package com.mavict.article.category;

import java.util.List;

import com.mavict.base.BaseDao;

/**
 * 
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */

public interface ArticleCategoryDao extends BaseDao<ArticleCategory, Integer> {
	/**  获取所有分类  */
	List<ArticleCategory> getAll();
}
