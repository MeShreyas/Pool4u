package com.carpool.entities;

import java.sql.Timestamp;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * PoolUserMap entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "pool_user_map", catalog = "carpool")
public class PoolUserMap implements java.io.Serializable {

	// Fields

	private PoolUserMapId id;
	private Pool pool;
	private UserData userData;
	private Timestamp mapTs;
	private String approvalFlag;
	// Constructors

	/** default constructor */
	public PoolUserMap() {
	}

	/** full constructor */
	public PoolUserMap(PoolUserMapId id, Pool pool, UserData userData, Timestamp mapTs, String approvalFlag) {
		this.id = id;
		this.pool = pool;
		this.userData = userData;
		this.mapTs = mapTs;
		this.approvalFlag = approvalFlag;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "poolId", column = @Column(name = "pool_id", nullable = false)),
			@AttributeOverride(name = "userId", column = @Column(name = "user_id", nullable = false)) })
	public PoolUserMapId getId() {
		return this.id;
	}

	public void setId(PoolUserMapId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pool_id", nullable = false, insertable = false, updatable = false)
	public Pool getPool() {
		return this.pool;
	}

	public void setPool(Pool pool) {
		this.pool = pool;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
	public UserData getUserData() {
		return this.userData;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}
	
	@Column(name = "map_ts", nullable = false, length = 19)
	public Timestamp getMapTs() {
		return this.mapTs;
	}

	public void setMapTs(Timestamp mapTs) {
		this.mapTs = mapTs;
	}

	/**
	 * @return the approvalFlag
	 */
	@Column(name = "approval_flag", nullable = false, length = 1)
	public String getApprovalFlag() {
		return approvalFlag;
	}

	/**
	 * @param approvalFlag the approvalFlag to set
	 */
	public void setApprovalFlag(String approvalFlag) {
		this.approvalFlag = approvalFlag;
	}
	
	
}