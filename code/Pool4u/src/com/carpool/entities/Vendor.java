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
 * Vendor entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "vendor", catalog = "carpool")
public class Vendor implements java.io.Serializable {

	// Fields

	private Integer vendorId;
	private Area area;
	private State state;
	private City city;
	private String vendorName;
	private String phone;
	private String email;
	private Set<UserData> userDatas = new HashSet<UserData>(0);

	// Constructors

	/** default constructor */
	public Vendor() {
	}

	/** minimal constructor */
	public Vendor(String vendorName) {
		this.vendorName = vendorName;
	}

	/** full constructor */
	public Vendor(Area area, State state, City city, String vendorName,
			String phone, String email, Set<UserData> userDatas) {
		this.area = area;
		this.state = state;
		this.city = city;
		this.vendorName = vendorName;
		this.phone = phone;
		this.email = email;
		this.userDatas = userDatas;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "vendor_id", unique = true, nullable = false)
	public Integer getVendorId() {
		return this.vendorId;
	}

	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
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
	@JoinColumn(name = "state_id")
	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id")
	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Column(name = "vendor_name", nullable = false, length = 45)
	public String getVendorName() {
		return this.vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vendor")
	public Set<UserData> getUserDatas() {
		return this.userDatas;
	}

	public void setUserDatas(Set<UserData> userDatas) {
		this.userDatas = userDatas;
	}

}