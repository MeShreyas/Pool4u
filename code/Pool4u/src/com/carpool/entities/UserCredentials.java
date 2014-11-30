package com.carpool.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Generated;

/**
 * UserCredentials entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_credentials", catalog = "carpool", uniqueConstraints = @UniqueConstraint(columnNames = "user_name"))
public class UserCredentials implements java.io.Serializable {

	// Fields

	private Integer userId;
	private UserData userData = new UserData();
	private String userName;
	private String password;
	private String isAuthorized;
	private Integer loginAttemptCount;
	private String isActive;
	private Timestamp userCreationDate;
	private Timestamp userUpdateDate;
	private Double userCredits;
	private Timestamp creditExpiryDate;
	private Timestamp accountExpiryDate;
	private Timestamp recCreDate;

	// Constructors

	/** default constructor */
	public UserCredentials() {
	}

	/** minimal constructor */
	public UserCredentials(Integer userId, UserData userData, String userName,
			String password, String isAuthorized, Integer loginAttemptCount,
			String isActive, Timestamp userCreationDate,
			Timestamp userUpdateDate, Timestamp creditExpiryDate, Timestamp accountExpiryDate,
			Timestamp recCreDate) {
		this.userId = userId;
		this.userData = userData;
		this.userName = userName;
		this.password = password;
		this.isAuthorized = isAuthorized;
		this.loginAttemptCount = loginAttemptCount;
		this.isActive = isActive;
		this.userCreationDate = userCreationDate;
		this.userUpdateDate = userUpdateDate;
		this.creditExpiryDate = creditExpiryDate;
		this.accountExpiryDate = accountExpiryDate;
		this.recCreDate = recCreDate;
	}

	/** full constructor */
	public UserCredentials(Integer userId, UserData userData, String userName,
			String password, String isAuthorized, Integer loginAttemptCount,
			String isActive, Timestamp userCreationDate,
			Timestamp userUpdateDate, Double userCredits,
			Timestamp creditExpiryDate, Timestamp accountExpiryDate, Timestamp recCreDate) {
		this.userId = userId;
		this.userData = userData;
		this.userName = userName;
		this.password = password;
		this.isAuthorized = isAuthorized;
		this.loginAttemptCount = loginAttemptCount;
		this.isActive = isActive;
		this.userCreationDate = userCreationDate;
		this.userUpdateDate = userUpdateDate;
		this.userCredits = userCredits;
		this.creditExpiryDate = creditExpiryDate;
		this.accountExpiryDate = accountExpiryDate;
		this.recCreDate = recCreDate;
	}

	// Property accessors
	
	@Id
	@Column(name = "user_id", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@PrimaryKeyJoinColumn
	@JoinColumn(name = "user_id")
	public UserData getUserData() {
		return this.userData;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}

	@Column(name = "user_name", unique = true, nullable = false, length = 100)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "password", nullable = false, length = 45)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "is_authorized", nullable = false, length = 1)
	public String getIsAuthorized() {
		return this.isAuthorized;
	}

	public void setIsAuthorized(String isAuthorized) {
		this.isAuthorized = isAuthorized;
	}

	@Column(name = "login_attempt_count", nullable = false)
	public Integer getLoginAttemptCount() {
		return this.loginAttemptCount;
	}

	public void setLoginAttemptCount(Integer loginAttemptCount) {
		this.loginAttemptCount = loginAttemptCount;
	}

	@Column(name = "is_active", nullable = false, length = 1)
	public String getIsActive() {
		return this.isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	@Column(name = "user_creation_date", nullable = false, length = 19)
	public Timestamp getUserCreationDate() {
		return this.userCreationDate;
	}

	public void setUserCreationDate(Timestamp userCreationDate) {
		this.userCreationDate = userCreationDate;
	}

	@Column(name = "user_update_date", nullable = false, length = 19)
	public Timestamp getUserUpdateDate() {
		return this.userUpdateDate;
	}

	public void setUserUpdateDate(Timestamp userUpdateDate) {
		this.userUpdateDate = userUpdateDate;
	}

	@Column(name = "user_credits", precision = 22, scale = 0)
	public Double getUserCredits() {
		return this.userCredits;
	}

	public void setUserCredits(Double userCredits) {
		this.userCredits = userCredits;
	}

	@Column(name = "credit_expiry_date", nullable = false, length = 19)
	public Timestamp getCreditExpiryDate() {
		return this.creditExpiryDate;
	}

	public void setCreditExpiryDate(Timestamp creditExpiryDate) {
		this.creditExpiryDate = creditExpiryDate;
	}
	
	@Column(name = "account_expiry_date", nullable = false, length = 19)
	public Timestamp getAccountExpiryDate() {
		return this.accountExpiryDate;
	}

	public void setAccountExpiryDate(Timestamp accountExpiryDate) {
		this.accountExpiryDate = accountExpiryDate;
	}

	@Column(name = "rec_cre_date", nullable = false, length = 19)
	public Timestamp getRecCreDate() {
		return this.recCreDate;
	}

	public void setRecCreDate(Timestamp recCreDate) {
		this.recCreDate = recCreDate;
	}

}