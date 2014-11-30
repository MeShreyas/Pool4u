/**
 * 
 */
package com.carpool.services;

import java.sql.Timestamp;
import java.util.List;

import com.carpool.dao.helper.EntityManagerHelper;
import com.carpool.entities.IUserAllowedCompaniesDAO;
import com.carpool.entities.UserAllowedCompanies;
import com.carpool.entities.UserAllowedCompaniesDAO;
import com.carpool.entities.UserAllowedCompaniesId;
import com.carpool.exceptions.PoolFatalException;
import com.carpool.exceptions.PoolNonFatalException;
import com.carpool.util.CarPoolConstants;
import com.carpool.util.CarpoolErrorCodes;

/**
 * @author Deepak
 *
 */
public class UpdateAllowedCompaniesService extends BaseService {

	private static BaseService service = null;
	IUserAllowedCompaniesDAO userAllowedCompaniesDao = new UserAllowedCompaniesDAO();
	public static synchronized BaseService getService(){
		if(service==null){
			service = new UpdateAllowedCompaniesService();
		}
		return service;
	}
	private UpdateAllowedCompaniesService() {
		
	}
	public void update(List companyList, String flag,int userId) throws PoolNonFatalException {
		// TODO Auto-generated method stub
			try{
				if(companyList.size()==0){
					throw new PoolNonFatalException(CarpoolErrorCodes.NO_COMPANIES_SELECTED);
				}
				UserAllowedCompanies allowedCompany = new UserAllowedCompanies();
				UserAllowedCompaniesId id = new UserAllowedCompaniesId();
				allowedCompany.setId(id);
				allowedCompany.getId().setUserId(userId);
				try{
				EntityManagerHelper.beginTransaction();
				System.out.println("flag in service : "+flag);
				if(CarPoolConstants.RESTRICT.equals(flag)){
					System.out.println("going to delete");
				// delete the compay to user mapping from user allowed companies table
					for(int index=0;index<companyList.size();index++){
						 allowedCompany = new UserAllowedCompanies();
						 id = new UserAllowedCompaniesId();
						allowedCompany.setId(id);
						allowedCompany.getId().setUserId(userId);
					//add the company to user mapping in user allowed companies table
						allowedCompany.setMapTs(new Timestamp(System.currentTimeMillis()));
						allowedCompany.getId().setCompanyId(Integer.parseInt(companyList.get(index).toString()));
						System.out.println("deleting :"+allowedCompany.getId().getCompanyId()+" user : "+allowedCompany.getId().getUserId());
						userAllowedCompaniesDao.delete(allowedCompany);
					}
				}
				else if(CarPoolConstants.ALLOW.equals(flag)){
					
					for(int index=0;index<companyList.size();index++){
						 allowedCompany = new UserAllowedCompanies();
						 id = new UserAllowedCompaniesId();
						allowedCompany.setId(id);
						allowedCompany.getId().setUserId(userId);
					//add the company to user mapping in user allowed companies table
						allowedCompany.setMapTs(new Timestamp(System.currentTimeMillis()));
						allowedCompany.getId().setCompanyId(Integer.parseInt(companyList.get(index).toString()));
						System.out.println("saving :"+allowedCompany.getId().getCompanyId()+" user : "+allowedCompany.getId().getUserId());
						userAllowedCompaniesDao.save(allowedCompany);
					}
				}
				
				EntityManagerHelper.commit();
			}
			catch (Exception e) {
				if(EntityManagerHelper.isACtive())
					EntityManagerHelper.rollback();
				throw new PoolFatalException(e);
			}
		}
		catch (java.lang.IllegalStateException e) {
			
			if(EntityManagerHelper.isACtive())
				EntityManagerHelper.rollback();
			throw new PoolFatalException(e);
		}
	}
	
	
	
	
}
