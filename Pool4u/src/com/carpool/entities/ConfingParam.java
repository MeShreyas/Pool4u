package com.carpool.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ConfingParam entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "confing_param", catalog = "carpool")
public class ConfingParam implements java.io.Serializable {

	// Fields

	private String paramName;
	private String paramValue;

	// Constructors

	/** default constructor */
	public ConfingParam() {
	}

	/** full constructor */
	public ConfingParam(String paramName, String paramValue) {
		this.paramName = paramName;
		this.paramValue = paramValue;
	}

	// Property accessors
	@Id
	@Column(name = "param_name", unique = true, nullable = false, length = 128)
	public String getParamName() {
		return this.paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	@Column(name = "param_value", nullable = false, length = 256)
	public String getParamValue() {
		return this.paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

}