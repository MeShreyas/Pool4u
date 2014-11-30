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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Company entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "company", catalog = "carpool")
public class Company implements java.io.Serializable {

	// Fields

	private Integer companyId;
	private Area area;
	private IndustrySector industrySector;
	private String companyName;
	private String phone;
	private String email;
	private Set<UserData> userDatas = new HashSet<UserData>(0);
	private Set<UserAllowedCompanies> userAllowedCompanieses = new HashSet<UserAllowedCompanies>(
			0);

	// Constructors

	/** default constructor */
	public Company() {
	}

	/** full constructor */
	public Company(Area area, IndustrySector industrySector,
			String companyName, String phone, String email,
			Set<UserData> userDatas,
			Set<UserAllowedCompanies> userAllowedCompanieses) {
		this.area = area;
		this.industrySector = industrySector;
		this.companyName = companyName;
		this.phone = phone;
		this.email = email;
		this.userDatas = userDatas;
		this.userAllowedCompanieses = userAllowedCompanieses;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "company_id", unique = true, nullable = false)
	public Integer getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "area_id")
	public Area getArea() {
		return this.area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sector_id")
	public IndustrySector getIndustrySector() {
		return this.industrySector;
	}

	public void setIndustrySector(IndustrySector industrySector) {
		this.industrySector = industrySector;
	}

	@Column(name = "company_name", length = 45)
	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")
	public Set<UserData> getUserDatas() {
		return this.userDatas;
	}

	public void setUserDatas(Set<UserData> userDatas) {
		this.userDatas = userDatas;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")
	public Set<UserAllowedCompanies> getUserAllowedCompanieses() {
		return this.userAllowedCompanieses;
	}

	public void setUserAllowedCompanieses(
			Set<UserAllowedCompanies> userAllowedCompanieses) {
		this.userAllowedCompanieses = userAllowedCompanieses;
	}

}