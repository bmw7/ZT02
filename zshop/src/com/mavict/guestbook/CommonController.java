package com.mavict.guestbook;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mavict.guestbook.Guestbook;
import com.mavict.guestbook.GuestbookService;

/**
 * 
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
@Controller
public class CommonController {
	
	@Resource(name = "guestbookServiceImpl")
	private GuestbookService guestbookService;

	@RequestMapping("/guestbook")
	public String guestbook(){
		return "/client/guestbook/client";
	}
	
	@RequestMapping("/guestbook/submit")
	@ResponseBody
	public String guestbookSubmit(HttpServletRequest request,Guestbook guestbook){
		guestbook.setEmail(request.getParameter("email"));
		guestbook.setPhone(request.getParameter("phone"));
		guestbook.setMessage(request.getParameter("message"));
		guestbook.setIp(request.getRemoteAddr());
		guestbook.setCreateDate(new Date());
		guestbookService.saveService(guestbook);
		return "提交留言成功！";
	}
}
