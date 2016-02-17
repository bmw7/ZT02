package com.mavict.member;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.mavict.base.BaseDao;
import com.mavict.base.BaseServiceImpl;

/**
 * 
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
@Service
public class MemberServiceImpl extends BaseServiceImpl<Member, Integer> implements MemberService {

	@Resource(name = "memberDaoImpl")
	private MemberDao memberDao;
	
	@Override
	@Resource(name = "memberDaoImpl")
	public void setBaseDao(BaseDao<Member, Integer> baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	public Member getCurrent() {
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		if (requestAttributes != null) {
			HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
			MemberPrincipal principal = (MemberPrincipal) request.getSession().getAttribute(Member.PRINCIPAL_NAME);
			if (principal != null) {
				return memberDao.get(principal.getId());
			}
		}
		return null;
	}
}
