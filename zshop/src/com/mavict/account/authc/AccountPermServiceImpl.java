package com.mavict.account.authc;

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
public class AccountPermServiceImpl extends BaseServiceImpl<AccountPerm, Integer> implements AccountPermService {

	@Resource(name = "accountPermDaoImpl")
	private AccountPermDao accountPermDao;
	
	@Override
	@Resource(name = "accountPermDaoImpl")
	public void setBaseDao(BaseDao<AccountPerm, Integer> baseDao) {
		super.setBaseDao(baseDao);
	}

}
