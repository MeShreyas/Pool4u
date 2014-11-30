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
 * State entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "state", catalog = "carpool", uniqueConstraints = @UniqueConstraint(columnNames = "state_name"))
public class State implements java.io.Serializable {

	// Fields

	private Integer stateId;
	private String stateName;
	private Set<Vendor> vendors = new HashSet<Vendor>(0);
	private Set<City> cities = new HashSet<City>(0);

	// Constructors

	/** default constructor */
	public State() {
	}

	/** full constructor */
	public State(String stateName, Set<Vendor> vendors, Set<City> cities) {
		this.stateName = stateName;
		this.vendors = vendors;
		this.cities = cities;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "state_id", unique = true, nullable = false)
	public Integer getStateId() {
		return this.stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	@Column(name = "state_name", unique = true, length = 45)
	public String getStateName() {
		return this.stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "state")
	public Set<Vendor> getVendors() {
		return this.vendors;
	}

	public void setVendors(Set<Vendor> vendors) {
		this.vendors = vendors;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "state")
	public Set<City> getCities() {
		return this.cities;
	}

	public void setCities(Set<City> cities) {
		this.cities = cities;
	}

}