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

/**
 * Routes entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "routes", catalog = "carpool")
public class Routes implements java.io.Serializable {

	// Fields

	private Integer routeId;
	private Integer routeCount;
	private Set<Pool> pools = new HashSet<Pool>(0);

	// Constructors

	/** default constructor */
	public Routes() {
	}

	/** full constructor */
	public Routes(Integer routeCount, Set<Pool> pools) {
		this.routeCount = routeCount;
		this.pools = pools;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "route_id", unique = true, nullable = false)
	public Integer getRouteId() {
		return this.routeId;
	}

	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}

	@Column(name = "route_count")
	public Integer getRouteCount() {
		return this.routeCount;
	}

	public void setRouteCount(Integer routeCount) {
		this.routeCount = routeCount;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "routes")
	public Set<Pool> getPools() {
		return this.pools;
	}

	public void setPools(Set<Pool> pools) {
		this.pools = pools;
	}

}