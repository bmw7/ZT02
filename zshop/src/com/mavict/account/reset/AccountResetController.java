package com.mavict.account.reset;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mavict.account.Account;
import com.mavict.account.AccountService;
import com.mavict.setting.mail.Mail;
import com.mavict.utils.EncryptUtils;
import com.mavict.utils.MailUtils;



/**
 * 
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
@Controller
@RequestMapping("/reset")
public class AccountResetController {
	
	@Resource(name = "accountServiceImpl")
	private AccountService accountService;
	
	@Resource(name = "accountResetServiceImpl")
	private AccountResetService accountResetService;
	
	/** 忘记密码 取回表单页 */
	@RequestMapping("/forget")
	public String forget(){
		return "/admin/account/reset/forget";
	}
	
	/** 检查用户名是否存在 */
	@RequestMapping(value = "/check")
	public @ResponseBody
	boolean checkUsername(String username) {
		if (StringUtils.isEmpty(username)) { return false; }
		String columnName = (username.contains("@")) ? "email" : "username";
		return (accountService.getService(columnName, username) == null) ? true : false;
	}
	
	/** 发送修改密码邮件 */
	@RequestMapping(value = "/back",method = RequestMethod.POST)
	@ResponseBody
	public String back(HttpServletRequest request) {
		String username = request.getParameter("username");
		String columnName = (username.contains("@")) ? "email" : "username";
		Account account = accountService.getService(columnName, username);
		String result = (username.contains("@")) ? "未查询到此邮箱，请重新输入" : "错误的管理账号";;
		if (account != null) {

			if(account.getEmail() == null){
				result = "该管理账号未设置邮箱";
			}else{
				String content = accountResetService.doCreateMailContentService(request, account);
				Mail email = new Mail();
				result = MailUtils.send(new AccountMail(account.getEmail(), content)); 
			}

		}

		return result;
	}
	
	
	/** 验证邮箱重置密码链接有效性，有效则转向修改密码表单页面 */
	@RequestMapping("/account")
	public String account(@RequestParam("uid") Integer uid,@RequestParam("code") String code,ModelMap model){
		AccountReset accountReset = accountResetService.getService("accountId", uid);
		String validateCode  = accountReset.getValidateCode();
		Date expireDate = accountReset.getExpireDate();
		if ( (validateCode.equals(code)) && (expireDate.getTime() > System.currentTimeMillis()) ) {
			model.put("uid", uid);
			return "/admin/account/reset/form";
		}
		return "/admin/account/reset/error";
	}
	
	/** 重置密码 */
	@RequestMapping("/{uid}")
	public String reset(@PathVariable Integer uid,HttpServletRequest request){
		String password = request.getParameter("password");
		String encryptPassword = EncryptUtils.encrypt(password.trim());
		Account account = accountService.getService(uid);
		account.setPassword(encryptPassword);
		accountService.updateService(account);  // 只可更新托管对象
		return "/admin/account/reset/success";
	}

}
