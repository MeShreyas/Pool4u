package com.carpool.entities;

import java.sql.Timestamp;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * UserAllowedCompanies entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_allowed_companies", catalog = "carpool", uniqueConstraints = @UniqueConstraint(columnNames = {
		"user_id", "company_id" }))
public class UserAllowedCompanies implements java.io.Serializable {

	// Fields

	private UserAllowedCompaniesId id;
	private UserData userData;
	private Company company;
	private Timestamp mapTs;
	// Constructors

	/** default constructor */
	public UserAllowedCompanies() {
	}

	/** full constructor */
	public UserAllowedCompanies(UserAllowedCompaniesId id, UserData userData,
			Company company,
			Timestamp mapTs) {
		this.id = id;
		this.userData = userData;
		this.company = company;
		this.mapTs = mapTs;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "userId", column = @Column(name = "user_id", nullable = false)),
			@AttributeOverride(name = "companyId", column = @Column(name = "company_id", nullable = false)) })
	public UserAllowedCompaniesId getId() {
		return this.id;
	}

	public void setId(UserAllowedCompaniesId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
	public UserData getUserData() {
		return this.userData;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id", nullable = false, insertable = false, updatable = false)
	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	@Column(name = "map_ts", nullable = false, length = 19)
	public Timestamp getMapTs() {
		return this.mapTs;
	}

	public void setMapTs(Timestamp mapTs) {
		this.mapTs = mapTs;
	}

}