package com.mavict.product.category;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
@Controller
@RequestMapping("/product")
public class ProductCategoryController {
	
	@Resource(name = "productCategoryServiceImpl")
	private ProductCategoryService productCategoryService;
	
	@RequestMapping("/list")
	public String list(ModelMap model){
		model.addAttribute("treeCategories", productCategoryService.getTreeService());
		return "/client/product/list";
	}
}
