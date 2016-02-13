package com.mavict.friendlinks;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mavict.utils.FileOperateUtils;

/**
 * Controller - 友情链接 
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
@Controller
@RequestMapping("/admin/friendlinks")
public class FriendlinksController {
	
	@Resource(name = "friendlinksServiceImpl")
	private FriendlinksService friendlinksService;
	
	/** 链接列表 */
	@RequestMapping("/list")
	public String list(ModelMap model){
		model.addAttribute("links", friendlinksService.getLinksService());
		return "/admin/friendlinks/list";
	}
	
	@RequestMapping("/submit")
	public String submit(@RequestParam MultipartFile myfile,HttpServletRequest request,Friendlinks friendlinks){
		friendlinks.setName(request.getParameter("name"));
		friendlinks.setOrders(Integer.valueOf(request.getParameter("orders")));
		friendlinks.setUrl(request.getParameter("url"));
		friendlinksService.saveService(friendlinks);
		if (!myfile.isEmpty()) {
			FileOperateUtils.uploadFile(myfile, String.valueOf(friendlinks.getId()), "/content/friendlinks", request);
		}
		return "redirect:/admin/friendlinks/list";
	}
	
	@RequestMapping("/submit/{id}")
	public String submit(@RequestParam MultipartFile myfile,@PathVariable Integer id,HttpServletRequest request,Friendlinks friendlinks){
		friendlinks.setId(id);
		friendlinks.setName(request.getParameter("name"));
		friendlinks.setOrders(Integer.valueOf(request.getParameter("orders")));
		friendlinks.setUrl(request.getParameter("url"));
		friendlinksService.updateService(friendlinks);
		if (!myfile.isEmpty()) {
			FileOperateUtils.uploadFile(myfile, String.valueOf(friendlinks.getId()), "/content/friendlinks", request);
		}
		return "redirect:/admin/friendlinks/list";
	}
	
	@RequestMapping("/del/{id}")
	public String del(@PathVariable Integer id){
		friendlinksService.deleteService(id);
		return "redirect:/admin/friendlinks/list";
	}

}
