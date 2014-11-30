package com.carpool.entities;

import java.sql.Timestamp;
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
 * Pool entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "pool", catalog = "carpool")

public class Pool  implements java.io.Serializable {


    // Fields    

     private Integer poolId;
	private UserData userData = new UserData();
     private Routes routes;
     private Integer seats;
     private String autoApprove;
     private String contribution;
     private Timestamp recCreDate;
     private String description;
     private Timestamp startTime;
     private Timestamp returnTime;
     private String poolTitle;
     private String smoking;
     private String womenOnly;
     private Set<PoolQuery> poolQueries = new HashSet<PoolQuery>(0);
     private Set<PoolUserMap> poolUserMaps = new HashSet<PoolUserMap>(0);


    // Constructors

    /** default constructor */
    public Pool() {
    }

	/** minimal constructor */
    public Pool(UserData userData, Routes routes, Integer seats, Timestamp recCreDate, Timestamp startTime, String poolTitle) {
        this.userData = userData;
        this.routes = routes;
        this.seats = seats;
        this.recCreDate = recCreDate;
        this.startTime = startTime;
        this.poolTitle = poolTitle;
    }
    
    /** full constructor */
    public Pool(UserData userData, Routes routes, Integer seats, String autoApprove, String contribution, Timestamp recCreDate, String description, Timestamp startTime, Timestamp returnTime, String poolTitle, String smoking, String womenOnly, Set<PoolQuery> poolQueries, Set<PoolUserMap> poolUserMaps) {
        this.userData = userData;
        this.routes = routes;
        this.seats = seats;
        this.autoApprove = autoApprove;
        this.contribution = contribution;
        this.recCreDate = recCreDate;
        this.description = description;
        this.startTime = startTime;
        this.returnTime = returnTime;
        this.poolTitle = poolTitle;
        this.smoking = smoking;
        this.womenOnly = womenOnly;
        this.poolQueries = poolQueries;
        this.poolUserMaps = poolUserMaps;
    }

   
    // Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
    
    @Column(name="pool_id", unique=true, nullable=false)

    public Integer getPoolId() {
        return this.poolId;
    }
    
    public void setPoolId(Integer poolId) {
        this.poolId = poolId;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="user_id", nullable=false)

    public UserData getUserData() {
        return this.userData;
    }
    
    public void setUserData(UserData userData) {
        this.userData = userData;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="route_id", nullable=false)

    public Routes getRoutes() {
        return this.routes;
    }
    
    public void setRoutes(Routes routes) {
        this.routes = routes;
    }
    
    @Column(name="seats", nullable=false)

    public Integer getSeats() {
        return this.seats;
    }
    
    public void setSeats(Integer seats) {
        this.seats = seats;
    }
    
    @Column(name="auto_approve", length=45)

    public String getAutoApprove() {
        return this.autoApprove;
    }
    
    public void setAutoApprove(String autoApprove) {
        this.autoApprove = autoApprove;
    }
    
    @Column(name="contribution", length=45)

    public String getContribution() {
        return this.contribution;
    }
    
    public void setContribution(String contribution) {
        this.contribution = contribution;
    }
    
    @Column(name="rec_cre_date", nullable=false, length=19)

    public Timestamp getRecCreDate() {
        return this.recCreDate;
    }
    
    public void setRecCreDate(Timestamp recCreDate) {
        this.recCreDate = recCreDate;
    }
    
    @Column(name="description", length=512)

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Column(name="start_time", nullable=false, length=19)

    public Timestamp getStartTime() {
        return this.startTime;
    }
    
    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }
    
	@Column(name = "return_time", nullable = true, length = 19)

    public Timestamp getReturnTime() {
        return this.returnTime;
    }
    
    public void setReturnTime(Timestamp returnTime) {
        this.returnTime = returnTime;
    }
    
    @Column(name="pool_title", nullable=false, length=250)

    public String getPoolTitle() {
        return this.poolTitle;
    }
    
    public void setPoolTitle(String poolTitle) {
        this.poolTitle = poolTitle;
    }
    
    @Column(name="smoking", length=1)

    public String getSmoking() {
        return this.smoking;
    }
    
    public void setSmoking(String smoking) {
        this.smoking = smoking;
    }
    
    @Column(name="women_only", length=1)

    public String getWomenOnly() {
        return this.womenOnly;
    }
    
    public void setWomenOnly(String womenOnly) {
        this.womenOnly = womenOnly;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="pool")

    public Set<PoolQuery> getPoolQueries() {
        return this.poolQueries;
    }
    
    public void setPoolQueries(Set<PoolQuery> poolQueries) {
        this.poolQueries = poolQueries;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="pool")

    public Set<PoolUserMap> getPoolUserMaps() {
        return this.poolUserMaps;
    }
    
    public void setPoolUserMaps(Set<PoolUserMap> poolUserMaps) {
        this.poolUserMaps = poolUserMaps;
    }
   








}