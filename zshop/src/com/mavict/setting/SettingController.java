package com.mavict.setting;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mavict.CacheService;
import com.mavict.setting.mail.Mail;
import com.mavict.utils.MailUtils;

/**
 * 
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
@Controller
@RequestMapping("/admin/setting")
public class SettingController {
	
	@Resource(name = "cacheServiceImpl")
	private CacheService cacheService;

	/** 编辑 */
	@RequestMapping("/edit")
	public String edit(ModelMap model) {
		return "/admin/setting/edit";
	}
	
	/** 更新 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Setting setting) {

		Setting srcSetting = SettingUtils.get(); // xml属性值赋值构造setting对象
		if (StringUtils.isEmpty(setting.getSmtpPassword())) {
			setting.setSmtpPassword(srcSetting.getSmtpPassword());
		}

		setting.setCnzzSiteId(srcSetting.getCnzzSiteId());
		setting.setCnzzPassword(srcSetting.getCnzzPassword());
		SettingUtils.set(setting);
		cacheService.clear();


		OutputStream outputStream = null;
		try {
			org.springframework.core.io.Resource resource = new ClassPathResource(SettingAttributes.PROJECT_PROPERTIES_PATH);
			Properties properties = PropertiesLoaderUtils.loadProperties(resource);
			String templateUpdateDelay = properties.getProperty("template.update_delay");
			String messageCacheSeconds = properties.getProperty("message.cache_seconds");
			
			/**
			 * 开发模式,设置freemarker模板不缓存,即:template_Update_Delay = 0
			 * 非开发模式,设置模板缓存,即:template_Update_Delay = 3600
			 * */
			if (setting.getIsDevelopment()) {
				if (!templateUpdateDelay.equals("0") || !messageCacheSeconds.equals("0")) {
					outputStream = new FileOutputStream(resource.getFile());
					properties.setProperty("template.update_delay", "0");
					properties.setProperty("message.cache_seconds", "0");
					properties.store(outputStream, "PROJECT PROPERTIES");
				}
			} else {
				if (templateUpdateDelay.equals("0") || messageCacheSeconds.equals("0")) {
					outputStream = new FileOutputStream(resource.getFile());
					properties.setProperty("template.update_delay", "3600");
					properties.setProperty("message.cache_seconds", "3600");
					properties.store(outputStream, "PROJECT PROPERTIES");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(outputStream);
		}

		return "redirect:edit";
	}
	
	
	
	/**
	 * 邮件测试   Setting setting = SettingUtils.get();  smtpPassword = setting.getSmtpPassword();
	 */
	@RequestMapping("/mailTest")
	public @ResponseBody String mailTest(HttpServletRequest request) {
		
		String smtpFromMail = request.getParameter("smtpFromMail"); 
		String smtpHost = request.getParameter("smtpHost"); 
		Integer smtpPort = Integer.parseInt(request.getParameter("smtpPort"));
		String smtpUsername = request.getParameter("smtpUsername");
		String smtpPassword = request.getParameter("smtpPassword");
		String toMail = request.getParameter("toMail");
		
		if (StringUtils.isEmpty(toMail)) {
			return "测试邮箱为空！";
		}

		Mail mail = new Mail();
		mail.setHost(smtpHost);
		mail.setSender(smtpFromMail);
		mail.setReceiver(toMail);
		mail.setName(smtpUsername);
		mail.setUsername(smtpFromMail);
		mail.setPassword(smtpPassword);
		mail.setSubject("邮件测试");
		mail.setMessage("这是邮件测试内容。");
		
		return MailUtils.send(mail);		
	}
	
	
}
