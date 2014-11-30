package com.carpool.entities;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * AlertHistory entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "alert_history", catalog = "carpool")
public class AlertHistory implements java.io.Serializable {

	// Fields

	private Integer alertId;
	private UserData userData;
	private NotificationEvents notificationEvents;
	private NotificationType notificationType;
	private String subject;
	private byte[] message;
	private String status;
	private Timestamp recCreDate;
	private Timestamp alertDeliveryDate;

	// Constructors

	/** default constructor */
	public AlertHistory() {
	}

	/** minimal constructor */
	public AlertHistory(Integer alertId, UserData userData,
			NotificationEvents notificationEvents,
			NotificationType notificationType, String status,
			Timestamp recCreDate, Timestamp alertDeliveryDate) {
		this.alertId = alertId;
		this.userData = userData;
		this.notificationEvents = notificationEvents;
		this.notificationType = notificationType;
		this.status = status;
		this.recCreDate = recCreDate;
		this.alertDeliveryDate = alertDeliveryDate;
	}

	/** full constructor */
	public AlertHistory(Integer alertId, UserData userData,
			NotificationEvents notificationEvents,
			NotificationType notificationType, String subject, byte[] message,
			String status, Timestamp recCreDate, Timestamp alertDeliveryDate) {
		this.alertId = alertId;
		this.userData = userData;
		this.notificationEvents = notificationEvents;
		this.notificationType = notificationType;
		this.subject = subject;
		this.message = message;
		this.status = status;
		this.recCreDate = recCreDate;
		this.alertDeliveryDate = alertDeliveryDate;
	}

	// Property accessors
	@Id
	@Column(name = "alert_Id", unique = true, nullable = false)
	public Integer getAlertId() {
		return this.alertId;
	}

	public void setAlertId(Integer alertId) {
		this.alertId = alertId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	public UserData getUserData() {
		return this.userData;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_id", nullable = false)
	public NotificationEvents getNotificationEvents() {
		return this.notificationEvents;
	}

	public void setNotificationEvents(NotificationEvents notificationEvents) {
		this.notificationEvents = notificationEvents;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "notification_type", nullable = false)
	public NotificationType getNotificationType() {
		return this.notificationType;
	}

	public void setNotificationType(NotificationType notificationType) {
		this.notificationType = notificationType;
	}

	@Column(name = "subject", length = 512)
	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Column(name = "message")
	public byte[] getMessage() {
		return this.message;
	}

	public void setMessage(byte[] message) {
		this.message = message;
	}

	@Column(name = "status", nullable = false, length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "rec_cre_date", nullable = false, length = 19)
	public Timestamp getRecCreDate() {
		return this.recCreDate;
	}

	public void setRecCreDate(Timestamp recCreDate) {
		this.recCreDate = recCreDate;
	}

	@Column(name = "alert_delivery_date", nullable = false, length = 19)
	public Timestamp getAlertDeliveryDate() {
		return this.alertDeliveryDate;
	}

	public void setAlertDeliveryDate(Timestamp alertDeliveryDate) {
		this.alertDeliveryDate = alertDeliveryDate;
	}

}