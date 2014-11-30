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
 * Device entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "device", catalog = "carpool", uniqueConstraints = @UniqueConstraint(columnNames = "device_desc"))
public class Device implements java.io.Serializable {

	// Fields

	private Integer deviceId;
	private String deviceDesc;
	private Set<UserLogin> userLogins = new HashSet<UserLogin>(0);

	// Constructors

	/** default constructor */
	public Device() {
	}

	/** minimal constructor */
	public Device(String deviceDesc) {
		this.deviceDesc = deviceDesc;
	}

	/** full constructor */
	public Device(String deviceDesc, Set<UserLogin> userLogins) {
		this.deviceDesc = deviceDesc;
		this.userLogins = userLogins;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "device_id", unique = true, nullable = false)
	public Integer getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	@Column(name = "device_desc", unique = true, nullable = false, length = 45)
	public String getDeviceDesc() {
		return this.deviceDesc;
	}

	public void setDeviceDesc(String deviceDesc) {
		this.deviceDesc = deviceDesc;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "device")
	public Set<UserLogin> getUserLogins() {
		return this.userLogins;
	}

	public void setUserLogins(Set<UserLogin> userLogins) {
		this.userLogins = userLogins;
	}

}