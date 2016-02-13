package com.mavict.freemarker.directive;

import java.io.IOException;
import java.util.ArrayList;
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
 * 获取文章上传图片的URL
 * 
 * 作用：给定article,获取该article对应的图片。
 * 
 * @param article
 * 
 * @return param ${imageUrls} - List<url>
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
@Component
public class ArticleImagesDirective extends BaseDirective implements ServletContextAware {

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
		List<String> urls = new ArrayList<String>();	
		
		if (articleImages.size()>0) {
			for (ArticleImage articleImage : articleImages) {
				String imageUrl = articleImage.getUrl();
				String[] path = (imageUrl.split("/").length > 1) ? imageUrl.split("/") : imageUrl.split("\\\\") ;
				String imageName = path[path.length - 1]; 
				String url = path[path.length - 4]+ "/" + path[path.length - 3] + "/" + path[path.length - 2] + "/" + imageName;
				urls.add(url);
			}
		}
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("imageUrls", urls);
		setLocalVariables(variables, env, body);
	}

}

