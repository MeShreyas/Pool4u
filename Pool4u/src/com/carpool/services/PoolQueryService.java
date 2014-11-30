/**
 * 
 */
package com.carpool.services;

import com.carpool.dao.helper.EntityManagerHelper;
import com.carpool.entities.IPoolQueryDAO;
import com.carpool.entities.PoolQuery;
import com.carpool.entities.PoolQueryDAO;
import com.carpool.exceptions.PoolNonFatalException;

/**
 * @author Deepak
 *
 */
public class PoolQueryService extends BaseService {

	private IPoolQueryDAO poolQueryDao = new PoolQueryDAO();
	private static BaseService service = null;
	
	public static synchronized BaseService getService(){
		if(service==null){
			service = new PoolQueryService();
		}
		return service;
	}
	private PoolQueryService() {
		
	}
	
	public void askPoolQuery(PoolQuery poolQuery) throws PoolNonFatalException{
		
		EntityManagerHelper.beginTransaction();
		poolQueryDao.save(poolQuery);
		EntityManagerHelper.commit();
		
	}
	
	public void respondPoolQuery(PoolQuery poolQuery) throws PoolNonFatalException{
		
		EntityManagerHelper.beginTransaction();
		poolQueryDao.update(poolQuery);
		// should we send email?
		EntityManagerHelper.commit();
		
	}
	public void rejectPoolQuery(PoolQuery poolQuery){
		
		EntityManagerHelper.beginTransaction();
		if(poolQuery.getResponse()==null || poolQuery.getResponse().trim().length()==0){
			poolQuery.setResponse("Rejected!");
		}
		poolQueryDao.update(poolQuery);
		//should we send email?
		EntityManagerHelper.commit();
		
	}
}
