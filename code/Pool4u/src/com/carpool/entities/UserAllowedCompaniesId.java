package com.carpool.entities;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * UserAllowedCompaniesId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class UserAllowedCompaniesId implements java.io.Serializable {

	// Fields

	private Integer userId;
	private Integer companyId;
	

	// Constructors

	/** default constructor */
	public UserAllowedCompaniesId() {
	}

	/** full constructor */
	public UserAllowedCompaniesId(Integer userId, Integer companyId) {
		this.userId = userId;
		this.companyId = companyId;
		
	}

	// Property accessors

	@Column(name = "user_id", nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "company_id", nullable = false)
	public Integer getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UserAllowedCompaniesId))
			return false;
		UserAllowedCompaniesId castOther = (UserAllowedCompaniesId) other;

		return ((this.getUserId() == castOther.getUserId()) || (this
				.getUserId() != null
				&& castOther.getUserId() != null && this.getUserId().equals(
				castOther.getUserId())))
				&& ((this.getCompanyId() == castOther.getCompanyId()) || (this
						.getCompanyId() != null
						&& castOther.getCompanyId() != null && this
						.getCompanyId().equals(castOther.getCompanyId())))
				;
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37 * result
				+ (getCompanyId() == null ? 0 : this.getCompanyId().hashCode());
		
		return result;
	}

}