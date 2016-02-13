package com.mavict.freemarker.directive;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.mavict.article.Article;
import com.mavict.article.image.ArticleImage;
import com.mavict.article.image.ArticleImageService;
import com.mavict.utils.FreemarkerUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 获取article所属的第一幅缩略图的URL
 * 
 * 作用：给定article,获取该article对应的第一个缩略图。
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
@Component
public class ArticleFirstThumbnailDirective extends BaseDirective implements ServletContextAware {

	private ServletContext servletContext;
	
	@Resource(name = "articleImageServiceImpl")
	private ArticleImageService articleImageService;
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		
		Article article = FreemarkerUtils.getParameter("article", Article.class, params);
		List<ArticleImage> articleImages = articleImageService.getArticleImageService(article);
		String url = null;
		String imageUrl = null;
		
		if (articleImages.size()>0) {
			ArticleImage articleImage = articleImages.get(0);		
			imageUrl = articleImage.getUrl();		
			String[] path = (imageUrl.split("/").length > 1) ? imageUrl.split("/") : imageUrl.split("\\\\") ;
			String thumbImageName = "thumbnail_"+path[path.length - 1]; 
			url = path[path.length - 4]+ "/" + path[path.length - 3] + "/" + path[path.length - 2] + "/" + thumbImageName;
		}
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("imageUrl", url);
		setLocalVariables(variables, env, body);
	}
}

