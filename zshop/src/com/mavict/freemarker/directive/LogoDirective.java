package com.mavict.freemarker.directive;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.mavict.utils.FreemarkerUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * Directive - 友情链接 后台管理 显示网站Logo
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */

@Component
public class LogoDirective extends BaseDirective  implements ServletContextAware{

	private static final String LINKS_ID = "linksId";
	private static final String IMG_TAGS = "imgTags";
	
	private ServletContext servletContext;
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,TemplateDirectiveBody body) throws TemplateException, IOException {
		int links_Id = FreemarkerUtils.getParameter(LINKS_ID, Integer.class, params);
		File dirFile = new File(servletContext.getRealPath(File.separator+"content"+File.separator+"friendlinks"));
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		File[] files = dirFile.listFiles();
		String img_Tags = "";
		for (int i = 0; i < files.length; i++) {
			String[] fileNameArray = files[i].getName().split("\\.");		
			if (Integer.valueOf(fileNameArray[0]) == links_Id) {
				String imgSrc = servletContext.getContextPath()+"/content/friendlinks/"+files[i].getName();
				img_Tags = "<img src="+imgSrc+" class='item_logo'>";
			}
			
		}
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put(IMG_TAGS, img_Tags);
		setLocalVariables(variables, env, body);
	}


}
