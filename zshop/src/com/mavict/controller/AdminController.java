package com.mavict.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mavict.account.AccountService;
import com.mavict.utils.RSAService;

/**
 * Controller - 后台登陆相关
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Resource(name = "accountServiceImpl")
	private AccountService accountService;

	@Resource(name = "rsaServiceImpl")
	private RSAService rsaService;
	
	/**
	 * 登陆验证  - 由本方法转向登陆页面 login.ftl
	 *  
	 * 登陆页面中,js加密库生成RSA公钥需提供参数 : modulus exponent
	 * 本方法负责随机生成这两个参数数据,并提供给登陆页面,由js生成公钥  rsaKey.setPublic(b64tohex("${modulus}"), b64tohex("${exponent}")); 进而实现在登陆页面由公钥加密用户主体登陆数据
	 */
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request,HttpServletResponse response,ModelMap model) throws ServletException, IOException{
		Subject currentUser = SecurityUtils.getSubject();
	
		if (!currentUser.isAuthenticated()) {               
	        rsaService.createPublicKeyModelParams(request, model);
	    	
	    	/** 在验证过程中产生错误则抛出，无错误转向后台主页 */
	    	if(request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME) != null){
	        	String loginFailure = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);   
	        	
	        	if(loginFailure.equals("org.apache.shiro.authc.UnknownAccountException")){
	        		model.put("failure","管理资料错误！");
	        	}
	        	
	        	if(loginFailure.equals("org.apache.shiro.authc.ConcurrentAccessException")){
	        		model.put("failure","登陆密码错误！");
	        	}
	        	
	        	if(loginFailure.equals("org.apache.shiro.authc.DisabledAccountException")){
	        		model.put("failure","账号被禁用！");
	        	}
	     	
	        	if(loginFailure.equals("org.apache.shiro.authc.LockedAccountException")){
	        		model.put("failure","账号被锁定！");
	        	} 
	         
	    	}
	    	return "/admin/login/login";
		}
		return "/admin/login/main";		
	}
	
	
	@RequestMapping("/kaptchaCheck")
	@ResponseBody 
	public boolean kaptchaCheck(HttpServletRequest request){
		String kaptchaExpected = (String)request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		String kaptchaReceived = request.getParameter("kaptcha");
		return (kaptchaReceived == null || !kaptchaReceived.equalsIgnoreCase(kaptchaExpected)) ? false : true;
	}
	

	/** 主操作框架界面 */
	@RequestMapping("/main")
	public String home(){
		return "/admin/login/main";
	}
	
	/** 后台内容首页 */
	@RequestMapping("/index")
	public String index(){
		return "/admin/login/index";
	}
	
	/** 帮助文档 */
	@RequestMapping("/docs")
	public String docs(){
		return "/admin/login/docs";
	}
	
	/** 未授权跳转页面 */
	@RequestMapping("/unauthorized")
	public String unauthorized(){
		return "/admin/login/unauthorized";
	}
	
	/** 安全退出   */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response,ModelMap model) throws ServletException, IOException{
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		return "redirect:/admin/login";
	}
}
