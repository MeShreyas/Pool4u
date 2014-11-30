package com.carpool.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * PoolFilterMapId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class PoolFilterMapId implements java.io.Serializable {

	// Fields

	private Integer poolId;
	private Integer filterId;
	

	// Constructors

	/** default constructor */
	public PoolFilterMapId() {
	}

	/** full constructor */
	public PoolFilterMapId(Integer poolId, Integer filterId) {
		this.poolId = poolId;
		this.filterId = filterId;
		
	}

	// Property accessors

	@Column(name = "pool_id", nullable = false)
	public Integer getPoolId() {
		return this.poolId;
	}

	public void setPoolId(Integer poolId) {
		this.poolId = poolId;
	}

	@Column(name = "filter_id", nullable = false)
	public Integer getFilterId() {
		return this.filterId;
	}

	public void setFilterId(Integer filterId) {
		this.filterId = filterId;
	}

	

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PoolFilterMapId))
			return false;
		PoolFilterMapId castOther = (PoolFilterMapId) other;

		return ((this.getPoolId() == castOther.getPoolId()) || (this
				.getPoolId() != null
				&& castOther.getPoolId() != null && this.getPoolId().equals(
				castOther.getPoolId())))
				&& ((this.getFilterId() == castOther.getFilterId()) || (this
						.getFilterId() != null
						&& castOther.getFilterId() != null && this
						.getFilterId().equals(castOther.getFilterId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getPoolId() == null ? 0 : this.getPoolId().hashCode());
		result = 37 * result
				+ (getFilterId() == null ? 0 : this.getFilterId().hashCode());
		return result;
	}

}