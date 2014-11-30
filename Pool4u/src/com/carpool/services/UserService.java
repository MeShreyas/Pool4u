/**
 * 
 */
package com.carpool.services;

import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;


import com.carpool.dao.helper.EntityManagerHelper;
import com.carpool.data.ApplicationCache;




import com.carpool.entities.Company;
import com.carpool.entities.CompanyDAO;
import com.carpool.entities.ConfingParam;
import com.carpool.entities.ConfingParamDAO;
import com.carpool.entities.ICompanyDAO;
import com.carpool.entities.IConfingParamDAO;
import com.carpool.entities.IUserCredentialsDAO;
import com.carpool.entities.IUserDataDAO;
import com.carpool.entities.UserCredentials;
import com.carpool.entities.UserCredentialsDAO;
import com.carpool.entities.UserData;
import com.carpool.entities.UserDataDAO;
import com.carpool.exceptions.PoolFatalException;
import com.carpool.exceptions.PoolNonFatalException;
import com.carpool.util.CarPoolConstants;
import com.carpool.util.CarPoolUtil;
import com.carpool.util.CarpoolErrorCodes;


/**
 * @author dhagedee
 *
 */
public class UserService extends BaseService {

	private static BaseService service = null;
	
	private IUserCredentialsDAO userCredDao = new UserCredentialsDAO();
	private IUserDataDAO userDataDao = new UserDataDAO();
	
	
	public static synchronized BaseService getService(){
		if(service==null){
			service = new UserService();
		}
		return service;
	}
	
	private UserService(){
		
	}
	
	/**
	 * This service registers the application user.
	 * If user is already registered, throw error.
	 * else if user role is U, set expiry date = todays date + 30, and verified flag as N. 
	 * send confirmation code to user through mail.
	 * if user role is corporate user or vendor, expiry date and verification is not required.
	 * @param userVo
	 * @return
	 * @throws PoolNonFatalException
	 */
	public String registerUser(UserData userData, UserCredentials userCredentials) throws PoolNonFatalException{
		
		
		List userList = userCredDao.findByUserName(userCredentials.getUserName());
		
		if(userList!=null && userList.size()>0){
			/* if user is already there in database, throw error */
			throw new PoolNonFatalException(CarpoolErrorCodes.USER_EXISTS);
		}
		/* if user is not present in database, register it */
		
		/* replace below call and user companyservice to get company
		 * 
		 */
		ICompanyDAO companyDao = new CompanyDAO();
		Company userCompany = companyDao.findById(userData.getCompany().getCompanyId());
		if(userCompany!=null){
			userData.setCompany(userCompany);
		}
		
		/* if user role is U, set expiry date, send confirmation code and set verified flag as N. */
		if(CarPoolConstants.USER == userData.getUserType().getUserTypeId()){
			
			//CarPoolUtil.sendConfirmationCode(userCredentials.getUserId(),userCredentials.getUserName(), userData.getCompany().getCompanyName());
			userCredentials.setIsAuthorized(CarPoolConstants.NO);
			
			
		} /* if user role is not U, i.e . if user role is Vendor or corporate, expiry date and confirmation is not required */
		else{
			userCredentials.setIsAuthorized(CarPoolConstants.YES);
		}
		userCredentials.setIsActive(CarPoolConstants.YES);
		userCredentials.setLoginAttemptCount(0);
		
		try{
			EntityManagerHelper.beginTransaction();
			
			Map configParams = ApplicationCache.getConfigParams();
			String userCredits = (String)configParams.get(CarPoolConstants.USER_CREDITS);
			
			userCredentials.setUserCredits(Double.parseDouble(userCredits));
			
			// get the userCreditExpiryLimit and use it for credit expiry date
			String expiryLimit = (String)configParams.get(CarPoolConstants.USER_CREDIT_EXPIRY_LIMIT);
			int iExpiryLimit = Integer.parseInt(expiryLimit);
			
			// get userAccountExpiryDate and use it to set account expirydate
			expiryLimit = (String)configParams.get(CarPoolConstants.USER_ACCOUNT_EXPIRY_LIMIT);
			int iAccountExpiryLimit = Integer.parseInt(expiryLimit);
			
			Timestamp now = new Timestamp(System.currentTimeMillis());
			userCredentials.setUserCreationDate(now);
			userCredentials.setUserUpdateDate(now);
			Timestamp then = new Timestamp(now.getTime());
			then.setDate(now.getDate()+iExpiryLimit);
			userCredentials.setCreditExpiryDate(then);
			then.setDate(now.getDate()+iAccountExpiryLimit);
			userCredentials.setAccountExpiryDate(then);
			
			userCredentials.setRecCreDate(now);
			userDataDao.save(userData);
			userCredentials.setUserData(userData);
			userCredentials.setUserId(userData.getUserId());
			userCredDao.save(userCredentials);
			
			if(CarPoolConstants.USER == userData.getUserType().getUserTypeId()){
				CarPoolUtil.sendConfirmationCode(userCredentials.getUserId(),userCredentials.getUserName(), userData.getCompany().getCompanyName());
			}
			
			EntityManagerHelper.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
			if(EntityManagerHelper.isACtive())
				EntityManagerHelper.rollback();
			throw new PoolFatalException(e);
		}
		return CarPoolConstants.SUCCESS;
		
	}
	public UserData getUserData(UserCredentials userCredentials) throws PoolNonFatalException{
	
		List userCredList = userCredDao.findByUserName(userCredentials.getUserName());
		UserCredentials userCreds = null;
		
		if(userCredList!=null && userCredList.size()>0){
			userCreds = (UserCredentials)userCredList.get(0);
			UserData userData = getUserData(userCreds.getUserId());
			return userData;
		}
		else{
			return null;	
		}
		
	}
	public UserData getUserData(int userId) throws PoolNonFatalException{
		UserData userData = userDataDao.findById(userId);
		return userData;
	}
	public UserCredentials getUserCredentials(UserCredentials userCredentials) throws PoolNonFatalException{
		
		List userCredList = userCredDao.findByUserName(userCredentials.getUserName());
		UserCredentials userCreds = null;
		
		if(userCredList!=null && userCredList.size()>0){
			userCreds = (UserCredentials)userCredList.get(0);
			System.out.println("hey, user id in user service : "+userCreds.getUserId());
			return userCreds;
		}
		else{
			return null;	
		}
		
	}
	public UserCredentials getUserCredentials(int userId) throws PoolNonFatalException{
		UserCredentials userCred = userCredDao.findById(userId);
		return userCred;
	}
	/**
	 * if the display name is already used by some user, return true else return false
	 * @param displayName
	 * @return
	 */
	public boolean checkDisplayName(String displayName){
		List userDataList = userDataDao.findByDisplayName(displayName);
		if(userDataList!=null && userDataList.size()>0){
			return true;
		}
		return false;
	}
	
	/**
	 * This method is to update the email id of the user
	 * @throws PoolNonFatalException
	 */
	public void updateUserEmail(UserData userData) throws PoolNonFatalException{
	
		try{
			EntityManagerHelper.beginTransaction();
			UserData userDataFromDB = userDataDao.findById(userData.getUserId());
			if(userDataFromDB==null){
				throw new PoolNonFatalException(CarpoolErrorCodes.USER_NOT_EXISTS);
			}
			
			userDataFromDB.setEmail(userData.getEmail());
			userDataDao.update(userDataFromDB);
			EntityManagerHelper.commit();
		}
		catch (Exception e) {
			// TODO: handle exception
			if(EntityManagerHelper.isACtive())
				EntityManagerHelper.rollback();
			if(e instanceof PoolNonFatalException){
				
				throw (PoolNonFatalException)e;
			}
			else{
				throw new PoolFatalException(e);
			}
		}
	}
	
	/**
	 * This method is to update the phone nulber of the user
	 * @throws PoolNonFatalException
	 */
	public void updateUserPhone(UserData userData) throws PoolNonFatalException{
	
		try{
			EntityManagerHelper.beginTransaction();
			UserData userDataFromDB = userDataDao.findById(userData.getUserId());
			if(userDataFromDB==null){
				throw new PoolNonFatalException(CarpoolErrorCodes.USER_NOT_EXISTS);
			}
			
			userDataFromDB.setPhone(userData.getPhone());
			userDataDao.update(userDataFromDB);
			EntityManagerHelper.commit();
		}
		catch (Exception e) {
			// TODO: handle exception
			if(EntityManagerHelper.isACtive())
				EntityManagerHelper.rollback();
			if(e instanceof PoolNonFatalException){
				
				throw (PoolNonFatalException)e;
			}
			else{
				throw new PoolFatalException(e);
			}
		}
		
	}

	public void changePassword(UserCredentials userCredentials) throws PoolNonFatalException {
		// TODO Auto-generated method stub
		try{
			EntityManagerHelper.beginTransaction();
			UserCredentials userCredFromDB = userCredDao.findById(userCredentials.getUserId());
			if(userCredFromDB==null){
				throw new PoolNonFatalException(CarpoolErrorCodes.USER_NOT_EXISTS);
			}
			
			userCredFromDB.setPassword(userCredentials.getPassword());
			userCredDao.update(userCredFromDB);
			EntityManagerHelper.commit();
		}
		catch (Exception e) {
			// TODO: handle exception
			if(EntityManagerHelper.isACtive())
				EntityManagerHelper.rollback();
			if(e instanceof PoolNonFatalException){
				
				throw (PoolNonFatalException)e;
			}
			else{
				throw new PoolFatalException(e);
			}
		}

	}
	
	
}
