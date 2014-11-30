package com.carpool.data;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "user_data", catalog = "carpool")
@SqlResultSetMapping(name="getItem", entities =
    @EntityResult(entityClass=com.carpool.data.UserDataTable.class, fields= {
        @FieldResult(name="userId", column="userId"),
        @FieldResult(name="firstName", column="first"),
        @FieldResult(name="fullName", column="fullName")
      
    }
    )
)
public class UserDataTable {

	
	private Integer userId;
	private String firstName;
	
	private String fullName;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Column(name = "first_name", nullable = false, length = 45)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the fullName
	 */
	
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
}
