package com.mavict.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间加减工具类
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
public final class TimeUtils {
	
	/** 不可实例化  */
	private TimeUtils() {
		
	}
	
	/** date增加1000年*/
	public static Date add1000(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, +1000);
		return calendar.getTime();	
	}
	
	/** date减少1000年*/
	public static Date cut1000(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, -1000);
		return calendar.getTime();	
	}
	
	/** 判断date是否超过3000年 */
	public static boolean isOver3000(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date year3000 = null;
		try {
			year3000 = df.parse("3000-01-01 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return (date.getTime() > year3000.getTime()) ? true : false;		
	}
}
