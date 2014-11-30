/**
 * 
 */
package com.carpool.data;

/**
 * @author Deepak
 *
 */
public class GeoRoute {
	private LatLng startPoint;
	private LatLng endPoint;
	private Integer routeId;
	
	
	/**
	 * @return the routeId
	 */
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
	 * @return the startPoint
	 */
	public LatLng getStartPoint() {
		return startPoint;
	}
	/**
	 * @param startPoint the startPoint to set
	 */
	public void setStartPoint(LatLng startPoint) {
		this.startPoint = startPoint;
	}
	/**
	 * @return the endPoint
	 */
	public LatLng getEndPoint() {
		return endPoint;
	}
	/**
	 * @param endPoint the endPoint to set
	 */
	public void setEndPoint(LatLng endPoint) {
		this.endPoint = endPoint;
	}
	

}
