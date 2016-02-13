package com.mavict.article.image;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mavict.article.Article;
import com.mavict.base.BaseDao;
import com.mavict.base.BaseServiceImpl;

/**
 * 
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
@Service
public class ArticleImageServiceImpl extends BaseServiceImpl<ArticleImage, Integer> implements ArticleImageService {

	@Resource(name = "articleImageDaoImpl")
	private ArticleImageDao articleImageDao;
	
	@Override
	@Resource(name = "articleImageDaoImpl")
	public void setBaseDao(BaseDao<ArticleImage, Integer> baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	public List<ArticleImage> getArticleImageService(Article article) {
		return articleImageDao.getArticleImage(article);
	}
	
}
