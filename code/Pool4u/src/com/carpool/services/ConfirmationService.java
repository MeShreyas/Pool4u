/**
 * 
 */
package com.carpool.services;

import java.sql.Timestamp;
import java.util.List;

import com.carpool.dao.helper.EntityManagerHelper;
import com.carpool.data.ApplicationCache;
import com.carpool.entities.Device;
import com.carpool.entities.DeviceDAO;
import com.carpool.entities.IDeviceDAO;
import com.carpool.entities.IUserAllowedCompaniesDAO;
import com.carpool.entities.IUserCredentialsDAO;
import com.carpool.entities.IUserDataDAO;
import com.carpool.entities.IUserLoginDAO;
import com.carpool.entities.UserAllowedCompanies;
import com.carpool.entities.UserAllowedCompaniesDAO;
import com.carpool.entities.UserAllowedCompaniesId;
import com.carpool.entities.UserCredentials;
import com.carpool.entities.UserCredentialsDAO;
import com.carpool.entities.UserData;
import com.carpool.entities.UserDataDAO;
import com.carpool.entities.UserLogin;
import com.carpool.entities.UserLoginDAO;
import com.carpool.exceptions.PoolFatalException;
import com.carpool.exceptions.PoolNonFatalException;
import com.carpool.util.CarPoolConstants;
import com.carpool.util.CarpoolErrorCodes;
import com.carpool.util.CodeGenerator;

/**
 * @author Deepak
 *
 */
public class ConfirmationService extends BaseService {

	private static BaseService service = null;
	private IUserCredentialsDAO userDao = new UserCredentialsDAO();
	private IUserDataDAO userDataDao = new UserDataDAO();
	private IDeviceDAO deviceDao = new DeviceDAO();
	private IUserLoginDAO userLoginDao = new UserLoginDAO();
	private IUserAllowedCompaniesDAO userAllowedCompaniesDao = new UserAllowedCompaniesDAO(); 
	public static synchronized BaseService getService(){
		if(service==null){
			service = new ConfirmationService();
		}
		return service;
	}
	private ConfirmationService(){
		
	}
	public String confirmUser(UserCredentials userVo,String code, String deviceName) throws PoolNonFatalException{
		
		/* get the user from database */
		List userList = userDao.findByUserName(userVo.getUserName());
		/* if user is not present in DB, throw error */
		if(userList==null||userList.size()==0){
			throw new PoolNonFatalException(CarpoolErrorCodes.USER_NOT_EXISTS);
		}
		UserCredentials userCred = (UserCredentials)userList.get(0);
		if(userCred==null){
			System.out.println("user is null");
		}
		else{
			if(userCred.getUserData()==null){
				System.out.println("user data is null");
			}
			else if(userCred.getUserData().getCompany()==null){
				System.out.println("company is null");
			}
		}
		UserData userData = userDataDao.findById(userCred.getUserId());
		
		/*int userId = user.getUserId();
		IUserDataDAO userDatadao = new UserDataDAO();
		UserData userData = userDatadao.findById(userId);
		*/
		String generatedCode = CodeGenerator.generateCodeForUser(userCred.getUserName(), userData.getCompany().getCompanyName());
		System.out.println("service generatedCode : "+generatedCode);
		System.out.println("service code : "+code);
		if(!generatedCode.equals(code)){
			throw new PoolNonFatalException(CarpoolErrorCodes.INVALID_CONFIRMATION_CODE);
		}
		
		try{
			
				EntityManagerHelper.beginTransaction();
				Timestamp now = new Timestamp(System.currentTimeMillis());
				userCred.setIsAuthorized(CarPoolConstants.YES);
				if(userCred.getUserCreationDate().equals(userCred.getUserUpdateDate())){
					String accountExpiryLimit = (String)ApplicationCache.getConfigParams().get(CarPoolConstants.USER_ACCOUNT_EXPIRY_LIMIT);
					Timestamp ts = (Timestamp)now.clone();
					ts.setDate(ts.getDate()+Integer.parseInt(accountExpiryLimit));
					userCred.setAccountExpiryDate(ts);
				}
				userCred.setUserUpdateDate(now);
				userDao.update(userCred);
				//set user's company as allowed organisation
				UserAllowedCompanies userCompany = new UserAllowedCompanies();
				userCompany.setMapTs(new Timestamp(System.currentTimeMillis()));
				userCompany.setId(new UserAllowedCompaniesId(userCred.getUserId(),userCred.getUserData().getCompany().getCompanyId()));
				userAllowedCompaniesDao.save(userCompany);
				UserLogin userLogin = new UserLogin();
				List deviceList = deviceDao.findByDeviceDesc(deviceName);
				Device device = null;
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
				userLogin.setUserId(userCred.getUserId());
				userLogin.setLoginStatus(CarPoolConstants.SUCCESS);
				userLogin.setStatusComments(CarPoolConstants.SUCCESS);
				userLoginDao.update(userLogin);
				
				
			EntityManagerHelper.commit();
		}
		catch (Exception e) {
			// TODO: handle exception
			if(EntityManagerHelper.isACtive())
				EntityManagerHelper.rollback();
			throw new PoolFatalException(e);
		}
		return CarPoolConstants.SUCCESS;
	}
}
