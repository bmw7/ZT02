package com.mavict.setting;

import org.apache.commons.beanutils.converters.AbstractConverter;

/**
 * 枚举类型转换
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
public class SettingEnumConverter extends AbstractConverter {

	/** 枚举类型 */
	private final Class<?> enumClass;

	/**
	 * @param enumClass
	 *            枚举类型
	 */
	public SettingEnumConverter(Class<?> enumClass) {
		this(enumClass, null);
	}

	/**
	 * @param enumClass
	 *            枚举类型
	 * @param defaultValue
	 *            默认值
	 */
	public SettingEnumConverter(Class<?> enumClass, Object defaultValue) {
		super(defaultValue);
		this.enumClass = enumClass;
	}

	/**
	 * 获取默认类型
	 * 
	 * @return 默认类型
	 */
	@Override
	protected Class<?> getDefaultType() {
		return this.enumClass;
	}

	/**
	 * 转换为枚举对象
	 * 
	 * @param type
	 *            类型
	 * @param value
	 *            值
	 * @return 枚举对象
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Object convertToType(Class type, Object value) {
		String stringValue = value.toString().trim();
		return Enum.valueOf(type, stringValue);
	}

	/**
	 * 转换为字符串
	 * 
	 * @param value
	 *            值
	 * @return 字符串
	 */
	protected String convertToString(Object value) {
		return value.toString();
	}

}