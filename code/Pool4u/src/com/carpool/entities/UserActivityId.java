package com.carpool.entities;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * UserActivityId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class UserActivityId implements java.io.Serializable {

	// Fields

	private Integer userId;
	private Integer activityId;
	private Timestamp activityTs;

	// Constructors

	/** default constructor */
	public UserActivityId() {
	}

	/** full constructor */
	public UserActivityId(Integer userId, Integer activityId,
			Timestamp activityTs) {
		this.userId = userId;
		this.activityId = activityId;
		this.activityTs = activityTs;
	}

	// Property accessors

	@Column(name = "user_id", nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "activity_id", nullable = false)
	public Integer getActivityId() {
		return this.activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	@Column(name = "activity_ts", nullable = false, length = 19)
	public Timestamp getActivityTs() {
		return this.activityTs;
	}

	public void setActivityTs(Timestamp activityTs) {
		this.activityTs = activityTs;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UserActivityId))
			return false;
		UserActivityId castOther = (UserActivityId) other;

		return ((this.getUserId() == castOther.getUserId()) || (this
				.getUserId() != null
				&& castOther.getUserId() != null && this.getUserId().equals(
				castOther.getUserId())))
				&& ((this.getActivityId() == castOther.getActivityId()) || (this
						.getActivityId() != null
						&& castOther.getActivityId() != null && this
						.getActivityId().equals(castOther.getActivityId())))
				&& ((this.getActivityTs() == castOther.getActivityTs()) || (this
						.getActivityTs() != null
						&& castOther.getActivityTs() != null && this
						.getActivityTs().equals(castOther.getActivityTs())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37
				* result
				+ (getActivityId() == null ? 0 : this.getActivityId()
						.hashCode());
		result = 37
				* result
				+ (getActivityTs() == null ? 0 : this.getActivityTs()
						.hashCode());
		return result;
	}

}