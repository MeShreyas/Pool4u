package com.carpool.entities;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * PoolUserMapId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class PoolUserMapId implements java.io.Serializable {

	// Fields

	private Integer poolId;
	private Integer userId;
	

	// Constructors

	/** default constructor */
	public PoolUserMapId() {
	}

	/** full constructor */
	public PoolUserMapId(Integer poolId, Integer userId) {
		this.poolId = poolId;
		this.userId = userId;
		
	}

	// Property accessors

	@Column(name = "pool_id", nullable = false)
	public Integer getPoolId() {
		return this.poolId;
	}

	public void setPoolId(Integer poolId) {
		this.poolId = poolId;
	}

	@Column(name = "user_id", nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PoolUserMapId))
			return false;
		PoolUserMapId castOther = (PoolUserMapId) other;

		return ((this.getPoolId() == castOther.getPoolId()) || (this
				.getPoolId() != null
				&& castOther.getPoolId() != null && this.getPoolId().equals(
				castOther.getPoolId())))
				&& ((this.getUserId() == castOther.getUserId()) || (this
						.getUserId() != null
						&& castOther.getUserId() != null && this.getUserId()
						.equals(castOther.getUserId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getPoolId() == null ? 0 : this.getPoolId().hashCode());
		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		
		return result;
	}

}