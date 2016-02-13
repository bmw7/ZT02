package com.mavict.article.category;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.mavict.article.Article;
import com.mavict.base.BaseEntity;

/**
 * 文章分类目录
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
@Entity
public class ArticleCategory extends BaseEntity {
	
	private static final long serialVersionUID = 1882951635603250573L;
	private String  name;   		  // 名称 
	private Integer grade; 		      // 层级 
	private Integer orders;           // 排列顺序 
	private Integer type;             // 分类类型 
	private String  url;              // 访问url
	private String  seoTitle;         // 页面标题
	private String  seoKeywords;      // 页面关键词 
	private String  seoDescription;   // 页面描述 
	
	private ArticleCategory parent;   // 父(上)级目录 
	private List<ArticleCategory> child = new ArrayList<ArticleCategory>(); // 子(下)级目录 
	
	private List<Article> articles = new ArrayList<Article>(); // 文章 


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
	public ArticleCategory getParent() {
		return parent;
	}

	public void setParent(ArticleCategory parent) {
		this.parent = parent;
	}

	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
	public List<ArticleCategory> getChild() {
		return child;
	}

	public void setChild(List<ArticleCategory> child) {
		this.child = child;
	}

	@OneToMany(mappedBy = "articleCategory")
	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	
}
