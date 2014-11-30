package com.carpool.data;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

@Entity
@Table(name = "pool", catalog = "carpool")
@SqlResultSetMapping(name="getPoolsNew", entities =
    @EntityResult(entityClass=com.carpool.data.PoolDetailsNew.class, fields= {
        @FieldResult(name="poolId", column="pool_id"),
        @FieldResult(name="routeId", column="route_id"),
        @FieldResult(name="startTime", column="start_time"),
        @FieldResult(name="returnTime", column="return_time"),
        @FieldResult(name="seats", column="seats"),
        @FieldResult(name="autoApprove", column="auto_approve"),
        @FieldResult(name="smoking", column="smoking"),
        @FieldResult(name="womenOnly", column="women_only"),
        @FieldResult(name="poolTitle", column="pool_title"),
        @FieldResult(name="contribution", column="contribution"),
        @FieldResult(name="firstName", column="first_name"),
        @FieldResult(name="lastName", column="last_name"),
        @FieldResult(name="companyName", column="company_name"),
        @FieldResult(name="postingUserCompanies", column="posting_user_companies"),
        @FieldResult(name="searchingUserCompanies", column="searching_user_companies"),
        @FieldResult(name="companyId", column="company_id")
    }
    )
)
public class PoolDetailsNew {
	private String poolId;
	private String routeId;
	private Timestamp startTime;
	private Timestamp returnTime;
	private Integer seats;
	private String autoApprove;
	private String smoking;
	private String womenOnly;
	private String poolTitle;
	private String contribution;
	private String firstName;
	private String lastName;
	private String companyName;
	private String postingUserCompanies;
	private String searchingUserCompanies;
	private String companyId;
	
	/**
	 * @return the poolTitle
	 */
	@Column(name = "pool_title", nullable = false )
	public String getPoolTitle() {
		return poolTitle;
	}
	/**
	 * @param poolTitle the poolTitle to set
	 */
	public void setPoolTitle(String poolTitle) {
		this.poolTitle = poolTitle;
	}
	/**
	 * @return the poolId
	 */
	@Id
	@Column(name = "pool_id", unique = true, nullable = false )
	public String getPoolId() {
		return poolId;
	}
	/**
	 * @param poolId the poolId to set
	 */
	public void setPoolId(String poolId) {
		this.poolId = poolId;
	}
	/**
	 * @return the routeId
	 */
	@Column(name = "route_id", nullable = false )
	public String getRouteId() {
		return routeId;
	}
	/**
	 * @param routeId the routeId to set
	 */
	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}
	/**
	 * @return the startTime
	 */
	@Column(name = "start_time", nullable = false )
	public Timestamp getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return the returnTime
	 */
	@Column(name = "return_time", nullable = false )
	public Timestamp getReturnTime() {
		return returnTime;
	}
	/**
	 * @param returnTime the returnTime to set
	 */
	public void setReturnTime(Timestamp returnTime) {
		this.returnTime = returnTime;
	}
	/**
	 * @return the seats
	 */
	@Column(name = "seats", nullable = false )
	public Integer getSeats() {
		return seats;
	}
	/**
	 * @param seats the seats to set
	 */
	public void setSeats(Integer seats) {
		this.seats = seats;
	}
	/**
	 * @return the autoApprove
	 */
	@Column(name = "auto_approve", nullable = false )
	public String getAutoApprove() {
		return autoApprove;
	}
	/**
	 * @param autoApprove the autoApprove to set
	 */
	public void setAutoApprove(String autoApprove) {
		this.autoApprove = autoApprove;
	}
	/**
	 * @return the smoking
	 */
	
	public String getSmoking() {
		return smoking;
	}
	/**
	 * @param smoking the smoking to set
	 */
	public void setSmoking(String smoking) {
		this.smoking = smoking;
	}
	/**
	 * @return the womenOnly
	 */
	public String getWomenOnly() {
		return womenOnly;
	}
	/**
	 * @param womenOnly the womenOnly to set
	 */
	public void setWomenOnly(String womenOnly) {
		this.womenOnly = womenOnly;
	}
	/**
	 * @return the contribution
	 */
	@Column(name = "contribution", nullable = true )
	public String getContribution() {
		return contribution;
	}
	/**
	 * @param contribution the contribution to set
	 */
	public void setContribution(String contribution) {
		this.contribution = contribution;
	}
	
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * @return the postingUserCompanies
	 */
	public String getPostingUserCompanies() {
		return postingUserCompanies;
	}
	/**
	 * @param postingUserCompanies the postingUserCompanies to set
	 */
	public void setPostingUserCompanies(String postingUserCompanies) {
		this.postingUserCompanies = postingUserCompanies;
	}
	/**
	 * @return the searchingUserCompanies
	 */
	public String getSearchingUserCompanies() {
		return searchingUserCompanies;
	}
	/**
	 * @param searchingUserCompanies the searchingUserCompanies to set
	 */
	public void setSearchingUserCompanies(String searchingUserCompanies) {
		this.searchingUserCompanies = searchingUserCompanies;
	}
	/**
	 * @return the companyId
	 */
	public String getCompanyId() {
		return companyId;
	}
	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	
}
