package com.mavict.friendlinks;

import java.util.List;

import com.mavict.base.BaseDao;

/**
 * 
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
public interface FriendlinksDao extends BaseDao<Friendlinks, Integer> {
	/** 获取links实体  按orders排列*/
	List<Friendlinks> getLinks();
}
