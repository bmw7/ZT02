package com.mavict.friendlinks;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mavict.base.BaseDao;
import com.mavict.base.BaseServiceImpl;

/**
 * 
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
@Service
public class FriendlinksServiceImpl extends BaseServiceImpl<Friendlinks, Integer> implements FriendlinksService {

	@Resource(name = "friendlinksDaoImpl")
	private FriendlinksDao friendlinksDao;
	
	@Override
	@Resource(name = "friendlinksDaoImpl")
	public void setBaseDao(BaseDao<Friendlinks, Integer> baseDao) {
		super.setBaseDao(baseDao);
	}

	/** 获取links实体  按orders排列*/
	@Override
	public List<Friendlinks> getLinksService() {
		return friendlinksDao.getLinks();
	}
}
