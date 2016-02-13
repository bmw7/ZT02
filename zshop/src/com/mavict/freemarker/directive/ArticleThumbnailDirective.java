package com.mavict.freemarker.directive;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.mavict.article.image.ArticleImage;
import com.mavict.utils.FreemarkerUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 获取 图片实体 articleImage 的缩略图URL
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
@Component
public class ArticleThumbnailDirective extends BaseDirective implements ServletContextAware {

	private ServletContext servletContext;
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		
		ArticleImage articleImage = FreemarkerUtils.getParameter("image", ArticleImage.class, params);
		String imageUrl = articleImage.getUrl();
		String[] path = (imageUrl.split("/").length > 1) ? imageUrl.split("/") : imageUrl.split("\\\\") ;
		String thumbImageName = "thumbnail_"+path[path.length - 1]; 
		String url = path[path.length - 4]+ "/" + path[path.length - 3] + "/" + path[path.length - 2] + "/" + thumbImageName;
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("imageUrl", url);
		setLocalVariables(variables, env, body);
	}
}
