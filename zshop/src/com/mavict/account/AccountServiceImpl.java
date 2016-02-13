package com.mavict.account;

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
public class AccountServiceImpl extends BaseServiceImpl<Account, Integer> implements AccountService  {

	@Resource(name = "accountDaoImpl")
	private AccountDao accountDao;
	
	@Override 
	@Resource(name = "accountDaoImpl")
	public void setBaseDao(BaseDao<Account, Integer> baseDao) {
		super.setBaseDao(baseDao);
	}
	
}
