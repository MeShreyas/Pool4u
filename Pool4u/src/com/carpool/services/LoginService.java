/**
 * 
 */
package com.carpool.services;


import java.sql.Timestamp;
import java.util.List;

import com.carpool.dao.helper.EntityManagerHelper;
import com.carpool.entities.Device;
import com.carpool.entities.DeviceDAO;
import com.carpool.entities.IDeviceDAO;
import com.carpool.entities.IUserCredentialsDAO;
import com.carpool.entities.IUserDataDAO;
import com.carpool.entities.IUserLoginDAO;
import com.carpool.entities.UserCredentials;
import com.carpool.entities.UserCredentialsDAO;
import com.carpool.entities.UserData;
import com.carpool.entities.UserDataDAO;
import com.carpool.entities.UserLogin;
import com.carpool.entities.UserLoginDAO;
import com.carpool.exceptions.PoolNonFatalException;
import com.carpool.util.CarPoolConstants;
import com.carpool.util.CarPoolUtil;
import com.carpool.util.CarpoolErrorCodes;

/**
 * @author Deepak
 *
 */
public class LoginService extends BaseService {

	private static BaseService service = null;
	private IUserCredentialsDAO userCredDao = new UserCredentialsDAO();
	private IUserLoginDAO userLoginDao = new UserLoginDAO();
	private IDeviceDAO deviceDao = new DeviceDAO();
	public static synchronized BaseService getService(){
		if(service==null){
			service = new LoginService();
		}
		return service;
	}
	/**
	 * 
	 */
	private LoginService() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * This method checks if user is a registered user of the system.
	 * If it is, it checks if the password entered is correct.
	 * If password is correct, it checks if user is verified user, it returns success.
	 * 		else it sends the confirmation code to user.
	 * @param user
	 * @return
	 * @throws PoolNonFatalException
	 */
	public String doLogin(UserCredentials user,String deviceName) throws PoolNonFatalException{
		
		List userCredList = userCredDao.findByUserName(user.getUserName());
		// insert data in user_login table
		
		
		// get the device id 
		List deviceList = deviceDao.findByDeviceDesc(deviceName);
		Device device = null;
		EntityManagerHelper.beginTransaction();
		UserLogin userLogin = new UserLogin();
		try{
			if(deviceList==null || deviceList.size()==0){
				// insert the data in device table
				device = new Device();
				device.setDeviceDesc(deviceName);
				deviceDao.save(device);
				System.out.println("device id : "+device.getDeviceId());
			}
			else{
				device = deviceDao.findByDeviceDesc(deviceName).get(0);
			}
			
			userLogin.setDevice(device);
			userLogin.setDeviceId(device.getDeviceId());
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			userLogin.setLoginTs(ts);
			userLogin.setRecCreDate(ts);
			int errorCode =0;
			if(userCredList!=null && userCredList.size()>0){
				UserCredentials userFromDB = (UserCredentials)userCredList.get(0);
				userLogin.setUserId(userFromDB.getUserId());
			//	userLogin.setUserData(userFromDB.getUserData());
				if(userFromDB.getPassword().equals(user.getPassword())){
					
					if(CarPoolConstants.YES.equals(userFromDB.getIsActive())){
						if(CarPoolConstants.YES.equals(userFromDB.getIsAuthorized()))
						{
							user = userFromDB;
							userLogin.setLoginStatus(CarPoolConstants.SUCCESS);
							userLogin.setStatusComments(CarPoolConstants.SUCCESS);
							userLoginDao.update(userLogin);
							EntityManagerHelper.commit();
							return CarPoolConstants.SUCCESS;
						}
						else{
							/* 
							 * user has not completed the registration process by entering
							 * confirmation code, so send the confirmation code again. 
							 */
							
							IUserDataDAO userDataDao = new UserDataDAO();
							UserData userData = userDataDao.findById(userFromDB.getUserId());
							CarPoolUtil.sendConfirmationCode(userFromDB.getUserId(),userFromDB.getUserName(), userData.getCompany().getCompanyName());
							
							errorCode=CarpoolErrorCodes.UNCONFIRMED_USER;
						
						}
					}
					else{
						errorCode=CarpoolErrorCodes.INACTIVE_USER;
					}
				}
				else{
					errorCode=CarpoolErrorCodes.WRONG_PASSWORD;
				}
			}
			else{
				errorCode=CarpoolErrorCodes.WRONG_USER_OR_EMPLOYER;
				throw new PoolNonFatalException(errorCode);
			}
			userLogin.setLoginStatus(CarPoolConstants.FAILURE);
			userLogin.setStatusComments(CarPoolConstants.FAILURE);
			System.out.println("error : "+errorCode);
			userLoginDao.update(userLogin);
			EntityManagerHelper.commit();
			throw new PoolNonFatalException(errorCode);
		}
		finally{
			if(EntityManagerHelper.isACtive())
				EntityManagerHelper.rollback();
		}
	}
	
}
