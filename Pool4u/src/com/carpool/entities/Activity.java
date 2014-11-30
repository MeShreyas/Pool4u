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
 * Activity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "activity", catalog = "carpool", uniqueConstraints = @UniqueConstraint(columnNames = "activity_desc"))
public class Activity implements java.io.Serializable {

	// Fields

	private Integer activityId;
	private String activityDesc;
	private Set<UserActivity> userActivities = new HashSet<UserActivity>(0);

	// Constructors

	/** default constructor */
	public Activity() {
	}

	/** full constructor */
	public Activity(String activityDesc, Set<UserActivity> userActivities) {
		this.activityDesc = activityDesc;
		this.userActivities = userActivities;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "activity_id", unique = true, nullable = false)
	public Integer getActivityId() {
		return this.activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	@Column(name = "activity_desc", unique = true, length = 45)
	public String getActivityDesc() {
		return this.activityDesc;
	}

	public void setActivityDesc(String activityDesc) {
		this.activityDesc = activityDesc;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "activity")
	public Set<UserActivity> getUserActivities() {
		return this.userActivities;
	}

	public void setUserActivities(Set<UserActivity> userActivities) {
		this.userActivities = userActivities;
	}

}