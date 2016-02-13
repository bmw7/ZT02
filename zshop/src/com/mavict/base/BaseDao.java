package com.mavict.base;

import java.io.Serializable;
import java.util.List;

import com.mavict.PageInfo;
import com.mavict.PagedContent;

/**
 * 数据操作基类
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */

public interface BaseDao<T,ID extends Serializable> {
	
	
	
	/*----------------- 增 ----------------*/
	
	
	
	/**
	 * 保存实体
	 * 
	 * @param entity 实体
	 * */
	void save(T entity);
	
	
	
	/*----------------- 删 ----------------*/
	
	
	
	/**
	 * 删除实体
	 * 
	 * @param id 实体id
	 * */
	void delete(ID id);
	
	
	/**
	 * 删除实体
	 * 
	 * @param entity 实体
	 * */
	void delete(T entity);
	
	
	/**
	 * 删除实体
	 * 
	 * @param clause 条件语句 包含占位符?
	 * @param values 占位符的对应值
	 * */
	void delete(String clause, Object...values);
	
	
	
	/*----------------- 改 ----------------*/
	
	
	
	/**
	 * 更新实体
	 * 
	 * @param entity 实体
	 * */
	void update(T entity);
	
	
	/**
	 * 更新实体
	 * 
	 * @param clause 条件语句 包含占位符?
	 * @param values 占位符的对应值
	 * */
	void update(String clause, Object...values);
	
	
	
	/*----------------- 查 ----------------*/
	
	
	
	/**
	 * 获取单个实体 - 查询实体id获取
	 * 
	 * @param id 实体id
	 * 
	 * @return 实体
	 * */
	T get(ID id);  
	
	
	/**
	 * 获取单个实体 - 查询字段值获取
	 * 
	 * @param column 欲查询的字段名
	 * @param value  查询字段的值
	 * 
	 * @return 实体
	 * */
	T get(String column,Object value);
	
	
	/**
	 * 获取所有实体
	 * 
	 * 说明：未定义排序，默认排序
	 * 
	 * @return 实体List
	 * */
	List<T> get(); 
	
	
	/**
	 * [G1] 获取实体集合 - 查询字段值获取 - 默认逆序排列
	 * 
	 * @param column 欲查询的字段名
	 * @param value  查询字段的值
	 * @param orderColumn 默认为逆序排列的字段名
	 * 
	 * @return 实体List集合
	 * */
	List<T> get(String column,Object value,String orderColumn);
	
	
	/**
	 * [G1-BASE] 获取实体集合 - 查询字段值获取
	 * 
	 * @param column 欲查询的字段名
	 * @param value  查询字段的值
	 * @param orderColumn 欲排列的字段名
	 * @param sequence 正序或逆序  asc 或  desc
	 * 
	 * @return 实体List集合
	 * */
	List<T> get(String column,Object value,String orderColumn, String sequence);
	
	
	/**
	 * [G2] 获取所有 满足条件语句的count个实体集合 - 默认从0开始
	 * 
	 * @param count 前count个条目
	 * @param orderColumn 排序字段
	 * @param sequence 正序或逆序 asc 或  desc
	 * @param fiters 检索条件
	 * 
	 * @return 实体List集合
	 * */
	List<T> get(Integer count, String orderColumn, String sequence, Object[]... filters);
	
	
	/**
	 * [G2-BASE] 获取所有 满足条件语句的 count个 实体集合 - [BASE]
	 * 
	 * @param start 开始条目
	 * @param count 条目数
	 * @param orderColumn 排序字段
	 * @param sequence 正序或逆序 asc 或  desc
	 * @param fiters 检索条件
	 * 
	 * @return 实体List集合
	 * */
	List<T> get(Integer start, Integer count, String orderColumn, String sequence, Object[]... filters); 

	
	/**
	 * [G3] 获取所有 满足条件语句的count个 实体集合 - 默认从0开始
	 * 
	 * @param count 前count个条目
	 * @param clause 条件语句 形如 "where id = ? and name = ?"
	 * @param values 占位符? 的对应值
	 * 
	 * @return 实体List集合
	 * */
	List<T> get(Integer count, String clause, Object... values);
	
	
	/**
	 * [G3-BASE] 获取所有 满足条件语句的 count个 实体集合 - [BASE]
	 * 
	 * @param start 开始条目
	 * @param count 条目数
	 * @param clause 条件语句 形如 "where id = ? and name = ?"
	 * @param values 占位符? 的对应值
	 * 
	 * @return 实体List集合
	 * */
	List<T> get(Integer start, Integer count, String clause, Object... values); 
	
	
	/** 
	 * 获取分页内容 
	 * 
	 * @param pageInfo 分页信息
	 * 
	 * @return 分页实体集合
	 * */
	PagedContent<T> getPagedContent(PageInfo pageInfo);
	
	
	/** 
	 * 获取单字段条件查询的分页内容 
	 * 
	 * @param pageInfo 分页信息
	 * @param column 查询字段名
	 * @param value 查询字段对应的查询值
	 * @param orderColumn 排序字段名
	 * @param sequence 正序或逆序
	 * 
	 * @return 分页实体集合
	 * */
	PagedContent<T> getPagedContent(PageInfo pageInfo, String column, Object value, String orderColumn, String sequence);
	
	
	/** 
	 * 获取单字段条件查询的分页内容 
	 * 
	 * @param pageInfo 分页信息
	 * @param clause 查询语句 包含占位符?
	 * @param values 占位符对应的查询值
	 * 
	 * @return 分页实体集合
	 * */
	PagedContent<T> getPagedContent(PageInfo pageInfo, String clause, Object... values);
	
	
	/** 
	 * 统计 实体数目
	 * 
	 * @return 所有实体数目
	 *  */
	Long count();
	
	
	/** 
	 * 统计 符合单字段查询条件的 实体数目
	 * 
	 * @param column 查询字段名
	 * @param value 查询字段对应的查询值
	 * 
	 * @return 实体数目
	 *  */
	Long count(String column, Object value);
	
	
	/** 
	 * 统计 符合查询语句的 实体数目
	 * 
	 * @param clause 查询条件语句
	 * 
	 * @return 实体数目
	 * */
	Long count(String clause);


}
