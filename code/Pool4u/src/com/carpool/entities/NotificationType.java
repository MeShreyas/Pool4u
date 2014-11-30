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
 * NotificationType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "notification_type", catalog = "carpool", uniqueConstraints = @UniqueConstraint(columnNames = {
		"notification_type", "notification_desc" }))
public class NotificationType implements java.io.Serializable {

	// Fields

	private Integer notificationType;
	private String notificationDesc;
	private String isActive;
	private Set<AlertHistory> alertHistories = new HashSet<AlertHistory>(0);
	private Set<UserNotification> userNotifications = new HashSet<UserNotification>(
			0);
	private Set<AlertDelivery> alertDeliveries = new HashSet<AlertDelivery>(0);

	// Constructors

	/** default constructor */
	public NotificationType() {
	}

	/** minimal constructor */
	public NotificationType(String notificationDesc, String isActive) {
		this.notificationDesc = notificationDesc;
		this.isActive = isActive;
	}

	/** full constructor */
	public NotificationType(String notificationDesc, String isActive,
			Set<AlertHistory> alertHistories,
			Set<UserNotification> userNotifications,
			Set<AlertDelivery> alertDeliveries) {
		this.notificationDesc = notificationDesc;
		this.isActive = isActive;
		this.alertHistories = alertHistories;
		this.userNotifications = userNotifications;
		this.alertDeliveries = alertDeliveries;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "notification_type", unique = true, nullable = false)
	public Integer getNotificationType() {
		return this.notificationType;
	}

	public void setNotificationType(Integer notificationType) {
		this.notificationType = notificationType;
	}

	@Column(name = "notification_desc", nullable = false, length = 45)
	public String getNotificationDesc() {
		return this.notificationDesc;
	}

	public void setNotificationDesc(String notificationDesc) {
		this.notificationDesc = notificationDesc;
	}

	@Column(name = "is_active", nullable = false, length = 2)
	public String getIsActive() {
		return this.isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "notificationType")
	public Set<AlertHistory> getAlertHistories() {
		return this.alertHistories;
	}

	public void setAlertHistories(Set<AlertHistory> alertHistories) {
		this.alertHistories = alertHistories;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "notificationType")
	public Set<UserNotification> getUserNotifications() {
		return this.userNotifications;
	}

	public void setUserNotifications(Set<UserNotification> userNotifications) {
		this.userNotifications = userNotifications;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "notificationType")
	public Set<AlertDelivery> getAlertDeliveries() {
		return this.alertDeliveries;
	}

	public void setAlertDeliveries(Set<AlertDelivery> alertDeliveries) {
		this.alertDeliveries = alertDeliveries;
	}

}