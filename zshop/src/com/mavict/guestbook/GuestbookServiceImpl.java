package com.mavict.guestbook;

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
public class GuestbookServiceImpl extends BaseServiceImpl<Guestbook, Integer> implements GuestbookService {

	@Resource(name = "guestbookDaoImpl")
	private GuestbookDao guestbookDao;
	
	@Override
	@Resource(name = "guestbookDaoImpl")
	public void setBaseDao(BaseDao<Guestbook, Integer> baseDao) {
		super.setBaseDao(baseDao);
	}

	
}
