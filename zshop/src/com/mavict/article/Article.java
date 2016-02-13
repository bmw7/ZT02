package com.mavict.article;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.apache.solr.client.solrj.beans.Field;

import com.mavict.article.category.ArticleCategory;
import com.mavict.article.image.ArticleImage;
import com.mavict.base.BaseEntity;

/**
 * 内容实体
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */

@Entity
public class Article extends BaseEntity {
	
	private static final long serialVersionUID = -9002168713541828055L;
	@Field
	private String  title;			  // 内容标题
	@Field
	private String  content;          // 具体内容
	@Field
	private Date    createDate;       // 发布时间 
	
	private String  source;	          // 内容来源
	private String  seoKeywords;	  // 页面关键词
	private String  seoDescription;   // 页面描述
	
	private ArticleCategory articleCategory; // 内容分类 
	private List<ArticleImage> articleImages = new ArrayList<ArticleImage>(); // 内容图片
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "articleCategoryId")
	public ArticleCategory getArticleCategory() {
		return articleCategory;
	}
	public void setArticleCategory(ArticleCategory articleCategory) {
		this.articleCategory = articleCategory;
	}
	
	@OneToMany(mappedBy = "article")
	public List<ArticleImage> getArticleImages() {
		return articleImages;
	}
	public void setArticleImages(List<ArticleImage> articleImages) {
		this.articleImages = articleImages;
	}

}
