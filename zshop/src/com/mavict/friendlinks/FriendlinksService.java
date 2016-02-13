package com.mavict.friendlinks;

import java.util.List;

import com.mavict.base.BaseService;

/**
 * 
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
public interface FriendlinksService extends BaseService<Friendlinks, Integer> {
	/** 获取links实体  按orders排列*/
	List<Friendlinks> getLinksService();
}
