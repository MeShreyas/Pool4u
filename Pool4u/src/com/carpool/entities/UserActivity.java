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
 * UserActivity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_activity", catalog = "carpool")
public class UserActivity implements java.io.Serializable {

	// Fields

	private UserActivityId id;
	private UserData userData;
	private Activity activity;

	// Constructors

	/** default constructor */
	public UserActivity() {
	}

	/** full constructor */
	public UserActivity(UserActivityId id, UserData userData, Activity activity) {
		this.id = id;
		this.userData = userData;
		this.activity = activity;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "userId", column = @Column(name = "user_id", nullable = false)),
			@AttributeOverride(name = "activityId", column = @Column(name = "activity_id", nullable = false)),
			@AttributeOverride(name = "activityTs", column = @Column(name = "activity_ts", nullable = false, length = 19)) })
	public UserActivityId getId() {
		return this.id;
	}

	public void setId(UserActivityId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
	public UserData getUserData() {
		return this.userData;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "activity_id", nullable = false, insertable = false, updatable = false)
	public Activity getActivity() {
		return this.activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

}