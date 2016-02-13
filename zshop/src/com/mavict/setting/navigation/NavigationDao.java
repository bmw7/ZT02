package com.mavict.setting.navigation;

import java.util.List;

import com.mavict.base.BaseDao;

/**
 * 
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */

public interface NavigationDao extends BaseDao<Navigation, Integer> {
	/** 获取所有分类  */
	List<Navigation> getAll();
	
	/** 判断是否有下级栏目*/
	boolean hasChild(Integer parentId);
	

}
