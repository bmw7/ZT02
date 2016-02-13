package com.mavict.friendlinks;

import javax.persistence.Entity;

import com.mavict.base.BaseEntity;

/**
 * Entity - 友情链接
 * 
 * 说明: 链接LOGO图片以id命名，上传地址 content/friendlinks/{id}.jpg
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
@Entity
public class Friendlinks extends BaseEntity {
	private static final long serialVersionUID = 6251500740614406640L;

	private String name;    // 链接名称
	private String url;     // 链接网址
	private Integer orders; // 排序
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getOrders() {
		return orders;
	}
	public void setOrders(Integer orders) {
		this.orders = orders;
	}
}
