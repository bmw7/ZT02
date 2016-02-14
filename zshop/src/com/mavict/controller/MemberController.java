package com.mavict.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import com.mavict.member.Member;
import com.mavict.member.MemberPrincipal;
import com.mavict.member.MemberService;
import com.mavict.utils.EncryptUtils;
import com.mavict.utils.RSAService;
import com.mavict.utils.RandomUtils;
import com.mavict.utils.WebUtils;
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
public class MemberController {

	@Resource(name = "memberServiceImpl")
	private MemberService memberService;
	
	@Resource(name = "rsaServiceImpl")
	private RSAService rsaService;
	
	/** 登陆页面  */
	@RequestMapping("/login.html")
	public String login(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		return "/client/member/login";
	}
	
	/** 
	 * 登陆加密
	 * 
	 * @return 生成前台页面js公钥所需的两个参数 modulus exponent,以[mavict]字符串连接二者并作为分隔符 
	 *  */
	@RequestMapping("/login/encrypt")
	@ResponseBody
	public String encrypt(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		return rsaService.createPublicKeyModelParams(request);
	}
	
	/** 登陆验证 */
	@RequestMapping("/login/submit")
	@ResponseBody
	public String loginAction(HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap model){
		String username = request.getParameter("username");
		String password = EncryptUtils.encrypt(rsaService.decryptParameter("encryptPassword", request));
		List<Member> members = memberService.getService("where username = ? and password = ?", username, password);
		if (members.size() == 1) {
			Member member = members.get(0);
			
			/* 服务器添加session */
			session.invalidate();
			session = request.getSession();
			session.setAttribute(Member.PRINCIPAL_NAME, new MemberPrincipal(member.getId(), member.getUsername(), member.getName(), member.getEmail(), member.getPhone()));
			
			/* 客户端添加cookie */
			String memberCookieValue = null;
			if (member.getPhone() != null) {
				memberCookieValue = member.getPhone();
			}
			
			if (member.getUsername() != null) {
				memberCookieValue = member.getUsername();
			}
			
			if (member.getName() != null) {
				memberCookieValue = member.getName();
			}
			
			WebUtils.addCookie(response, Member.COOKIE_NAME, memberCookieValue);
			return "success";
		}	
		return "登陆失败";
	}
	
	/** 登陆退出  */
	@RequestMapping("/login/logout")
	@ResponseBody
	public String logout(HttpServletResponse response){
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		if (requestAttributes != null) {
			HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
			HttpSession session = request.getSession();
			session.invalidate();
			WebUtils.removeCookie(request, response, Member.COOKIE_NAME);
		}
		return "success";
	}
		
	/** 登陆成功跳转页面  */
	@RequestMapping("/member/center.html")
	public String center(){
		return "/client/member/center";
	}

	/** 充值 */
	@RequestMapping("/member/charge.html")
	public String charge(){
		return "/client/member/charge";
	}
	
	/** 充值 */
	@RequestMapping("/member/chargeMoney")
	public String chargeMoney(Float money){
		memberService.updateService("set money = ? where id = ?", money,1);
		return "redirect:/member/charge";
	}
	
	/** 注册  */
	@RequestMapping("/register.html")
	public String register(){
		return "/client/member/register";
	}
	
	/** 手机注册  验证码操作  */
	@RequestMapping("/register/phoneSMS")
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
	@RequestMapping("/register/phone")
	public String registerPhone(HttpServletRequest request, Member member){
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		member.setPhone(phone);
		/* 加密密码 */
		member.setPassword(EncryptUtils.encrypt(password));
		memberService.saveService(member);
		return "/client/member/register";
	}
	
	/** 用户名邮件注册提交  */
	@RequestMapping("/register/username")
	@ResponseBody
	public String registerUsername(HttpServletRequest request, Member member){
		String username = request.getParameter("username-email");
		String password = request.getParameter("password");
		
		if (username.contains("@")) {
			member.setEmail(username);
		}else {
			member.setUsername(username);
		}
		/* 加密密码 */
		member.setPassword(EncryptUtils.encrypt(password));
		memberService.saveService(member);
		return "注册成功";
	}
	
}
