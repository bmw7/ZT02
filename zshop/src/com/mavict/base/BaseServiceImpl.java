package com.mavict.base;

import java.io.Serializable;
import java.util.List;

import com.mavict.PageInfo;
import com.mavict.PagedContent;


/**
 * 服务实现基类
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */

public class BaseServiceImpl<T,ID extends Serializable> implements BaseService<T, ID> {

	/** baseDao */
	public BaseDao<T, ID> baseDao;
	
	/**
	 * 1、实体类要继承本类，必须在 实体类ServiceImpl 中手动注入 实体类DaoImpl，才能做下面的事情
	 * 2、baseDaoImpl无法注入
	 * */
	public void setBaseDao(BaseDao<T, ID> baseDao) {
		this.baseDao = baseDao;
	}
	
	@Override
	public void saveService(T entity) {
		baseDao.save(entity);
	}

	@Override
	public void deleteService(ID id) {
		baseDao.delete(id);	
	}

	@Override
	public void deleteService(T entity) {
		baseDao.delete(entity);
	}

	@Override
	public void deleteService(String clause, Object... values) {
		baseDao.delete(clause, values);
	}
	
	@Override
	public void updateService(T entity) {
		baseDao.update(entity);	
	}
	
	@Override
	public void updateService(String clause, Object... values) {
		baseDao.update(clause, values);
	}

	@Override
	public T getService(ID id) {
		return baseDao.get(id);
	}
	
	@Override
	public T getService(String columnName, Object queryObject) {
		return baseDao.get(columnName, queryObject);
	}
	
	@Override
	public List<T> getService() {
		return baseDao.get();
	}
	
	@Override
	public List<T> getService(String column, Object value, String orderColumn) {
		return baseDao.get(column, value, orderColumn);
	}

	@Override
	public List<T> getService(String column, Object value, String orderColumn, String sequence) {
		return baseDao.get(column, value, orderColumn, sequence);
	}
	
	@Override
	public List<T> getService(Integer count, String orderColumn, String sequence, Object[]... filters) {
		return baseDao.get(count, orderColumn, sequence, filters);
	}
	
	@Override
	public List<T> getService(Integer start, Integer count, String orderColumn, String sequence, Object[]... filters) {
		return baseDao.get(start, count, orderColumn, sequence, filters);
	}
	
	@Override
	public List<T> getService(Integer count, String clause, Object... values) {
		return baseDao.get(count, clause, values);
	}

	@Override
	public List<T> getService(Integer start, Integer count, String clause, Object... values) {
		return baseDao.get(start, count, clause, values);
	}

	@Override
	public PagedContent<T> getPagedContentService(PageInfo pageInfo) {
		return baseDao.getPagedContent(pageInfo);
	}

	@Override
	public PagedContent<T> getPagedContentService(PageInfo pageInfo,String column, Object value,String orderColumn,String sequence) {
		return baseDao.getPagedContent(pageInfo, column, value,orderColumn,sequence);
	}

	@Override
	public PagedContent<T> getPagedContentService(PageInfo pageInfo, String clause, Object... values) {
		return baseDao.getPagedContent(pageInfo, clause, values);
	}



}
