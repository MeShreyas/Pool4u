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
import javax.persistence.UniqueConstraint;

/**
 * City entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "city", catalog = "carpool", uniqueConstraints = @UniqueConstraint(columnNames = {
		"city_name", "city_id" }))
public class City implements java.io.Serializable {

	// Fields

	private Integer cityId;
	private State state;
	private String cityName;
	private Set<Vendor> vendors = new HashSet<Vendor>(0);
	private Set<Area> areas = new HashSet<Area>(0);

	// Constructors

	/** default constructor */
	public City() {
	}

	/** minimal constructor */
	public City(State state, String cityName) {
		this.state = state;
		this.cityName = cityName;
	}

	/** full constructor */
	public City(State state, String cityName, Set<Vendor> vendors,
			Set<Area> areas) {
		this.state = state;
		this.cityName = cityName;
		this.vendors = vendors;
		this.areas = areas;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "city_id", unique = true, nullable = false)
	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id", nullable = false)
	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@Column(name = "city_name", nullable = false, length = 45)
	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "city")
	public Set<Vendor> getVendors() {
		return this.vendors;
	}

	public void setVendors(Set<Vendor> vendors) {
		this.vendors = vendors;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "city")
	public Set<Area> getAreas() {
		return this.areas;
	}

	public void setAreas(Set<Area> areas) {
		this.areas = areas;
	}

}