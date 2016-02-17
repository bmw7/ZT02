package com.mavict.member;

import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 会员访问过滤器
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
public class MemberInterceptor extends HandlerInterceptorAdapter {
	
	/* 重定向视图名称前缀 */
	private static final String REDIRECT_VIEW_NAME_PREFIX = "redirect:";

	/* "重定向URL"参数名称 */
	private static final String REDIRECT_URL_PARAMETER_NAME = "redirectUrl";

	/* "会员"属性名称 */
	private static final String MEMBER_ATTRIBUTE_NAME = "member";

	/* 默认登录URL */
	private static final String DEFAULT_LOGIN_URL = "/login.html";

	/* 登录URL */
	private String loginUrl = DEFAULT_LOGIN_URL;
	
	@Resource(name = "memberServiceImpl")
	private MemberService memberService;


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		MemberPrincipal memberPrincipal = (MemberPrincipal) session.getAttribute(Member.PRINCIPAL_NAME);
		if (memberPrincipal != null) {
			return true;
		}else {
			String requestType = request.getHeader("X-Requested-With");
			if (requestType != null && requestType.equalsIgnoreCase("XMLHttpRequest")) {
				response.addHeader("loginStatus", "accessDenied");
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
				return false;
			} else {
				if (request.getMethod().equalsIgnoreCase("GET")) {
					String redirectUrl = request.getQueryString() != null ? request.getRequestURI() + "?" + request.getQueryString() : request.getRequestURI();
					response.sendRedirect(request.getContextPath() + loginUrl + "?" + REDIRECT_URL_PARAMETER_NAME + "=" + URLEncoder.encode(redirectUrl, "UTF-8"));
				} else {
					response.sendRedirect(request.getContextPath() + loginUrl);
				}
				return false;
			}
		}
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if (modelAndView != null) {
			String viewName = modelAndView.getViewName();
			if (!StringUtils.startsWith(viewName, REDIRECT_VIEW_NAME_PREFIX)) {
				//modelAndView.addObject(MEMBER_ATTRIBUTE_NAME, memberService.getCurrent());
			}
		}
	}

}
