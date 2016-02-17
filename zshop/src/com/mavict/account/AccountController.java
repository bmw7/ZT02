package com.mavict.account;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mavict.PageInfo;
import com.mavict.account.authc.AccountRole;
import com.mavict.account.authc.AccountRoleService;
import com.mavict.base.BaseController;
import com.mavict.utils.EncryptUtils;


/**
 * Controller - 系统管理员
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */

@Controller
@RequestMapping("/admin/account")
public class AccountController extends BaseController{
	
	@Resource(name = "accountServiceImpl")
	private AccountService accountService;
	
	@Resource(name = "accountRoleServiceImpl")
	private AccountRoleService accountRoleService;
	
	/** 列表 */
	@RequestMapping("/list")
	public String list(ModelMap model,PageInfo pageInfo){
		model.addAttribute("pagedContent", accountService.getPagedContentService(pageInfo));
		return "/admin/account/list";
	}
	
	/** 添加 */
	@RequestMapping("/add")
	public String add(ModelMap model){
		model.addAttribute("accountRoles", accountRoleService.getService("order by id asc"));
		return "/admin/account/add";
	}
	
	/** 保存 */
	@RequestMapping("/save")
	public String save(RedirectAttributes redirectAttributes,Account account, Boolean isEnabled, Integer accountRoleId){
		account.setIsEnabled(isEnabled);
		account.setIsLocked(false);
		account.setLoginCount(0);
		account.setLoginFailureCount(0);
		account.setCreateDate(new Date());
		account.setPassword(EncryptUtils.encrypt(account.getPassword()));
		
		AccountRole accountRole = new AccountRole();
		accountRole.setId(accountRoleId);
		account.setAccountRole(accountRole);
		
		accountService.saveService(account);
		redirectAttributes.addFlashAttribute("success", "添加管理账号成功！");
		return "redirect:/admin/account/add";
	}
	
	/** 检查用户名是否存在 */
	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public @ResponseBody
	boolean checkUsername(String username) {
		if (StringUtils.isEmpty(username)) { return false; }
		return (accountService.getService("username", username) == null) ? true : false;
	}
	
	/** 编辑 */
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, ModelMap model){
		model.addAttribute("account", accountService.getService(id));
		model.addAttribute("roles", accountRoleService.getService("order by id asc"));
		return "/admin/account/edit";
	}
	
	/** 更新 */
	@RequestMapping("/update")
	public String update(RedirectAttributes redirectAttributes,Account account, String newPassword, Integer roleId, boolean isEnabled, boolean isLocked,HttpServletRequest request){
		/** 密码表单不为空,重置密码 */
		if (!(newPassword == null ||"".equals(newPassword))) {
			account.setPassword(EncryptUtils.encrypt(newPassword));
		}
		/** 锁定与否 */
		if (isLocked) {
			account.setIsLocked(true);
			account.setLockedDate(new Date()); 
		}else {
			account.setLoginFailureCount(0);
			account.setIsLocked(false);
		}
		/** 启用与否 */ 
		if (isEnabled) {
			account.setIsEnabled(true);
		}else {
			account.setIsEnabled(false);
		}
		
		/** 更新角色 */
		AccountRole accountRole = new AccountRole();
		accountRole.setId(roleId);
		account.setAccountRole(accountRole);
		request.getSession().setAttribute("loginName", account.getName());
		
		accountService.updateService(account);
		redirectAttributes.addFlashAttribute("success", "更新管理账号成功！");
		return "redirect:/admin/account/edit/"+account.getId();
	}
	
	

	
	
	@RequestMapping("/mail")
	public String sendMail(){
		
//		AccountMail accountMail = new AccountMail(receiver, message);
//	    MailUtils.send(mail);  
		
//		accountService.getAccountService("admin");
		
		accountService.getService(1);
		
//		Account account = new Account();
//		account.setUsername("zhangtao");
//		account.setPassword("zzz");
//		account.setIsEnabled(true);
//		account.setIsLocked(false);
//		account.setLoginFailureCount(0);
//		accountService.saveService(account);
		return "admin/account/mail";
	}
	


}
