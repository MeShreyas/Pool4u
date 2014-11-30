/**
 * 
 */
package com.carpool.services;

import java.util.List;

import com.carpool.entities.CompanyDAO;
import com.carpool.entities.ICompanyDAO;
import com.carpool.entities.IUserAllowedCompaniesDAO;
import com.carpool.entities.UserAllowedCompaniesDAO;

/**
 * @author Deepak
 *
 */
public class CompanyService extends BaseService {
	private static BaseService service = null;
	private ICompanyDAO companyDao = new CompanyDAO();
	private IUserAllowedCompaniesDAO userAllowedCompaniesDao = new UserAllowedCompaniesDAO();
	public static synchronized BaseService getService(){
		if(service==null){
			service = new CompanyService();
		}
		return service;
	}
	private CompanyService() {
		
	}
	
	public List getCompanies(){
		
		List companyList = companyDao.findAll();
		if(companyList!=null && companyList.size()>0){
			return companyList;
		}
		return null;
		
	}
	public List getAllowedCompanies(int userId) {
		// TODO Auto-generated method stub
		List allowedCompanies = userAllowedCompaniesDao.findByProperty("id.userId", userId);
		if(allowedCompanies!=null && allowedCompanies.size()>0){
			return allowedCompanies;
		}
		
		return null;
	}
	
}
