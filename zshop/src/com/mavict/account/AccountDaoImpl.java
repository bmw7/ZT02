package com.mavict.account;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.mavict.base.BaseDaoImpl;
import com.mavict.utils.EncryptUtils;

/**
 * AccountDao 继承了 BaseDao , 并可扩充自己独有的方法
 * BaseDaoImpl实现了 BaseDao 
 * AccountDaoImpl 继承 BaseDaoImpl，并实现 AccountDao : 
 * 1、要实现 AccountDao 独有的方法 
 * 2、AccountDao 继承 BaseDao里面的那些抽象方法，都已在 BaseDaoImpl 中实现，所以要继承 BaseDaoImpl
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
@Repository
public class AccountDaoImpl extends BaseDaoImpl<Account, Integer>implements AccountDao {

	@Override
	public Account getAccount(String username) {
		String hql = "from Account where username =:username";
		Query query = getSession().createQuery(hql);
		query.setString("username", EncryptUtils.encrypt(username));
		Account account = (Account) query.uniqueResult();
		if (account == null) {  return null;  }
		return account;
	}

	@Override
	public Account getAccountFromMail(String mail) {
		String hql = "from Account where email =:email";
		Query query = getSession().createQuery(hql);
		query.setString("email", mail);
		Account account = (Account) query.uniqueResult();
		if (account == null) {  return null;  }
		return account;
	}

}
