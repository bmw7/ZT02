package com.mavict.setting.navigation;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.mavict.base.BaseEntity;

/**
 * 导航菜单项目设置
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
@Entity
public class Navigation extends BaseEntity{
	private static final long serialVersionUID = -1272195452948428553L;
	private String name;   		// 名称 
	private Integer grade; 		// 层级 
	private Integer orders;     // 排列顺序 
	private String url;         // 访问链接
	
	private Navigation parent;  // 父(上)级目录 
	private List<Navigation> child = new ArrayList<Navigation>(); // 子(下)级目录
	
	public Navigation(){
		
	}
	
	public Navigation(Integer id) {
		super();
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public Integer getOrders() {
		return orders;
	}
	public void setOrders(Integer orders) {
		this.orders = orders;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "parentId")  // 不可与属性名相同
	@NotFound(action = NotFoundAction.IGNORE)
	public Navigation getParent() {
		return parent;
	}
	public void setParent(Navigation parent) {
		this.parent = parent;
	}
	
	@OneToMany(mappedBy = "parent", fetch = FetchType.EAGER) // 关系的维护在多的一方，所以实际真调用getChild是得不到东西的
	public List<Navigation> getChild() {
		return child;
	}
	public void setChild(List<Navigation> child) {
		this.child = child;
	}
	
}
