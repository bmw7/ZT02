package com.mavict.friendlinks;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mavict.base.BaseDaoImpl;

/**
 * 
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
@Repository
public class FriendlinksDaoImpl extends BaseDaoImpl<Friendlinks, Integer> implements FriendlinksDao {

	/** 获取links实体  按orders排列*/
	@Override
	public List<Friendlinks> getLinks() {
		String hql = "from Friendlinks order by orders asc";
		return getSession().createQuery(hql).list();
	}

}
