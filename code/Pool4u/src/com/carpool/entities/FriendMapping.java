package com.carpool.entities;

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
 * FriendMapping entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "friend_mapping", catalog = "carpool")
public class FriendMapping implements java.io.Serializable {

	// Fields

	private FriendMappingId id;
	private UserData userDataByUserId;
	private UserData userDataByFriendId;

	// Constructors

	/** default constructor */
	public FriendMapping() {
	}

	/** full constructor */
	public FriendMapping(FriendMappingId id, UserData userDataByUserId,
			UserData userDataByFriendId) {
		this.id = id;
		this.userDataByUserId = userDataByUserId;
		this.userDataByFriendId = userDataByFriendId;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "userId", column = @Column(name = "user_id", nullable = false)),
			@AttributeOverride(name = "friendId", column = @Column(name = "friend_id", nullable = false)),
			@AttributeOverride(name = "travelCount", column = @Column(name = "travel_count")),
			@AttributeOverride(name = "travelTs", column = @Column(name = "travel_ts", nullable = false, length = 19)) })
	public FriendMappingId getId() {
		return this.id;
	}

	public void setId(FriendMappingId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
	public UserData getUserDataByUserId() {
		return this.userDataByUserId;
	}

	public void setUserDataByUserId(UserData userDataByUserId) {
		this.userDataByUserId = userDataByUserId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "friend_id", nullable = false, insertable = false, updatable = false)
	public UserData getUserDataByFriendId() {
		return this.userDataByFriendId;
	}

	public void setUserDataByFriendId(UserData userDataByFriendId) {
		this.userDataByFriendId = userDataByFriendId;
	}

}