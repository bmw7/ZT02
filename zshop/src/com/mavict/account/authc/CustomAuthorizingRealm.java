package com.mavict.account.authc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.time.DateUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ConcurrentAccessException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.subject.WebSubject;
import org.springframework.stereotype.Component;

import com.mavict.account.Account;
import com.mavict.account.AccountService;

/**
 * 
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */

@Component
public class CustomAuthorizingRealm extends AuthorizingRealm{
	
	@Resource(name = "accountServiceImpl")
	private AccountService accountService;
	
	/** 授权 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		String username = (String) principals.fromRealm(getName()).iterator().next(); // 获取当前登录的用户名
		/** 判断登陆名是否电子邮件 */
		String columnName = (username.indexOf("@") == -1) ? "username" : "email";
		Account account = accountService.getService(columnName, username);
		
		AccountRole role = account.getAccountRole();             /** 获取角色*/
		List<AccountPerm> accountPerms = role.getAccountPerms(); /** 获取权限*/	
		
		List<String> permissions = new ArrayList<String>();
		for (AccountPerm accountPerm : accountPerms) {
			permissions.add(accountPerm.getName());
		}
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(permissions);
		return info;
	}

	/** 验证 */
	@SuppressWarnings("unused")
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException{
		UsernamePasswordToken token = (UsernamePasswordToken) authToken;
		String username = token.getUsername().trim();
		String password = new String(token.getPassword());
		HttpServletRequest request = (HttpServletRequest) ((WebSubject)SecurityUtils.getSubject()).getServletRequest();   
		String ip = request.getRemoteAddr();
		
		/** 判断登陆名是否电子邮件 */
		String columnName = (username.indexOf("@") == -1) ? "username" : "email";
		Account account = accountService.getService(columnName, username);
		
		/** 用户名不正确，无实体 */
		if (account == null) {
			throw new UnknownAccountException();
		}
		
		String dbPassword = account.getPassword();
		String loginIp = account.getLoginIp();
		Integer loginFailureCount = account.getLoginFailureCount();
		boolean isEnabled = account.getIsEnabled();
		boolean isLocked = account.getIsLocked();

		/** 若不匹配，则抛出异常，由AccountController接收异常并判断类型。若无异常抛出，则会生成下面正确的认证信息SimpleAuthenticationInfo */
		
		/** 用户被禁用 */
		if (!isEnabled) {
			throw new DisabledAccountException();
		}
		
		/** 用户被锁定 */
		if (isLocked) {
			Date lockedDate = account.getLockedDate();
			Date unlockDate = DateUtils.addMinutes(lockedDate, 2);
			if (new Date().after(unlockDate)) {
				loginFailureCount = 0;
				account.setLoginFailureCount(loginFailureCount);
				isLocked = false;
				account.setIsLocked(isLocked);
				account.setLockedDate(null);
				accountService.updateService(account);
			}else {
				throw new LockedAccountException();
			}
		}
		
		/** 密码验证 */
		if (!password.equals(dbPassword)) {
			loginFailureCount += 1;
			account.setLoginFailureCount(loginFailureCount);
			
			if (loginFailureCount >= 5) {
				account.setIsLocked(true);
				account.setLockedDate(new Date());
			}
			accountService.updateService(account);
			throw new ConcurrentAccessException();
		}else {
			account.setLoginIp(ip);
			account.setLoginCount(account.getLoginCount()+1);
			
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			session.setAttribute("loginName", account.getName());
			session.setAttribute("loginCount", account.getLoginCount());
			session.setAttribute("loginDate", account.getLoginDate());
			session.setAttribute("loginAddress", ip);
			
			account.setLoginDate(new Date());
			account.setLoginFailureCount(0);
			accountService.updateService(account);
		}
		
		return new SimpleAuthenticationInfo(username, password, getName());
	}



}
