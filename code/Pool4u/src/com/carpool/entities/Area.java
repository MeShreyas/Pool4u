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
 * Area entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "area", catalog = "carpool")
public class Area implements java.io.Serializable {

	// Fields

	private Integer areaId;
	private City city;
	private String areaName;
	private Set<Vendor> vendors = new HashSet<Vendor>(0);
	private Set<Company> companies = new HashSet<Company>(0);

	// Constructors

	/** default constructor */
	public Area() {
	}

	/** minimal constructor */
	public Area(City city, String areaName) {
		this.city = city;
		this.areaName = areaName;
	}

	/** full constructor */
	public Area(City city, String areaName, Set<Vendor> vendors,
			Set<Company> companies) {
		this.city = city;
		this.areaName = areaName;
		this.vendors = vendors;
		this.companies = companies;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "area_id", unique = true, nullable = false)
	public Integer getAreaId() {
		return this.areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id", nullable = false)
	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Column(name = "area_name", nullable = false, length = 45)
	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "area")
	public Set<Vendor> getVendors() {
		return this.vendors;
	}

	public void setVendors(Set<Vendor> vendors) {
		this.vendors = vendors;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "area")
	public Set<Company> getCompanies() {
		return this.companies;
	}

	public void setCompanies(Set<Company> companies) {
		this.companies = companies;
	}

}