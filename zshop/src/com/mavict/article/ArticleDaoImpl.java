package com.mavict.article;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.mavict.PageInfo;
import com.mavict.PagedContent;
import com.mavict.base.BaseDaoImpl;

/**
 * 
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
@Repository
public class ArticleDaoImpl extends BaseDaoImpl<Article, Integer> implements ArticleDao {

	@Override
	public List<Article> getNumList(Integer categoryId,Integer Num) {
		String hql = "from Article where articleCategoryId=:categoryId order by id desc";
		Query query = getSession().createQuery(hql);
		query.setParameter("categoryId", categoryId);
		query.setFirstResult(0);  
        query.setMaxResults(Num);  
		return query.list();
	}

	@Override
	public PagedContent<Article> getPagedContentByCategoryId(Integer categoryId, PageInfo pageInfo) {
		String hql = "from Article where articleCategoryId =:categoryId order by id desc";
		Query query = getSession().createQuery(hql);
		query.setParameter("categoryId", categoryId);
		query.setFirstResult((pageInfo.getPageNumber()-1)*pageInfo.getPageSize());
		query.setMaxResults(pageInfo.getPageSize());
		List<Article> content = query.list();
		return new PagedContent(content,count(categoryId),pageInfo);
	}

	@Override
	public Long count(Integer categoryId) {
		String hql = "select count(*) from Article where articleCategoryId =:categoryId";
		Query query = getSession().createQuery(hql);
		query.setParameter("categoryId", categoryId);
		return (Long) query.uniqueResult();
	}

}
