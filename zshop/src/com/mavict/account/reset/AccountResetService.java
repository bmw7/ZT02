package com.mavict.account.reset;

import javax.servlet.http.HttpServletRequest;

import com.mavict.account.Account;
import com.mavict.base.BaseService;

/**
 * 
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */

public interface AccountResetService extends BaseService<AccountReset, Integer> {
	/** 通过邮箱找回（修改）密码 */	
	String doCreateMailContentService(HttpServletRequest request,Account account);
}
