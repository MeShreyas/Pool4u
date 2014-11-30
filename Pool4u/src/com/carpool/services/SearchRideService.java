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
import com.carpool.data.PoolDetailsNew;
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
public List searchARideNew(int userId,int companyId, Pool pool, GeoRoute geoRoute){
		
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
		
		List<PoolDetailsNew> pools =  poolDao.searchNewPools(userId, routeIds, params, startTimeRange, true);
		
		filterAllowedPools(pools,companyId);
		
		
		return pools;
	}
	/**
	 * This method filters the pools fetched according to the user preferences for the companies
	 * @param pools
	 * @param companyId
	 */
	private void filterAllowedPools(List<PoolDetailsNew> pools, int companyId) {
		PoolDetailsNew pool = null;
		System.out.println("pool filter for company preferences");
		for(int index=0;index<pools.size();index++){
			pool = pools.get(index);
			// if publishers company is allowed by the seeker and seekers company is allowed by the publisher, keep the pool in list else remove it
			if(!(pool.getSearchingUserCompanies().contains(pool.getCompanyId()) && pool.getPostingUserCompanies().contains(companyId+""))){
				System.out.println("removing");
				pools.remove(index);
				index--;
			}
			
		}
		
		
	}
}

