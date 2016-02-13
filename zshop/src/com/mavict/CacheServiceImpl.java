package com.mavict;

import javax.annotation.Resource;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.mavict.setting.SettingUtils;
import com.mavict.utils.SpringUtils;

import freemarker.template.TemplateModelException;

/**
 * Service - 缓存
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
@Service
public class CacheServiceImpl implements CacheService {

	@Resource(name = "ehCacheManager")
	private CacheManager cacheManager;
	@Resource(name = "freeMarkerConfigurer")
	private FreeMarkerConfigurer freeMarkerConfigurer;
	/** 无法注入messageSource */
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;

	public String getDiskStorePath() {
		return cacheManager.getConfiguration().getDiskStoreConfiguration().getPath();
	}

	public int getCacheSize() {
		int cacheSize = 0;
		String[] cacheNames = cacheManager.getCacheNames();
		if (cacheNames != null) {
			for (String cacheName : cacheNames) {
				Ehcache cache = cacheManager.getEhcache(cacheName);
				if (cache != null) {
					cacheSize += cache.getSize();
				}
			}
		}
		return cacheSize;
	}

	@CacheEvict(value = { "setting" }, allEntries = true)
	public void clear() {
		//reloadableResourceBundleMessageSource = (ReloadableResourceBundleMessageSource) SpringUtils.getBean("messageSource"); 
		//reloadableResourceBundleMessageSource.clearCache();
		try {
			freeMarkerConfigurer.getConfiguration().setSharedVariable("setting", SettingUtils.get());
		} catch (TemplateModelException e) {
			e.printStackTrace();
		}
		freeMarkerConfigurer.getConfiguration().clearTemplateCache();
	}

}