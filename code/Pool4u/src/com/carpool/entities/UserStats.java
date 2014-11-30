package com.carpool.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * UserStats entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_stats", catalog = "carpool")
public class UserStats implements java.io.Serializable {

	// Fields

	private Integer userId;
	private UserData userData;
	private Integer reliableCount;

	// Constructors

	/** default constructor */
	public UserStats() {
	}

	/** full constructor */
	public UserStats(Integer userId, UserData userData, Integer reliableCount) {
		this.userId = userId;
		this.userData = userData;
		this.reliableCount = reliableCount;
	}

	// Property accessors
	@Id
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

	@Column(name = "reliable_count", nullable = false)
	public Integer getReliableCount() {
		return this.reliableCount;
	}

	public void setReliableCount(Integer reliableCount) {
		this.reliableCount = reliableCount;
	}

}