package com.mavict.setting.navigation;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mavict.article.category.ArticleCategory;
import com.mavict.article.category.ArticleCategoryService;

/**
 * 
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
@Controller
@RequestMapping("/admin/setting/navigation")
public class NavigationController {

	@Resource(name = "navigationServiceImpl")
	private NavigationService navigationService;
	
	@Resource(name = "articleCategoryServiceImpl")
	private ArticleCategoryService articleCategoryService;

	
	/** 分类列表 */
	@RequestMapping("/list")
	public String list(ModelMap model){
		model.addAttribute("navigationTree", navigationService.getTreeService());
		model.addAttribute("articleCategoryTree", articleCategoryService.getTreeService());
		return "/admin/setting/navigation/list";
	}
	
	/** 
	 * 保存一级栏目菜单,即前台导航条上的一级菜单栏目
	 * 
	 * @param categoryId: 
	 * 其格式类同 ${category.id}#articleCategory。取到之后,以#做分割,前半部分表示添加的项目的id,
	 * 后半部分表示区别 添加的是 何种类别 的项目,比如是文章类别的项目还是产品类别的项目,为方便扩展而预留的判定方式。
	 * 
	 *  */
	@RequestMapping("/save")
	public String save(Navigation navigation,String categoryId,RedirectAttributes redirectAttributes){	
		String[] path = categoryId.split("#");
		
		if (Integer.parseInt(path[0]) == 0 && "".equals(navigation.getName())) {
			redirectAttributes.addFlashAttribute("info", "一级栏目选择框 和 一级栏目名称表单 不能同时为空！");
			return "redirect:/admin/setting/navigation/list";
		}
		
		/** 判定添加的一级项目是文章类别 */
		if ("articleCategory".equals(path[1])) {
			ArticleCategory articleCategory = Integer.parseInt(path[0]) != 0 ? articleCategoryService.getService(Integer.valueOf(path[0])) : new ArticleCategory();
			
			navigationService.saveCategoryService(navigation,articleCategory);
			redirectAttributes.addFlashAttribute("info", "添加导航栏目成功！");
		}
		return "redirect:/admin/setting/navigation/list";
	}
	
	
	/** 保存子项目 */
	@RequestMapping("/saveChild")
	public String saveChild(Navigation navigation,String categoryId,RedirectAttributes redirectAttributes){	
		String[] path = categoryId.split("#");
		
		if (Integer.parseInt(path[0]) == 0 && "".equals(navigation.getName())) {
			redirectAttributes.addFlashAttribute("info", "子栏目选择框 和 子栏目名称表单 不能同时为空！");
			return "redirect:/admin/setting/navigation/list";
		}
		
		if ("articleCategory".equals(path[2])) {
			ArticleCategory articleCategory = Integer.parseInt(path[0]) != 0 ? articleCategoryService.getService(Integer.valueOf(path[0])) : new ArticleCategory();
			Navigation parent = navigationService.getService(Integer.valueOf(path[1]));
			
			navigationService.saveChildService(navigation,parent,articleCategory);
			redirectAttributes.addFlashAttribute("info", "添加子栏目成功！");
		}
		
		return "redirect:/admin/setting/navigation/list";
	}
	
	/** 删除 */
	@RequestMapping("/del/{id}")
	public String del(@PathVariable Integer id,RedirectAttributes redirectAttributes){
		if (navigationService.doIsChildService(id)) {
			redirectAttributes.addFlashAttribute("info", "本栏目下还有子栏目，不能删除！");
			return "redirect:/admin/setting/navigation/list";
		}
		navigationService.deleteService(id);
		return "redirect:/admin/setting/navigation/list";
	}
	
	/** 批量删除 */
	@RequestMapping("/batchDel")
	@ResponseBody
	public String batchDel(HttpServletRequest request){
		String ids[] = request.getParameter("ids").split("#");
		for (int id = 1; id < ids.length; id++) {
			Navigation navigation = navigationService.getService(Integer.valueOf(String.valueOf(ids[id])));
			if (navigation.getChild().size() != 0) {
				return "有子栏目,无法删除.";
			}
			navigation.setChild(null);
			navigationService.deleteService(Integer.valueOf(String.valueOf(ids[id])));
		}
		return "删除成功！";
	}
	
    /** 编辑 */
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable Integer id,ModelMap model){
		model.addAttribute("navigation", navigationService.getService(id));
		return "/admin/setting/navigation/edit";
	}
	
	/** 更新 */
	@RequestMapping("/update")
	public String update(Navigation navigation,Integer parentId,RedirectAttributes redirectAttributes){
		if(null != parentId){
			Navigation parent = new Navigation();
			parent.setId(parentId);
			navigation.setParent(parent);
		}
		navigationService.updateService(navigation);
		redirectAttributes.addFlashAttribute("info", "更新栏目成功");
		return "redirect:/admin/setting/navigation/list";
	}
	
	/** 移动 */
	@RequestMapping("/move")
	@ResponseBody
	public String move(HttpServletRequest request){
		Integer myId = Integer.valueOf(request.getParameter("myId"));
		Integer rpId = Integer.valueOf(request.getParameter("rpId"));
		Integer myOrders = Integer.parseInt(request.getParameter("myOrders"));
		Integer rpOrders = Integer.parseInt(request.getParameter("rpOrders"));
		navigationService.updateSequenceService(myId, rpId, myOrders, rpOrders);
		return "succss";	
	}
	
	
}
