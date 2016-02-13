package com.mavict.account.reset;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.mavict.account.Account;
import com.mavict.base.BaseDao;
import com.mavict.base.BaseServiceImpl;

/**
 * 
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */

@Service
public class AccountResetServiceImpl extends BaseServiceImpl<AccountReset, Integer> implements AccountResetService {

	@Resource(name = "accountResetDaoImpl")
	private AccountResetDao accountResetDao;
	
	@Override
	@Resource(name = "accountResetDaoImpl")
	public void setBaseDao(BaseDao<AccountReset, Integer> baseDao) {
		super.setBaseDao(baseDao);
	}

	/**
	 * 1、生成 验证信息 并保存至AccountSet表
	 * 2、返回 修改密码邮件正文 
	 * */
	@Override
	public String doCreateMailContentService(HttpServletRequest request,Account account) {
		return accountResetDao.doCreateMailContent(request, account);
	}


}
