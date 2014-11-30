/**
 * 
 */
package com.carpool.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.carpool.dao.helper.EntityManagerHelper;
import com.carpool.data.GeoRoute;
import com.carpool.data.UserSessionData;
import com.carpool.entities.IPoolDAO;
import com.carpool.entities.IRoutesDAO;
import com.carpool.entities.IUserCredentialsDAO;
import com.carpool.entities.IUserDataDAO;
import com.carpool.entities.Pool;
import com.carpool.entities.PoolDAO;
import com.carpool.entities.Routes;
import com.carpool.entities.RoutesDAO;
import com.carpool.entities.UserCredentials;
import com.carpool.entities.UserCredentialsDAO;
import com.carpool.entities.UserDataDAO;
import com.carpool.exceptions.PoolFatalException;
import com.carpool.exceptions.PoolNonFatalException;
import com.carpool.util.CarPoolConstants;
import com.carpool.util.CarpoolErrorCodes;
import com.carpool.util.GeoUtil;

/**
 * @author Deepak
 *
 */
public class OfferRideService extends BaseService {
	private static BaseService service = null;
	
	IRoutesDAO routesDao = new RoutesDAO();
	IPoolDAO poolDao = new PoolDAO();
	
	IUserDataDAO userDataDAO = new UserDataDAO();
	IUserCredentialsDAO userCredDao = new UserCredentialsDAO();
	public static synchronized BaseService getService(){
		if(service==null){
			service = new OfferRideService();
		}
		return service;
	}
	/**
	 * To fetch the data from different tables to show on UI 
	 */
	private OfferRideService() {
		
		
	}

	
	/**
	 * This method fetches the filters available to be applied to a pool.
	 * If the logged in user isn't Female, Women only option won't be available in filters
	 * @param userSessionData
	 * @return
	 */
	public Map fetchInitData(UserSessionData userSessionData) {
		/*List filterList = filtersDao.findAll();
		Map filters = new HashMap();
		
		if(filterList!=null || filterList.size()>0){
			PoolFilters filter = null;
			
			if(!CarPoolConstants.FEMALE.equals(userSessionData.getGender())){
				
				for(int index=0; index<filterList.size(); index++){
					filter = (PoolFilters)filterList.get(index);
					// id can be used here, but if seed data changes, id will change and 
					//it won't work so used description to compare
					if(filter.getFilterDesc().equals("Women Only")){
						filterList.remove(index);
						index--;
					}
				}
			}
			else if(userSessionData.getGender()==null||userSessionData.getGender().trim().length()==0 ){
				filterList.clear();
			}
			
			
			filters.put("PoolFilters", filterList);
			return filters;
		}*/
		List filterList = new ArrayList();
		Map filters = new HashMap();
		if(CarPoolConstants.FEMALE.equals(userSessionData.getGender())){
			filterList.add("womenOnly");
			
		}
		filterList.add("smoking");
		filterList.add("autoApprove");
		filters.put("PoolFilters", filterList);
		return filters;
	}

	public Pool OfferARide(Pool pool, GeoRoute geoRoute) throws PoolNonFatalException{
		
		System.out.println("in service");
		// check if the user publishing a pool has enough credit available
		int userId = pool.getUserData().getUserId();
		UserCredentials user = userCredDao.findById(userId);
		Double credits = user.getUserCredits();
		// if no enough credits are available throw error
		if(credits<pool.getSeats()){
			new PoolNonFatalException(CarpoolErrorCodes.NO_ENOUGH_CREDITS);
		}
		//if yes, 
		// check if route is available if so use it's id else insert a new record
		
		try{
		EntityManagerHelper.beginTransaction();
		
		
		
		// hit mongo db to check if route exists
		List<Integer> routesList = GeoUtil.getRoutes(geoRoute);
		Routes route = null;
		//System.out.println("route from mongo : "+routesList.get(0));
		if(routesList!=null && routesList.size()>0){
			// get the route from Routes table using the id received from mongoDB
			route = routesDao.findById(routesList.get(0));
			// increment the routeCount for usage data
			System.out.println("route selected : "+route.getRouteId());
			route.setRouteCount(route.getRouteCount()+1);
			
			pool.setRoutes(route);
			routesDao.update(route);
		}
		else{
			 /*create route record and use the new route id*/
			route = new Routes();
			route.setRouteCount(1);
			routesDao.save(route);
			geoRoute.setRouteId(route.getRouteId());
			GeoUtil.saveRoute(geoRoute);
			pool.setRoutes(route);
		}
		
		// insert data into pool table
		pool.setRecCreDate(new Timestamp(System.currentTimeMillis()));
		System.out.println("pool id b4 save : "+pool.getPoolId());
		//poolDao = new PoolDAO();
		poolDao.save(pool);
		System.out.println("pool id : "+pool.getPoolId());
		
		EntityManagerHelper.commit();
		
		}
		catch (Exception e) {
			e.printStackTrace();
			if(EntityManagerHelper.isACtive())
				EntityManagerHelper.rollback();
			throw new PoolFatalException(e);
		}
		pool = poolDao.findById(pool.getPoolId());
		return pool;
	}
}
