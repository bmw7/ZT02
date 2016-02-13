package com.mavict.guestbook;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mavict.PageInfo;

/**
 * 
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
@Controller
@RequestMapping("/admin/guestbook")
public class GuestbookController {
	
	@Resource(name = "guestbookServiceImpl")
	private GuestbookService guestbookService;

	/** 留言列表 */
	@RequestMapping("/list")
	public String list(ModelMap model,PageInfo pageInfo){
		pageInfo.setPageSize(2);
		model.addAttribute("guestbooks", guestbookService.getPagedContentService(pageInfo));
		model.addAttribute("pageUrl", "/admin/guestbook/list");
		return "/admin/guestbook/list";
	}
	
	/** 未回复留言列表 */
	@RequestMapping("/unlist")
	public String unlist(ModelMap model,PageInfo pageInfo){
		pageInfo.setPageSize(2);
		model.addAttribute("guestbooks", guestbookService.getPagedContentService(pageInfo, "reply", null, "id", "desc"));
		model.addAttribute("pageUrl", "/admin/guestbook/list");
		return "/admin/guestbook/list";
	}
	
	/** 留言删除 */
	@RequestMapping("/del/{id}")
	public String delete(@PathVariable Integer id){
		guestbookService.deleteService(id);
		return "redirect:/admin/guestbook/list";
	}
	
	/** 回复留言 */
	@RequestMapping("/reply/{id}")
	public String reply(@PathVariable Integer id,HttpServletRequest request) throws UnsupportedEncodingException{
		guestbookService.updateService("set reply = ? where id = ?", new String(request.getParameter("reply").getBytes("iso-8859-1"), "utf-8"), id);
		return "redirect:/admin/guestbook/list";
	}
	
	
}
