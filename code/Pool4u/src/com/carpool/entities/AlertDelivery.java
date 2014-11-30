package com.carpool.entities;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Lob;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * AlertDelivery entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "alert_delivery", catalog = "carpool")
public class AlertDelivery implements java.io.Serializable {

	// Fields

	private Integer alertId;
	private UserData userData;
	private NotificationEvents notificationEvents;
	private NotificationType notificationType;
	private String subject;
	private byte[] message;
	private String status;
	private Timestamp recCreDate;

	// Constructors

	/** default constructor */
	public AlertDelivery() {
	}

	/** minimal constructor */
	public AlertDelivery(UserData userData,
			NotificationEvents notificationEvents,
			NotificationType notificationType, String status,
			Timestamp recCreDate) {
		this.userData = userData;
		this.notificationEvents = notificationEvents;
		this.notificationType = notificationType;
		this.status = status;
		this.recCreDate = recCreDate;
	}

	/** full constructor */
	public AlertDelivery(UserData userData,
			NotificationEvents notificationEvents,
			NotificationType notificationType, String subject, byte[] message,
			String status, Timestamp recCreDate) {
		this.userData = userData;
		this.notificationEvents = notificationEvents;
		this.notificationType = notificationType;
		this.subject = subject;
		this.message = message;
		this.status = status;
		this.recCreDate = recCreDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
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
	@JoinColumn(name = "event_Id", nullable = false)
	public NotificationEvents getNotificationEvents() {
		return this.notificationEvents;
	}

	public void setNotificationEvents(NotificationEvents notificationEvents) {
		this.notificationEvents = notificationEvents;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "notification_Type", nullable = false)
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

	@Column(name = "Message")
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

}