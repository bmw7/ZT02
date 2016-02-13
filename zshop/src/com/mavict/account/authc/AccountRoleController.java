package com.mavict.account.authc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mavict.PageInfo;
import com.mavict.account.AccountService;

/**
 * 管理账户角色 Controller
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
@Controller
@RequestMapping("/admin/account/role")
public class AccountRoleController {

	@Resource(name = "accountRoleServiceImpl")
	private AccountRoleService accountRoleService;
	
	@Resource(name = "accountPermServiceImpl")
	private AccountPermService accountPermService;
	
	@Resource(name = "accountServiceImpl")
	private AccountService accountService;
	
	/** 列表 */
	@RequestMapping("/list")
	public String list(ModelMap model,PageInfo pageInfo,@RequestParam(value = "pageNumber",required = false) Integer pageNumber){
		if (pageNumber == null) {
			pageNumber = 1;
		}
		pageInfo.setPageNumber(pageNumber);
		model.addAttribute("pagedContent",accountRoleService.getPagedContentService(pageInfo));
		model.addAttribute("pageUrl", "/admin/account/role/list");
		return "/admin/account/role/list";
	}
	
	/** 编辑 */
	@RequestMapping("/edit/{accountRoleId}")
	public String edit(@PathVariable Integer accountRoleId,ModelMap model){
		AccountRole accountRole = accountRoleService.getService(accountRoleId);
		model.addAttribute("accountRole", accountRole);
		
		/* 获取所有权限,并将所有权限的ID赋值给String数组accountPermIds。注:用Integer数组失效*/
		List<AccountPerm> accountPerms = accountRole.getAccountPerms();
		String[] accountPermIds = new String[accountPerms.size()]; 
		for (int i = 0; i < accountPerms.size(); i++) {
			accountPermIds[i] = String.valueOf(accountPerms.get(i).getId());
		}
		model.addAttribute("accountPermIds",accountPermIds);
		
		return "/admin/account/role/edit";
	}
	
	/** 删除 */
	@RequestMapping("/del/{accountRoleId}")
	public String del(@PathVariable Integer accountRoleId,RedirectAttributes redirectAttributes){
		/* 系统角色不能删除*/
		if (accountRoleId == 1) {
			redirectAttributes.addFlashAttribute("info", "system role cannot be deleted!");
			return "redirect:/admin/account/role/list";
		}
		/* 有用户使用该角色,不能删除*/
		if (accountService.getService("accountRoleId", accountRoleId, "id") != null) {
			redirectAttributes.addFlashAttribute("info", "this role is used by someone user.so you cannot delete it.");
			return "redirect:/admin/account/role/list";
		}
		AccountRole accountRole = accountRoleService.getService(accountRoleId);
		/* ManyToMany 关系避免一溜全删的必要措施 */
		accountRole.setAccountPerms(null);
		accountRoleService.deleteService(accountRole);
		return "redirect:/admin/account/role/list";
	}
	
	/** 更新 */
	@RequestMapping("/update")
	public String update(RedirectAttributes redirectAttributes,AccountRole accountRole,String[] accountPermIds){
		accountRole.setAccountPerms(getAccountPermsFromIds(accountPermIds));
		accountRoleService.updateService(accountRole);
		redirectAttributes.addFlashAttribute("info", "角色信息更新成功！");
		return "redirect:/admin/account/role/list";
	}
	
	/** 添加 */
	@RequestMapping("/add")
	public String add(){
		return "/admin/account/role/add";
	}
	
	/** 保存 */
	@RequestMapping("/save")
	public String save(RedirectAttributes redirectAttributes,AccountRole accountRole,String[] accountPermIds){
		accountRole.setAccountPerms(getAccountPermsFromIds(accountPermIds));
		accountRoleService.saveService(accountRole);
		redirectAttributes.addFlashAttribute("info", "角色添加成功！");
		return "redirect:/admin/account/role/list";
	}
	
	/** 通过checkbox表单提交的accountPermIds获取accountPerms对象*/
	public List<AccountPerm> getAccountPermsFromIds(String[] accountPermIds){
		List<String> permIds = Arrays.asList(accountPermIds);
		List<AccountPerm> accountPerms = new ArrayList<AccountPerm>();
		for (String permId : permIds) {
			AccountPerm accountPerm = accountPermService.getService(Integer.valueOf(permId));
			accountPerms.add(accountPerm);
		}
		return accountPerms;
	}
	
	
}
