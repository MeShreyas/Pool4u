/**
 * 
 */
package com.carpool.handlers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carpool.entities.Company;
import com.carpool.exceptions.PoolFatalException;
import com.carpool.factory.ServiceFactory;
import com.carpool.services.BaseService;
import com.carpool.services.CompanyService;
import com.carpool.util.CarPoolConstants;
import com.carpool.util.CarpoolErrorCodes;
import com.pool4u.services.session.SessionManager;

/**
 * @author Deepak
 *
 */
public class CompanyListHandler implements ActionHandler  {

	/**
	 * 
	 */
	public CompanyListHandler() {
		// TODO Auto-generated constructor stub
	}
	public String execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		
		System.out.println("session validity : "+SessionManager.isSessionValid(request));
		BaseService service = ServiceFactory.getService(CarPoolConstants.FETCH_COMPANY_LIST);
		// convert to companyService
		CompanyService companyService = (CompanyService)service;
		// call method to get the list of companies
		List companyList = companyService.getCompanies();
		if(companyList==null){
			
			throw new PoolFatalException("There are no companies in the system yet!");
		}
		else{
			String jSonResponse = "{\"status\":\"SUCCESS\",\"companies\" : [";
			StringBuffer tmpString = new StringBuffer("");
			Company company = null;
			for(int index=0; index<companyList.size(); index++){
				company = (Company)companyList.get(index);
				tmpString.append("{\"companyId\":"+company.getCompanyId()+", \"companyName\":"+"\""+company.getCompanyName()+"\"}");
				if(index!=companyList.size()-1){
					tmpString.append(",");
				}
			}
			jSonResponse = jSonResponse+tmpString.toString()+"]}";
			System.out.println("jsonResponse : \n"+jSonResponse);
			response.getWriter().print(jSonResponse);
			return CarPoolConstants.SUCCESS;
		}
		
	}
	
}
