/**
 * 
 */
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

/**
 * @author Deepak
 *
 */
@Entity
@Table(name = "routes", catalog = "carpool")
@SqlResultSetMapping(name="getRouteCount", entities =
    @EntityResult(entityClass=com.carpool.data.RouteCount.class, fields= {
       
        @FieldResult(name="routeCount", column="route_count"),
        @FieldResult(name="routeId", column="route_Id")
    }
    )
)
public class RouteCount {
	private Integer routeId;
	private Integer routeCount;
	/**
	 * @return the routeId
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "route_id", unique = true, nullable = false)
	public Integer getRouteId() {
		return routeId;
	}
	/**
	 * @param routeId the routeId to set
	 */
	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}
	
	/**
	 * @return the routeCount
	 */
	@Column(name = "route_count", nullable = true)
	public Integer getRouteCount() {
		return routeCount;
	}
	/**
	 * @param routeCount the routeCount to set
	 */
	public void setRouteCount(Integer routeCount) {
		this.routeCount = routeCount;
	}
	
	
	
	
}
