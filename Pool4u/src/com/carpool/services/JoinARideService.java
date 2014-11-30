/**
 * 
 */
package com.carpool.services;

import com.carpool.dao.helper.EntityManagerHelper;
import com.carpool.entities.IPoolDAO;
import com.carpool.entities.IPoolUserMapDAO;
import com.carpool.entities.IUserDataDAO;
import com.carpool.entities.Pool;
import com.carpool.entities.PoolDAO;
import com.carpool.entities.PoolUserMap;
import com.carpool.entities.PoolUserMapDAO;
import com.carpool.entities.UserData;
import com.carpool.entities.UserDataDAO;
import com.carpool.exceptions.PoolNonFatalException;
import com.carpool.util.CarPoolConstants;
import com.carpool.util.CarpoolErrorCodes;

/**
 * @author Deepak
 *
 */
public class JoinARideService extends BaseService {

	
	private static BaseService service = null;
	private IPoolDAO poolDao = new PoolDAO();
	private IPoolUserMapDAO poolUserMapDao = new PoolUserMapDAO();
	private IUserDataDAO userDataDao = new UserDataDAO();
	public static synchronized BaseService getService(){
		if(service==null){
			service = new JoinARideService();
		}
		return service;
	}
	/**
	 * 
	 */
	private JoinARideService() {
		// TODO Auto-generated constructor stub
	}
	
	
	public synchronized void joinRide(Pool pool,int user_id) throws PoolNonFatalException{
		
		EntityManagerHelper.beginTransaction();
		pool = poolDao.findById(pool.getPoolId());
		
		if(pool.getSeats()==0){
			throw new PoolNonFatalException(CarpoolErrorCodes.NO_SEATS_AVAILABLE);
		}
		PoolUserMap poolUserMap = new PoolUserMap();
		poolUserMap.setPool(pool);
		poolUserMap.setApprovalFlag(pool.getAutoApprove());
		UserData userData = userDataDao.findById(user_id);
		poolUserMap.setUserData(userData);
		poolUserMapDao.save(poolUserMap);
		//if auto approve is Y, reduce the number of available seats by 1
		if(CarPoolConstants.YES.equals(pool.getAutoApprove())){
			pool.setSeats(pool.getSeats()-1);
			poolDao.update(pool);
		}
		
		EntityManagerHelper.commit();
		
	}
	public void approveJoin(PoolUserMap poolUserMap) throws PoolNonFatalException{
		EntityManagerHelper.beginTransaction();
		Pool pool = poolDao.findById(poolUserMap.getId().getPoolId());
		if(pool.getSeats()==0){
			throw new PoolNonFatalException(CarpoolErrorCodes.NO_SEATS_AVAILABLE);
		}
		poolUserMap = poolUserMapDao.findById(poolUserMap.getId());
		poolUserMap.setApprovalFlag(CarPoolConstants.YES);
		poolUserMapDao.update(poolUserMap);
		pool.setSeats(pool.getSeats()-1);
		poolDao.update(pool);
		EntityManagerHelper.commit();
	}
	
}
