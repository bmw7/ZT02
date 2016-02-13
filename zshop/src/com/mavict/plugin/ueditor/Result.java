package com.mavict.plugin.ueditor;

import org.json.JSONObject;

/**
 * 处理状态接口
 */
public interface Result {
	
	public boolean isSuccess();
	
	public void putInfo(String name,int val);
	public void putInfo(String name,long val);
	public void putInfo(String name,String val);
	
	public JSONObject toJSONObject();

}
