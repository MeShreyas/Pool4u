package com.carpool.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Suggestions entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "suggestions", catalog = "carpool")
public class Suggestions implements java.io.Serializable {

	// Fields

	private Integer id;
	private String suggestion;

	// Constructors

	/** default constructor */
	public Suggestions() {
	}

	/** full constructor */
	public Suggestions(String suggestion) {
		this.suggestion = suggestion;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "suggestion", nullable = false)
	public String getSuggestion() {
		return this.suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

}