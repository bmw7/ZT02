package com.mavict;

import java.io.Serializable;

/**
 * 分页信息 - 初始默认页码(第1页) 初始默认每页记录数 (12); 可以再设置 
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */

public class PageInfo implements Serializable {

	private static final long serialVersionUID = 991622231056346359L;
	
	private static final int DEFAULT_PAGE_NUMBER = 1;
	private static final int DEFAULT_PAGE_SIZE = 12;
	
	private int pageNumber = DEFAULT_PAGE_NUMBER; /** 页码 */
	private int pageSize = DEFAULT_PAGE_SIZE;     /** 每页记录数*/
	
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
