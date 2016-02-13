package com.mavict.article;

import java.util.List;

import com.mavict.PageInfo;
import com.mavict.PagedContent;
import com.mavict.base.BaseDao;

/**
 * 
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */

public interface ArticleDao extends BaseDao<Article, Integer> {
	/**
	 * 获取前Num条文章
	 * 
	 * @param categoryId 文章目录Id
	 * @param Num 前Num条
	 * 
	 * @return 文章实体List
	 * */
	List<Article> getNumList(Integer categoryId,Integer Num);
	
	/**
	 * 获取指定目录下的文章分页操作
	 * 
	 * @param categoryId 文章目录Id
	 * @param pageInfo 分页信息
	 * 
	 * @return 
	 * */
	PagedContent<Article> getPagedContentByCategoryId(Integer categoryId,PageInfo pageInfo);
	
	/**
	 * 获取指定目录下文章的数目
	 * 
	 * @param categoryId 文章目录Id
	 * */
	Long count(Integer categoryId);

}
