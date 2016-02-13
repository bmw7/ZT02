package com.mavict.account.reset;

import java.sql.Timestamp;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;

import com.mavict.account.Account;
import com.mavict.base.BaseDaoImpl;
import com.mavict.utils.EncryptUtils;

/**
 * 
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */

@Repository
public class AccountResetDaoImpl extends BaseDaoImpl<AccountReset, Integer> implements AccountResetDao {

	@Override
	public String doCreateMailContent(HttpServletRequest request,Account account) {
		String uuid = UUID.randomUUID().toString(); // 密钥
        Timestamp expireDate = new Timestamp(System.currentTimeMillis() + 20 * 60 * 1000);// 20分钟后过期
        long date = expireDate.getTime() / 1000 * 1000;  // 忽略毫秒数  mySql 取出时间是忽略毫秒数的
        String validateCode = EncryptUtils.encrypt(account.getId() + "$" + date + "$" + uuid);
        
        AccountReset accountReset = get("accountId",account.getId());
        if (accountReset == null) {
        	accountReset = new AccountReset(account,expireDate,validateCode);
        	save(accountReset);
		}else{
			accountReset.setExpireDate(expireDate);
			accountReset.setValidateCode(validateCode);
			update(accountReset);
		}
        
        String basePath = request.getScheme() + "://"+ request.getServerName() + ":"+ request.getServerPort() + request.getContextPath() + "/";
        String resetPath = basePath + "reset/account?uid="+account.getId() + "&code="+ validateCode;
        
        String mailContent = "请勿回复本邮件。<br/><br/>请  <b><a href=" + resetPath + " target='_BLANK'>点击此处</a></b> 重新设置密码。若无法点击，请复制下面链接至浏览器打开:<br/><br/>"
        		+  resetPath + "<br/><br/>"
                + "<br/>注意:本邮件超过20分钟,链接将会失效,需要重新申请。";	
        
        return mailContent;
	}

}
