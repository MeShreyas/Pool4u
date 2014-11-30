/**
 * 
 */
package com.carpool.services;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.carpool.data.GeoRoute;
import com.carpool.data.PoolDetails;
import com.carpool.entities.IPoolDAO;
import com.carpool.entities.Pool;
import com.carpool.entities.PoolDAO;
import com.carpool.util.GeoUtil;

/**
 * @author Deepak
 *
 */
public class SearchRideService extends BaseService {
	private static BaseService service = null;
	IPoolDAO poolDao = new PoolDAO();
	public static synchronized BaseService getService(){
		if(service==null){
			service = new SearchRideService();
		}
		return service;
	}
	private SearchRideService(){
		
	}
	public List searchARide(Pool pool, GeoRoute geoRoute){
		
		System.out.println("from : "+geoRoute.getStartPoint());
		List<Integer> routeIds = GeoUtil.getRoutes(geoRoute);
		HashMap<String, String> params = new HashMap<String, String>();
		HashMap<String, String> startTimeRange = new HashMap<String, String>();
		
		String autoApprove = pool.getAutoApprove();
		if(autoApprove!=null){
			params.put("auto_approve", autoApprove);
		}
		
		params.put("smoking", pool.getSmoking());
		params.put("women_only", pool.getWomenOnly());
		
		Timestamp startTime = pool.getStartTime();
		
		Timestamp startTime1 = (Timestamp) startTime.clone();
		startTime1.setMinutes(startTime1.getMinutes()-30);
		Timestamp startTime2 = (Timestamp) startTime.clone();
		startTime2.setMinutes(startTime2.getMinutes()+30);
		startTimeRange.put("startTime1", startTime1.toString());
		startTimeRange.put("startTime2", startTime2.toString());
		
		List<PoolDetails> pools =  poolDao.searchPools(routeIds, params, startTimeRange, true);
		
		
		return pools;
	}
}

