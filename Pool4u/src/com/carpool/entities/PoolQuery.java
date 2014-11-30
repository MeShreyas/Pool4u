package com.carpool.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * PoolQueries entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="pool_query"
    ,catalog="carpool"
)

public class PoolQuery  implements java.io.Serializable {


    // Fields    

     private Integer messageId;
     private Pool pool = new Pool();
     private UserData userData = new UserData();
     private String query;
     private String response;


    // Constructors

    /** default constructor */
    public PoolQuery() {
    }

	/** minimal constructor */
    public PoolQuery(Pool pool, UserData userData) {
        this.pool = pool;
        this.userData = userData;
    }
    
    /** full constructor */
    public PoolQuery(Pool pool, UserData userData, String query, String response) {
        this.pool = pool;
        this.userData = userData;
        this.query = query;
        this.response = response;
    }

   
    // Property accessors
    @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="message_id", unique=true, nullable=false)

    public Integer getMessageId() {
        return this.messageId;
    }
    
    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="pool_id", nullable=false)

    public Pool getPool() {
        return this.pool;
    }
    
    public void setPool(Pool pool) {
        this.pool = pool;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="user_id", nullable=false)

    public UserData getUserData() {
        return this.userData;
    }
    
    public void setUserData(UserData userData) {
        this.userData = userData;
    }
    
    @Column(name="query", length=512)

    public String getQuery() {
        return this.query;
    }
    
    public void setQuery(String query) {
        this.query = query;
    }
    
    @Column(name="response", length=512)

    public String getResponse() {
        return this.response;
    }
    
    public void setResponse(String response) {
        this.response = response;
    }
   








}