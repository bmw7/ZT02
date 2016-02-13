package com.mavict.article.image;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.mavict.article.Article;
import com.mavict.base.BaseEntity;

/**
 * 
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
@Entity
public class ArticleImage extends BaseEntity{
	
	private static final long serialVersionUID = 4749015953937619895L;
	private String url;
	private String title;
	private Integer orders;
	private Article article;
	
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@ManyToOne
	@JoinColumn(name = "articleId")
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}

}
