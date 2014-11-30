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
 * NotificationEvents entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "notification_events", catalog = "carpool", uniqueConstraints = @UniqueConstraint(columnNames = {
		"event_desc", "event_id" }))
public class NotificationEvents implements java.io.Serializable {

	// Fields

	private Integer eventId;
	private String eventDesc;
	private String isActive;
	private Set<AlertHistory> alertHistories = new HashSet<AlertHistory>(0);
	private Set<UserNotification> userNotifications = new HashSet<UserNotification>(
			0);
	private Set<AlertDelivery> alertDeliveries = new HashSet<AlertDelivery>(0);

	// Constructors

	/** default constructor */
	public NotificationEvents() {
	}

	/** minimal constructor */
	public NotificationEvents(String eventDesc, String isActive) {
		this.eventDesc = eventDesc;
		this.isActive = isActive;
	}

	/** full constructor */
	public NotificationEvents(String eventDesc, String isActive,
			Set<AlertHistory> alertHistories,
			Set<UserNotification> userNotifications,
			Set<AlertDelivery> alertDeliveries) {
		this.eventDesc = eventDesc;
		this.isActive = isActive;
		this.alertHistories = alertHistories;
		this.userNotifications = userNotifications;
		this.alertDeliveries = alertDeliveries;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "event_id", unique = true, nullable = false)
	public Integer getEventId() {
		return this.eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	@Column(name = "event_desc", nullable = false, length = 45)
	public String getEventDesc() {
		return this.eventDesc;
	}

	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}

	@Column(name = "is_active", nullable = false, length = 2)
	public String getIsActive() {
		return this.isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "notificationEvents")
	public Set<AlertHistory> getAlertHistories() {
		return this.alertHistories;
	}

	public void setAlertHistories(Set<AlertHistory> alertHistories) {
		this.alertHistories = alertHistories;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "notificationEvents")
	public Set<UserNotification> getUserNotifications() {
		return this.userNotifications;
	}

	public void setUserNotifications(Set<UserNotification> userNotifications) {
		this.userNotifications = userNotifications;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "notificationEvents")
	public Set<AlertDelivery> getAlertDeliveries() {
		return this.alertDeliveries;
	}

	public void setAlertDeliveries(Set<AlertDelivery> alertDeliveries) {
		this.alertDeliveries = alertDeliveries;
	}

}