package com.carpool.entities;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * UserLogin entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_login", catalog = "carpool")
public class UserLogin implements java.io.Serializable {

	// Fields

	private Integer userId;
	private UserData userData;
	private Integer deviceId;
	private Device device;
	private Timestamp loginTs;
	private String loginStatus;
	private String statusComments;
	private Timestamp recCreDate;

	// Constructors

	/** default constructor */
	public UserLogin() {
	}

	/** minimal constructor */
	public UserLogin(UserData userData, Timestamp recCreDate) {
		this.userData = userData;
		this.recCreDate = recCreDate;
	}

	/** full constructor */
	public UserLogin(UserData userData, Device device, Timestamp loginTs,
			String loginStatus, String statusComments, Timestamp recCreDate) {
		this.userData = userData;
		this.device = device;
		this.loginTs = loginTs;
		this.loginStatus = loginStatus;
		this.statusComments = statusComments;
		this.recCreDate = recCreDate;
	}

	// Property accessors
	@Id
	//@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	@JoinColumn(name = "user_id")
	public UserData getUserData() {
		return this.userData;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}

	@Column(name = "device_id", unique = true, nullable = false,insertable=false, updatable=false)
	public Integer getDeviceId() {
		return this.userId;
	}
	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "device_id", nullable = false)
	public Device getDevice() {
		return this.device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	@Column(name = "login_ts", length = 19)
	public Timestamp getLoginTs() {
		return this.loginTs;
	}

	public void setLoginTs(Timestamp loginTs) {
		this.loginTs = loginTs;
	}

	@Column(name = "login_status", length = 45)
	public String getLoginStatus() {
		return this.loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	@Column(name = "status_comments", length = 45)
	public String getStatusComments() {
		return this.statusComments;
	}

	public void setStatusComments(String statusComments) {
		this.statusComments = statusComments;
	}

	@Column(name = "rec_cre_date", nullable = false, length = 19)
	public Timestamp getRecCreDate() {
		return this.recCreDate;
	}

	public void setRecCreDate(Timestamp recCreDate) {
		this.recCreDate = recCreDate;
	}

}