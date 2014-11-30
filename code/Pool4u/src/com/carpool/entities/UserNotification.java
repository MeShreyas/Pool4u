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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * UserNotification entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_notification", catalog = "carpool")
public class UserNotification implements java.io.Serializable {

	// Fields

	private Integer notificationId;
	private NotificationType notificationType;
	private NotificationEvents notificationEvents;
	private Set<UserData> userDatas = new HashSet<UserData>(0);

	// Constructors

	/** default constructor */
	public UserNotification() {
	}

	/** minimal constructor */
	public UserNotification(NotificationType notificationType,
			NotificationEvents notificationEvents) {
		this.notificationType = notificationType;
		this.notificationEvents = notificationEvents;
	}

	/** full constructor */
	public UserNotification(NotificationType notificationType,
			NotificationEvents notificationEvents, Set<UserData> userDatas) {
		this.notificationType = notificationType;
		this.notificationEvents = notificationEvents;
		this.userDatas = userDatas;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "notification_id", unique = true, nullable = false)
	public Integer getNotificationId() {
		return this.notificationId;
	}

	public void setNotificationId(Integer notificationId) {
		this.notificationId = notificationId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "notification_type_id", nullable = false)
	public NotificationType getNotificationType() {
		return this.notificationType;
	}

	public void setNotificationType(NotificationType notificationType) {
		this.notificationType = notificationType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_id", nullable = false)
	public NotificationEvents getNotificationEvents() {
		return this.notificationEvents;
	}

	public void setNotificationEvents(NotificationEvents notificationEvents) {
		this.notificationEvents = notificationEvents;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "user_preferences", catalog = "carpool", joinColumns = { @JoinColumn(name = "notification_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "user_id", nullable = false, updatable = false) })
	public Set<UserData> getUserDatas() {
		return this.userDatas;
	}

	public void setUserDatas(Set<UserData> userDatas) {
		this.userDatas = userDatas;
	}

}