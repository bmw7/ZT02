package com.mavict.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.util.Assert;

import com.mavict.PageInfo;
import com.mavict.PagedContent;

/**
 * 数据操作实现基类
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */

public class BaseDaoImpl<T,ID extends Serializable> implements BaseDao<T, ID>{
	
	public Class<T> entityClass; // 实体类类型
	
	@Resource
	public SessionFactory sessionFactory;
	
	/** 获取session */
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	/** 构造函数获取实体类类名称 */
	public BaseDaoImpl() {
		Type type = getClass().getGenericSuperclass();
		Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
		entityClass = (Class<T>) parameterizedType[0];		
	}
	
	@Override
	public void save(T entity) {
		Assert.notNull(entity);
		getSession().save(entity);
	}

	@Override
	public void delete(ID id) {
		delete(get(id));
	}

	@Override
	public void delete(T entity) {
		getSession().delete(entity);
	}

	@Override
	public void delete(String clause, Object... values) {
		String hql = "delete "+ entityClass.getSimpleName() + " " + clause;
		Query query = getSession().createQuery(hql);
		
		/* 设置占位符参数值  */
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);	
		}		
		query.executeUpdate();	
	}
	
	@Override
	public void update(T entity) {
		getSession().update(entity);
	}
	
	@Override
	public void update(String clause, Object... values) {
		String hql = "update "+ entityClass.getSimpleName() + " " + clause;
		Query query = getSession().createQuery(hql);
		
		/* 设置占位符参数值  */
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);	
		}		
		query.executeUpdate();
	}

	@Override
	public T get(ID id) {
		return (T) getSession().get(entityClass, id);
	}
	
	@Override
	public List<T> get() {
		String hql = "from "+entityClass.getSimpleName();
		return getSession().createQuery(hql).list();
	}
	
	@Override
	public T get(String column, Object value) {
		String hql = "from "+entityClass.getSimpleName()+" where "+column+" =:queryObject";
		Query query = getSession().createQuery(hql);
		query.setParameter("queryObject", value);
		T entity = (T) query.uniqueResult();
		if (entity == null) {  return null;  }
		return entity;
	}
	
	@Override
	public List<T> get(Integer start, Integer count, String clause, Object... values) {
		String hql = "from "+ entityClass.getSimpleName() + " " + clause;
		Query query = getSession().createQuery(hql);
		
		/* 设置占位符参数值  */
		for (int i = 0; i < values.length; i++) {
			
			/* 对Date类型额外设置 */
			if (values[i] instanceof Date) {
				query.setDate(i, (Date) values[i]);		
			}else{
				query.setParameter(i, values[i]);
			}	
		}	
		
		/* 设置起始条目 */
		query.setFirstResult(start);
		query.setMaxResults(count);
		
		return query.list();
	}
	
	
	@Override
	public List<T> get(String clause, Object... values) {
		String hql = "from "+ entityClass.getSimpleName() + " " + clause;
		Query query = getSession().createQuery(hql);
		
		/* 设置占位符参数值  */
		for (int i = 0; i < values.length; i++) {
			
			/* 对Date类型额外设置 */
			if (values[i] instanceof Date) {
				query.setDate(i, (Date) values[i]);		
			}else{
				query.setParameter(i, values[i]);
			}	
		}	
		
		return query.list();
	}
	
	
	@Override
	public PagedContent<T> getPagedContent(PageInfo pageInfo) {
		String hql = "from "+ entityClass.getSimpleName()+" order by id desc";
		Query query = getSession().createQuery(hql);
		query.setFirstResult((pageInfo.getPageNumber()-1)*pageInfo.getPageSize());
		query.setMaxResults(pageInfo.getPageSize());
		List<T> content = query.list();
		return new PagedContent(content,count(),pageInfo);
	}

	@Override
	public PagedContent<T> getPagedContent(PageInfo pageInfo,String column,Object value,String orderColumn,String sequence) {
		String hql = "from "+ entityClass.getSimpleName() +" where " + column + " =:queryValue order by "+ orderColumn + " " + sequence;
		Query query = getSession().createQuery(hql);
		query.setParameter("queryValue", value);
		query.setFirstResult((pageInfo.getPageNumber()-1)*pageInfo.getPageSize());
		query.setMaxResults(pageInfo.getPageSize());
		List<T> content = query.list();
		return new PagedContent(content,count(column,value),pageInfo);
	}

	@Override
	public PagedContent<T> getPagedContent(PageInfo pageInfo, String clause, Object... values) {
		String hql = "from "+ entityClass.getSimpleName() + " " + clause;
		Query query = getSession().createQuery(hql);
		
		/* 设置占位符参数值  */
		for (int i = 0; i < values.length; i++) {
			
			/* 对Date类型额外设置 */
			if (values[i] instanceof Date) {
				query.setDate(i, (Date) values[i]);		
			}else{
				query.setParameter(i, values[i]);
			}	
		}	
		
		query.setFirstResult((pageInfo.getPageNumber()-1)*pageInfo.getPageSize());
		query.setMaxResults(pageInfo.getPageSize());
		List<T> content = query.list();
		
		return new PagedContent(content,count(clause),pageInfo);
	}
	
	
	@Override
	public Long count() {
		String hql = "select count(*) from "+entityClass.getSimpleName();
		Query query = getSession().createQuery(hql);	
		return  (Long) query.uniqueResult();
	}

	
	@Override
	public Long count(String column, Object value) {
		String hql = "select count(*) from "+ entityClass.getSimpleName() + " where "+ column + " =:queryValue";
		Query query = getSession().createQuery(hql);
		query.setParameter("queryValue", value);	
		return  (Long) query.uniqueResult();
	}


	@Override
	public Long count(String clause) {
		String hql = "select count(*) from "+ entityClass.getSimpleName() + clause;
		Query query = getSession().createQuery(hql);	
		return  (Long) query.uniqueResult();
	}



	@Override
	public List<T> get(Integer count, String clause, Object... values) {
		return get(0, count, clause, values);
	}


}
