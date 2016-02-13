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
public class AccountRoleServiceImpl extends BaseServiceImpl<AccountRole, Integer> implements AccountRoleService {

	@Resource(name = "accountRoleDaoImpl")
	private AccountRoleDao accountRoleDao;

	@Override
	@Resource(name = "accountRoleDaoImpl")
	public void setBaseDao(BaseDao<AccountRole, Integer> baseDao) {
		super.setBaseDao(baseDao);
	}
	
	
}
