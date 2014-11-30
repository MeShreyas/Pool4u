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

@Entity
@Table(name = "routes", catalog = "carpool")
@SqlResultSetMapping(name="getRoute", entities =
    @EntityResult(entityClass=com.carpool.data.RouteContri.class, fields= {
        @FieldResult(name="contri", column="contri"),
        @FieldResult(name="start", column="startPoint"),
        @FieldResult(name="end", column="endPoint"),
        @FieldResult(name="routeId", column="route")
    }
    )
)
public class RouteContri {

	private Integer routeId;
	private String contri;
	private String start;
	private String end;
	
	
	/**
	 * @return the routeid
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "route_id", unique = true, nullable = false)
	public Integer getRouteId() {
		return routeId;
	}
	/**
	 * @param routeid the routeid to set
	 */
	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}
	/**
	 * @return the contri
	 */
	
	public String getContri() {
		return contri;
	}
	/**
	 * @param contri the contri to set
	 */
	public void setContri(String contri) {
		this.contri = contri;
	}
	/**
	 * @return the start
	 */
	@Column(name = "start", nullable = false)
	public String getStart() {
		return start;
	}
	/**
	 * @param start the start to set
	 */
	public void setStart(String start) {
		this.start = start;
	}
	/**
	 * @return the end
	 */
	@Column(name = "end", nullable = false)
	public String getEnd() {
		return end;
	}
	/**
	 * @param end the end to set
	 */
	public void setEnd(String end) {
		this.end = end;
	}
	
}
