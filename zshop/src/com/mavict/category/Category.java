package com.mavict.category;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.mavict.base.BaseEntity;

/**
 * 分类基础类
 * 
 * 继承本类时，需添加 分类主体 属性和设置，例 建立Article相关的分类，如下操作
 *   
 * private List<Article> articles = new ArrayList<Article>(); 
 * 
 * @OneToMany(mappedBy = "articleCategory")
 * public List<Article> getArticles() {
 * 	   return articles;
 * }
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
@MappedSuperclass
public class Category<T> extends BaseEntity {

	private static final long serialVersionUID = 4296595765557428644L;

	/* 分类名称  */
	private String  name;
	
	/* 分类层级  */
	private Integer grade; 		      
	
	/* 分类排序  */
	private Integer orders;           
	
	/* 分类类型  */
	private Integer type;             
	
	/* 访问网址  */
	private String  url;              
	
	/* SEO标题  */
	private String  seoTitle;         
	
	/* SEO关键词  */
	private String  seoKeywords;      
	
	/* SEO页面描述  */
	private String  seoDescription;   
	
	/* 父(上)级目录   */
	private T parent;
	
	/* 子(下)级目录   */ 
	private List<T> child = new ArrayList<T>(); 
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Integer getOrders() {
		return orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSeoTitle() {
		return seoTitle;
	}

	public void setSeoTitle(String seoTitle) {
		this.seoTitle = seoTitle;
	}

	public String getSeoKeywords() {
		return seoKeywords;
	}

	public void setSeoKeywords(String seoKeywords) {
		this.seoKeywords = seoKeywords;
	}

	public String getSeoDescription() {
		return seoDescription;
	}

	public void setSeoDescription(String seoDescription) {
		this.seoDescription = seoDescription;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "parentId")  // 不可与属性名相同
	@NotFound(action = NotFoundAction.IGNORE)
	public T getParent() {
		return parent;
	}

	public void setParent(T parent) {
		this.parent = parent;
	}

	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
	public List<T> getChild() {
		return child;
	}

	public void setChild(List<T> child) {
		this.child = child;
	}  

}
