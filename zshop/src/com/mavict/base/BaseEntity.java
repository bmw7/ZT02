package com.mavict.base;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.solr.client.solrj.beans.Field;

/**
 * 
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
@MappedSuperclass
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 5220285063411628324L;
	@Field
	public Integer id;

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
