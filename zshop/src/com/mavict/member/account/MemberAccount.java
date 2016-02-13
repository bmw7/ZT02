package com.mavict.member.account;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.mavict.base.BaseEntity;
import com.mavict.member.Member;

/**
 * 资金变动记录账户
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
@Entity
public class MemberAccount extends BaseEntity {
	private static final long serialVersionUID = 3439767956054197978L;
	
	/* 资金变动类型  */
	public enum Type{
		/* 充值  */
		charge,
		/* 提取  */
		draw,
		/* 消费  */
		buy,
		/* 冻结  */
		freeze,
		/* 解冻  */
		thaw	
	}
	
	/* 资金变动结果  */
	public enum Result{
		/* 成功  */
		success,
		/* 失败  */
		fail,
		/* 未完成  */
		ongoing
	}
	
	/* 资金数额  */
	private Float amount;
	/* 资金变动类型  */
	private Type type;
	/* 资金变动结果  */
	private Result result;
	/* 会员  */
	private Member member;
	/* 操作时间  */
	private Date createDate;
	
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	
	@ManyToOne
	@JoinColumn(name = "memberId")
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
