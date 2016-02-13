package com.mavict.controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mavict.member.Member;
import com.mavict.member.MemberService;
import com.mavict.utils.RandomUtils;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

/**
 * Controller - 会员
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
@Controller("memberClientController")
@RequestMapping("/member")
public class MemberController {

	@Resource(name = "memberServiceImpl")
	private MemberService memberService;
	
	/** 登陆  */
	@RequestMapping("/login.html")
	public String login(){
		return "/client/member/login";
	}
	
	/** 注册  */
	@RequestMapping("/register.html")
	public String register(){
		return "/client/member/register";
	}

	/** 充值 */
	@RequestMapping("/charge.html")
	public String charge(){
		return "/client/member/charge";
	}
	
	/** 充值 */
	@RequestMapping("/chargeMoney")
	public String chargeMoney(Float money){
		memberService.updateService("set money = ? where id = ?", money,1);
		return "redirect:/member/charge";
	}
	
	/** 手机注册  验证码操作  */
	@RequestMapping("/phoneSMS")
	@ResponseBody
	public String phoneSMS(HttpServletRequest request, HttpServletResponse response){
		String phone = request.getParameter("phone");
		
		String url="http://gw.api.taobao.com/router/rest";  
		String appkey="23308585";  
		String secret="9f7e17816526d476236836c48f309fc9";
		String idCode = RandomUtils.getRandom(1000, 9999);
		//短信模板的内容  
		String json="{\"code\":\""+idCode+"\",\"product\":\"沧海软件(北京)有限公司\"}";  

		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend("123456");
		req.setSmsType("normal");
		req.setSmsFreeSignName("注册验证");
		req.setSmsParamString(json);
		req.setRecNum(phone);
		req.setSmsTemplateCode("SMS_5062118");
		AlibabaAliqinFcSmsNumSendResponse rsp;
		try {
			rsp = client.execute(req);
			String result = rsp.getBody();
			if (result.contains("error_response")) {
				return "发生错误";
			}else if (result.contains("alibaba_aliqin_fc_sms_num_send_response")) {
				/* 往客户端写cookie */
				Cookie cookie = new Cookie("idCode",idCode);
				/* 10分钟内有效  */
				cookie.setMaxAge(600);
				cookie.setPath("/");
				response.addCookie(cookie);
				return "发送成功!";
			}
		} catch (ApiException e) {
			e.printStackTrace();
		}		
		return "null";
	}
	
	
	/** 手机注册提交  */
	@RequestMapping("/registerPhone")
	public String registerPhone(HttpServletRequest request, Member member){
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		member.setPhone(phone);
		member.setPassword(password);
		memberService.saveService(member);
		return "/client/member/register";
	}
	
	/** 用户名邮件注册提交  */
	@RequestMapping("/registerUsername")
	@ResponseBody
	public String registerUsername(HttpServletRequest request, Member member){
		String username = request.getParameter("username-email");
		String password = request.getParameter("password");
		
		if (username.contains("@")) {
			member.setEmail(username);
		}else {
			member.setUsername(username);
		}
		member.setPassword(password);
		memberService.saveService(member);
		return "注册成功";
	}
	
}
