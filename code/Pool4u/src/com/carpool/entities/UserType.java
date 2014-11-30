package com.carpool.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * UserType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_type", catalog = "carpool", uniqueConstraints = @UniqueConstraint(columnNames = "user_type_desc"))
public class UserType implements java.io.Serializable {

	// Fields

	private Integer userTypeId;
	private String userTypeDesc;
	private Set<UserData> userDatas = new HashSet<UserData>(0);

	// Constructors

	/** default constructor */
	public UserType() {
	}

	/** full constructor */
	public UserType(String userTypeDesc, Set<UserData> userDatas) {
		this.userTypeDesc = userTypeDesc;
		this.userDatas = userDatas;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_type_id", unique = true, nullable = false)
	public Integer getUserTypeId() {
		return this.userTypeId;
	}

	public void setUserTypeId(Integer userTypeId) {
		this.userTypeId = userTypeId;
	}

	@Column(name = "user_type_desc", unique = true, length = 45)
	public String getUserTypeDesc() {
		return this.userTypeDesc;
	}

	public void setUserTypeDesc(String userTypeDesc) {
		this.userTypeDesc = userTypeDesc;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userType")
	public Set<UserData> getUserDatas() {
		return this.userDatas;
	}

	public void setUserDatas(Set<UserData> userDatas) {
		this.userDatas = userDatas;
	}

}