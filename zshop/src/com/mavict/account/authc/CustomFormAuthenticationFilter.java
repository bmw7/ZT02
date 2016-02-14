package com.mavict.account.authc;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.mavict.utils.EncryptUtils;
import com.mavict.utils.RSAService;

/**
 * authc过滤器 - 前台登陆必经此过滤 
 * 1、获取前台传来的加密的用户名密码，在此解密 2、将解密后的用户名密码传入新的token，供自定义realm验证使用
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
@Component
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {

	@Resource(name="rsaServiceImpl")
	private RSAService rsaService;
	
	private String encryptPassword = "encryptPassword";
	
	
	/** 
	 *  将解密之后的用户名和密码，重新 服务器端 加密成暗文，放入token，以供自定义realm验证
	 *   
	 *  特别要注意的是，若前台表单的用户名和密码字段的名称是username和password的活，getUsername() 和 getPassword() 将自动获取表单的未加密值
	 *  而不会经过本类对该两方法的覆写，即前台的RSA加密解密不起作用。改变前台页面用户名和密码两字段名的命名可以改变这一现象。
	 * */
	@Override
	protected AuthenticationToken createToken(ServletRequest servletRequest,ServletResponse servletResponse) {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		String username = getUsername(request); // username未加密，本方法会自动获取前台表单名为username的表单值
		String password = EncryptUtils.encrypt(getPassword(request));
		rsaService.removePrivateKey(request);  // 将存储在session中的私钥删除。确保下次前台产生 RSA钥匙对 不受影响
	
		return new UsernamePasswordToken(username, password);
	}
	
	/** 解密 前台RSA公钥加密 之后的密码 */
	@Override
	protected String getPassword(ServletRequest servletRequest) {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		return rsaService.decryptParameter(encryptPassword, request);
	}
	
	/** 
	 * 登陆成功跳转 successUrl
	 * 
	 * 不覆写此方法则登陆成功不会跳转到successUrl
	 * 参考文章: http://bbs.csdn.net/topics/390427117   
	 * http://www.xuebuyuan.com/2116235.html(我的shiro之旅: 十二 shiro 踢出用户(同一用户只能一处登录))
	 * */
	@Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
  
        String url = this.getSuccessUrl();
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + url);    //页面跳转
         
        return false;
    }

}


















