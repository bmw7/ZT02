package com.mavict.article.category;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mavict.base.BaseDaoImpl;

/**
 * 
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
@Repository
public class ArticleCategoryDaoImpl extends BaseDaoImpl<ArticleCategory, Integer> implements ArticleCategoryDao {
	@Override
	public List<ArticleCategory> getAll() {
		return getSession().createQuery("from ArticleCategory order by orders asc").list();
	}
}
