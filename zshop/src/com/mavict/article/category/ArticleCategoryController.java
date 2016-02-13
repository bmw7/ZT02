package com.mavict.article.category;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mavict.article.Article;
import com.mavict.article.ArticleService;

/**
 * Controller - 分类目录
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
@Controller
@RequestMapping("/admin/article/category")
public class ArticleCategoryController {

	@Resource(name = "articleCategoryServiceImpl")
	private ArticleCategoryService articleCategoryService;
	
	@Resource(name = "articleServiceImpl")
	private ArticleService articleService;
	
	/** 列表 */
	@RequestMapping("/list")
	public String list(ModelMap model){
		model.addAttribute("tree", articleCategoryService.getTreeService());
		return "/admin/article/category/list";
	}

	/** 添加 */
	@RequestMapping("/add")
	public String add(ModelMap model){
		model.addAttribute("tree", articleCategoryService.getTreeService());
		return "/admin/article/category/add";
	}
	
	/** 保存 */
	@RequestMapping("/save")
	public String save(RedirectAttributes redirectAttributes,ArticleCategory articleCategory,Integer parentId,Integer type){
		ArticleCategory parent = new ArticleCategory();
		parent.setId(parentId);
		parent.setType(type);
		articleCategory.setParent(parent);                   // 显示保存父对象，因parentId不能为null 
		articleCategoryService.saveService(articleCategory);
		articleCategory.setOrders(articleCategory.getId().intValue());  // 设置id与orders相同 
		articleCategoryService.updateService(articleCategory);
		redirectAttributes.addFlashAttribute("info", "添加分类成功!");
		return "redirect:/admin/article/category/add";
	}
	
	/** 编辑 */
	@CacheEvict(value = "CUSTOM_CACHE", key = "'articleCategory'")
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable Integer id,ModelMap model){
		model.addAttribute("articleCategory", articleCategoryService.getService(id));
		model.addAttribute("articleCategoryTree", articleCategoryService.getTreeService());
		return "/admin/article/category/edit";
	}
	
	/** 更新 */
	@CacheEvict(value = "CUSTOM_CACHE", key = "'articleCategory'")
	@RequestMapping("/update")
	public String update(RedirectAttributes redirectAttributes,ArticleCategory articleCategory,Integer parentId){
		ArticleCategory parent = articleCategoryService.getService(parentId);
		articleCategory.setParent(parent);
		articleCategory.setGrade(parent.getGrade()+1);
		articleCategoryService.updateService(articleCategory);
		redirectAttributes.addFlashAttribute("info", "分类编辑成功!");
		return "redirect:/admin/article/category/list";
	}
	
	/** 删除 */
	@CacheEvict(value = "CUSTOM_CACHE", key = "'articleCategory'")
	@RequestMapping("/del/{id}")
	public String delete(@PathVariable Integer id,RedirectAttributes redirectAttributes){
		List<ArticleCategory> articleCategories = articleCategoryService.getService("parentId", id, "id");
		List<Article> articles = articleService.getService("articleCategoryId", id, "id");
		
		if (articleCategories.size() != 0){
			redirectAttributes.addFlashAttribute("info", "本分类下有子分类，不能删除！");
		}else if (articles.size() != 0) {
			redirectAttributes.addFlashAttribute("info", "本分类下还有文章，不能删除！");
		}else {
			articleCategoryService.deleteService(id);
		}
		
		return "redirect:/admin/article/category/list";
	}

	/** 移动 */
	@CacheEvict(value = "CUSTOM_CACHE", key = "'articleCategory'")
	@RequestMapping("/move")
	@ResponseBody
	public String move(HttpServletRequest request){
		Integer myId = Integer.valueOf(request.getParameter("myId"));
		Integer rpId = Integer.valueOf(request.getParameter("rpId"));
		Integer myOrders = Integer.parseInt(request.getParameter("myOrders"));
		Integer rpOrders = Integer.parseInt(request.getParameter("rpOrders"));
		articleCategoryService.updateSequenceService(myId, rpId, myOrders, rpOrders);
		return "操作成功！";	
	}
	
}
