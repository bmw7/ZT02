package com.mavict.setting;

/**
 * 公共参数
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */

public final class SettingAttributes {
	
	/** 日期格式配比 */
	public static final String[] DATE_PATTERNS = new String[] { "yyyy", "yyyy-MM", "yyyyMM", "yyyy/MM", "yyyy-MM-dd", "yyyyMMdd", "yyyy/MM/dd", "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss", "yyyy/MM/dd HH:mm:ss" };

	/** project.xml文件路径 */
	public static final String PROJECT_XML_PATH = "/project.xml";

	/** project.properties文件路径 */
	public static final String PROJECT_PROPERTIES_PATH = "/project.properties";
	
	/** redis连接地址 */
	public static final String REDIS_SERVER = null;
	
	/** solr服务器地址 */
	public static final String SOLR_SERVER = null;

	/** 不可实例化 */
	private SettingAttributes() {
	}
}
