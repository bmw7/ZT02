package com.mavict.article;

import java.util.List;

import com.mavict.PageInfo;
import com.mavict.PagedContent;
import com.mavict.base.BaseService;

/**
 * 
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */

public interface ArticleService extends BaseService<Article, Integer> {

	/**
	 * 获取前Num条文章
	 * 
	 * @param categoryId 文章目录Id
	 * @param Num 前Num条
	 * 
	 * @return 文章实体List
	 * */
	List<Article> getNumListService(Integer categoryId,Integer Num);
	
	/**
	 * 获取指定目录下的文章分页操作
	 * 
	 * @param categoryId 文章目录Id
	 * @param pageInfo 分页信息
	 * 
	 * @return 
	 * */
	PagedContent<Article> getPagedContentByCategoryIdService(Integer categoryId,PageInfo pageInfo);
	
	
}
