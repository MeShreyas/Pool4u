package com.carpool.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

/**
 * UserData entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_data", catalog = "carpool")
public class UserData implements java.io.Serializable {

	// Fields

	private Integer userId;
	private Company company= new Company();
	private Vendor vendor;
	private UserType userType = new UserType();
	private String firstName;
	private String lastName;
	private String displayName;
	private String gender;
	private String phone;
	private String email;
	private Set<FriendMapping> friendMappingsForFriendId = new HashSet<FriendMapping>(
			0);
	private Set<FriendMapping> friendMappingsForUserId = new HashSet<FriendMapping>(
			0);
	private Set<PoolUserMap> poolUserMaps = new HashSet<PoolUserMap>(0);
	private Set<UserActivity> userActivities = new HashSet<UserActivity>(0);
	private Set<AlertHistory> alertHistories = new HashSet<AlertHistory>(0);
	private Set<UserNotification> userNotifications = new HashSet<UserNotification>(
			0);
	
	private Set<AlertDelivery> alertDeliveries = new HashSet<AlertDelivery>(0);
	private Set<UserAllowedCompanies> userAllowedCompanieses = new HashSet<UserAllowedCompanies>(
			0);
	private Set<Pool> pools = new HashSet<Pool>(0);
	private Set<PoolQuery> poolQueries = new HashSet<PoolQuery>(0);

	// Constructors

	/** default constructor */
	public UserData() {
	}

	/** minimal constructor */
	public UserData(UserType userType, String firstName) {
		this.userType = userType;
		this.firstName = firstName;
	}

	/** full constructor */
	public UserData(Company company, Vendor vendor, UserType userType,
			String firstName, String lastName, String displayName,
			String gender, String phone, String email,
			Set<FriendMapping> friendMappingsForFriendId,
			Set<FriendMapping> friendMappingsForUserId,
			Set<PoolUserMap> poolUserMaps, Set<UserActivity> userActivities,
			Set<AlertHistory> alertHistories,
			Set<UserNotification> userNotifications,
			Set<AlertDelivery> alertDeliveries,
			Set<UserAllowedCompanies> userAllowedCompanieses, Set<Pool> pools,
			Set<PoolQuery> poolQueries) {
		this.company = company;
		this.vendor = vendor;
		this.userType = userType;
		this.firstName = firstName;
		this.lastName = lastName;
		this.displayName = displayName;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
		this.friendMappingsForFriendId = friendMappingsForFriendId;
		this.friendMappingsForUserId = friendMappingsForUserId;
		this.poolUserMaps = poolUserMaps;
		this.userActivities = userActivities;
		this.alertHistories = alertHistories;
		this.userNotifications = userNotifications;
		
		this.alertDeliveries = alertDeliveries;
		this.userAllowedCompanieses = userAllowedCompanieses;
		this.pools = pools;
		this.poolQueries = poolQueries;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "company_id")
	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vendor_id")
	public Vendor getVendor() {
		return this.vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_type_id", nullable = false)
	public UserType getUserType() {
		return this.userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	@Column(name = "first_name", nullable = false, length = 45)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name", length = 45)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "display_name", length = 45)
	public String getDisplayName() {
		return this.displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Column(name = "gender", length = 45)
	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "phone", length = 45)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "email", length = 45)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userDataByFriendId")
	public Set<FriendMapping> getFriendMappingsForFriendId() {
		return this.friendMappingsForFriendId;
	}

	public void setFriendMappingsForFriendId(
			Set<FriendMapping> friendMappingsForFriendId) {
		this.friendMappingsForFriendId = friendMappingsForFriendId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userDataByUserId")
	public Set<FriendMapping> getFriendMappingsForUserId() {
		return this.friendMappingsForUserId;
	}

	public void setFriendMappingsForUserId(
			Set<FriendMapping> friendMappingsForUserId) {
		this.friendMappingsForUserId = friendMappingsForUserId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userData")
	public Set<PoolUserMap> getPoolUserMaps() {
		return this.poolUserMaps;
	}

	public void setPoolUserMaps(Set<PoolUserMap> poolUserMaps) {
		this.poolUserMaps = poolUserMaps;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userData")
	public Set<UserActivity> getUserActivities() {
		return this.userActivities;
	}

	public void setUserActivities(Set<UserActivity> userActivities) {
		this.userActivities = userActivities;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userData")
	public Set<AlertHistory> getAlertHistories() {
		return this.alertHistories;
	}

	public void setAlertHistories(Set<AlertHistory> alertHistories) {
		this.alertHistories = alertHistories;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userDatas")
	public Set<UserNotification> getUserNotifications() {
		return this.userNotifications;
	}

	public void setUserNotifications(Set<UserNotification> userNotifications) {
		this.userNotifications = userNotifications;
	}

	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userData")
	public Set<AlertDelivery> getAlertDeliveries() {
		return this.alertDeliveries;
	}

	public void setAlertDeliveries(Set<AlertDelivery> alertDeliveries) {
		this.alertDeliveries = alertDeliveries;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userData")
	public Set<UserAllowedCompanies> getUserAllowedCompanieses() {
		return this.userAllowedCompanieses;
	}

	public void setUserAllowedCompanieses(
			Set<UserAllowedCompanies> userAllowedCompanieses) {
		this.userAllowedCompanieses = userAllowedCompanieses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userData")
	public Set<Pool> getPools() {
		return this.pools;
	}

	public void setPools(Set<Pool> pools) {
		this.pools = pools;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userData")
	public Set<PoolQuery> getPoolQueries() {
		return this.poolQueries;
	}

	public void setPoolQueries(Set<PoolQuery> poolQueries) {
		this.poolQueries = poolQueries;
	}

}