package com.carpool.entities;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * FriendMappingId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class FriendMappingId implements java.io.Serializable {

	// Fields

	private Integer userId;
	private Integer friendId;
	private Integer travelCount;
	private Timestamp travelTs;

	// Constructors

	/** default constructor */
	public FriendMappingId() {
	}

	/** minimal constructor */
	public FriendMappingId(Integer userId, Integer friendId, Timestamp travelTs) {
		this.userId = userId;
		this.friendId = friendId;
		this.travelTs = travelTs;
	}

	/** full constructor */
	public FriendMappingId(Integer userId, Integer friendId,
			Integer travelCount, Timestamp travelTs) {
		this.userId = userId;
		this.friendId = friendId;
		this.travelCount = travelCount;
		this.travelTs = travelTs;
	}

	// Property accessors

	@Column(name = "user_id", nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "friend_id", nullable = false)
	public Integer getFriendId() {
		return this.friendId;
	}

	public void setFriendId(Integer friendId) {
		this.friendId = friendId;
	}

	@Column(name = "travel_count")
	public Integer getTravelCount() {
		return this.travelCount;
	}

	public void setTravelCount(Integer travelCount) {
		this.travelCount = travelCount;
	}

	@Column(name = "travel_ts", nullable = false, length = 19)
	public Timestamp getTravelTs() {
		return this.travelTs;
	}

	public void setTravelTs(Timestamp travelTs) {
		this.travelTs = travelTs;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof FriendMappingId))
			return false;
		FriendMappingId castOther = (FriendMappingId) other;

		return ((this.getUserId() == castOther.getUserId()) || (this
				.getUserId() != null
				&& castOther.getUserId() != null && this.getUserId().equals(
				castOther.getUserId())))
				&& ((this.getFriendId() == castOther.getFriendId()) || (this
						.getFriendId() != null
						&& castOther.getFriendId() != null && this
						.getFriendId().equals(castOther.getFriendId())))
				&& ((this.getTravelCount() == castOther.getTravelCount()) || (this
						.getTravelCount() != null
						&& castOther.getTravelCount() != null && this
						.getTravelCount().equals(castOther.getTravelCount())))
				&& ((this.getTravelTs() == castOther.getTravelTs()) || (this
						.getTravelTs() != null
						&& castOther.getTravelTs() != null && this
						.getTravelTs().equals(castOther.getTravelTs())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37 * result
				+ (getFriendId() == null ? 0 : this.getFriendId().hashCode());
		result = 37
				* result
				+ (getTravelCount() == null ? 0 : this.getTravelCount()
						.hashCode());
		result = 37 * result
				+ (getTravelTs() == null ? 0 : this.getTravelTs().hashCode());
		return result;
	}

}