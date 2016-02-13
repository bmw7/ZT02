package com.mavict.setting;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.io.IOUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.core.io.ClassPathResource;


/**
 * Setting CommonAttributes EnumConverter
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */

public class SettingUtils {

	private static final CacheManager cacheManager = CacheManager.create();
	private static final BeanUtilsBean beanUtils = new BeanUtilsBean();
	private static final String SETTING_NODES = "/project/setting";
		
	/** 
	 * 读取 project.xml中的属性值,并赋值给对应的setting对象属性  
	 * @return 系统设置对象 Setting
	 * */
	public static Setting get(){
		
		Ehcache cache = cacheManager.getEhcache(Setting.CACHE_NAME);
		net.sf.ehcache.Element cacheElement = cache.get(Setting.CACHE_KEY);
		Setting setting;
		
		/** setting缓存不存在时,读取xml文件,将预定义值设置进setting实体属性*/
		if (cacheElement != null) {
			setting = (com.mavict.setting.Setting) cacheElement.getObjectValue();  
		} else {
			setting = new Setting();
			try {
				File settingFile = new ClassPathResource(SettingAttributes.PROJECT_XML_PATH).getFile();
				Document document = new SAXReader().read(settingFile);
				List<Element> elements = document.selectNodes(SETTING_NODES);  
				for (Element element : elements) {
					String name = element.attributeValue("name");
					String value = element.attributeValue("value");
					beanUtils.setProperty(setting, name, value);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}
		
		return setting;
	}
	
	
	/** 
	 * 设置 系统设置 
	 * 
	 * 1、将setting对象中的属性值,对应写进xml文件,xml文件写进硬盘
	 * 2、将setting对象写进缓存
	 * */
	public static void set(Setting setting){
		try {
			File projectXmlFile = new ClassPathResource(SettingAttributes.PROJECT_XML_PATH).getFile();
			Document document = new SAXReader().read(projectXmlFile);
			List<Element> elements = document.selectNodes(SETTING_NODES);  
			for (Element element : elements) {
				try {
					String name = element.attributeValue("name");        // 读取xml文件中 name的值(名字)
					Attribute attribute = element.attribute("value");    // 获取xml文件中value的属性节点
					String value = beanUtils.getProperty(setting, name); // 根据name的值，获取     提交上来   的setting对象的对应的name属性的值
					attribute.setValue(value);                           // 在xml文件中为该属性节点赋值
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}
			}

			// 将上面每个元素的属性节点赋值完毕的document写入硬盘
			FileOutputStream fileOutputStream = null;
			XMLWriter xmlWriter = null;
			try {
				OutputFormat outputFormat = OutputFormat.createPrettyPrint();
				outputFormat.setEncoding("UTF-8");
				outputFormat.setIndent(true);
				outputFormat.setIndent("	");
				outputFormat.setNewlines(true);
				fileOutputStream = new FileOutputStream(projectXmlFile);
				xmlWriter = new XMLWriter(fileOutputStream, outputFormat);
				xmlWriter.write(document);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (xmlWriter != null) {
					try {
						xmlWriter.close();
					} catch (IOException e) {
					}
				}
				IOUtils.closeQuietly(fileOutputStream);
			}

			// 写入缓存
			Ehcache cache = cacheManager.getEhcache(Setting.CACHE_NAME);
			cache.put(new net.sf.ehcache.Element(Setting.CACHE_KEY, setting));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 不可实例化
	private SettingUtils() {
	}
}
