package com.mavict;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 分页类 - 内容、总记录数、分页信息(页码、每页记录数)
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */

public class PagedContent<T> implements Serializable {

	private static final long serialVersionUID = 8500274874425778254L;
	
	private List<T> content = new ArrayList<T>();   /** 内容 */
	private final long total;                             /** 总记录数 */
	private final PageInfo pageInfo;                      /** 分页信息 */
	
	/** 初始构造函数  */
	public PagedContent(){
		total = 0;
		pageInfo = new PageInfo();
	}
	
	/** 赋值构造函数  */
	public PagedContent(List<T> content,long total,PageInfo pageInfo){
		this.content.addAll(content);
		this.total = total; 
		this.pageInfo = pageInfo;
	}
	
	/** 获取内容  */
	public List<T> getContent() {
		return content;
	}
	
	/** 设置内容  */
	public void setContent(List<T> content){
		this.content = content;
	}
	
	/** 获取总记录数  */
	public long getTotal() {
		return total;
	}

	/** 通过 pageInfo 获取页码 */
	public int getPageNumber() {         
		return pageInfo.getPageNumber();
	} 
	
	/** 通过 pageInfo 获取每页记录数 */
	public int getPageSize() {
		return pageInfo.getPageSize();
	}
	
	/** 获取总页数 */
	public long getTotalPages() {
		return (long) Math.ceil((double) getTotal() / (double) getPageSize());
	}

}
