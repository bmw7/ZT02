package com.mavict.article;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mavict.PageInfo;
import com.mavict.PagedContent;
import com.mavict.base.BaseDao;
import com.mavict.base.BaseServiceImpl;

/**
 * 
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
@Service
public class ArticleServiceImpl extends BaseServiceImpl<Article, Integer> implements ArticleService {

	@Resource(name = "articleDaoImpl")
	private ArticleDao articleDao;
	
	@Override
	@Resource(name = "articleDaoImpl")
	public void setBaseDao(BaseDao<Article, Integer> baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	public List<Article> getNumListService(Integer categoryId,Integer Num) {
		return articleDao.getNumList(categoryId,Num);
	}

	@Override
	public PagedContent<Article> getPagedContentByCategoryIdService(Integer categoryId, PageInfo pageInfo) {
		return articleDao.getPagedContentByCategoryId(categoryId, pageInfo);
	}

	
}
