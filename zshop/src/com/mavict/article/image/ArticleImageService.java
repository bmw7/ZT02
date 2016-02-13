package com.mavict.article.image;

import java.util.List;

import com.mavict.article.Article;
import com.mavict.base.BaseService;

/**
 * 
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
public interface ArticleImageService extends BaseService<ArticleImage, Integer> {
	/**
	 * 获取Article上传文章图片
	 * 
	 * @param article 某篇文章
	 * 
	 * @return 该篇文章上传的 
	 * */
	List<ArticleImage> getArticleImageService(Article article);
}
