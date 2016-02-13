package com.mavict.freemarker.directive;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.mavict.utils.FreemarkerUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 模板指令 - 分页
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
@Component
public class PageDirective extends BaseDirective {

	private static final String PAGE_NUMBER = "pageNumber";     /** "当前页码"变量与参数名称 */
	private static final String TOTAL_PAGES = "totalPages";     /** "总页数"变量与参数名称 */
	private static final String PAGE_URL    = "pageUrl";        /** ajax分页跳转请求网址 变量与参数名称 */
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		Integer pageNumber = FreemarkerUtils.getParameter(PAGE_NUMBER, Integer.class, params);
		Integer totalPages = FreemarkerUtils.getParameter(TOTAL_PAGES, Integer.class, params);
		String pageUrl = FreemarkerUtils.getParameter(PAGE_URL, String.class, params);
		

		if (pageNumber == null || pageNumber < 1) {
			pageNumber = 1;
		}
		if (totalPages == null || totalPages < 1) {
			totalPages = 1;
		}
		
		Map<String, Object> variables = new HashMap<String, Object>();	
		variables.put(PAGE_NUMBER, pageNumber);
		variables.put(TOTAL_PAGES, totalPages);	
		variables.put(PAGE_URL, pageUrl);	
		setLocalVariables(variables, env, body);
	}

}