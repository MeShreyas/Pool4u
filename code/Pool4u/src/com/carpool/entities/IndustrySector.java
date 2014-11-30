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
 * IndustrySector entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "industry_sector", catalog = "carpool", uniqueConstraints = @UniqueConstraint(columnNames = "sector_name"))
public class IndustrySector implements java.io.Serializable {

	// Fields

	private Integer sectorId;
	private String sectorName;
	private Set<Company> companies = new HashSet<Company>(0);

	// Constructors

	/** default constructor */
	public IndustrySector() {
	}

	/** full constructor */
	public IndustrySector(String sectorName, Set<Company> companies) {
		this.sectorName = sectorName;
		this.companies = companies;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "sector_id", unique = true, nullable = false)
	public Integer getSectorId() {
		return this.sectorId;
	}

	public void setSectorId(Integer sectorId) {
		this.sectorId = sectorId;
	}

	@Column(name = "sector_name", unique = true, length = 45)
	public String getSectorName() {
		return this.sectorName;
	}

	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "industrySector")
	public Set<Company> getCompanies() {
		return this.companies;
	}

	public void setCompanies(Set<Company> companies) {
		this.companies = companies;
	}

}