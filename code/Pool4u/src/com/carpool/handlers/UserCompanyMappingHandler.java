/**
 * 
 */
package com.carpool.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carpool.entities.Company;
import com.carpool.entities.UserAllowedCompanies;
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
public class UserCompanyMappingHandler implements ActionHandler {

	private boolean isError;
	private int userId;
	/* (non-Javadoc)
	 * @see com.carpool.handlers.ActionHandler#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.http.HttpSession)
	 */
	public String execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		SessionManager.isSessionValid(request);
		setUIDataToVO(request, session);
		// TODO Auto-generated method stub
		BaseService service = ServiceFactory.getService(CarPoolConstants.FETCH_COMPANY_LIST);
		// convert to companyService
		CompanyService companyService = (CompanyService)service;
		// call method to get the list of companies
		List companyList = companyService.getCompanies();
		if(companyList==null){
			throw new PoolFatalException("There are no companies in the system yet!");
		}
		List allowedCompanies = companyService.getAllowedCompanies(userId);
		if(allowedCompanies==null){
			allowedCompanies= new ArrayList();
		}
		List allowedCompanyList = new ArrayList();
		int allowedCompanyId = 0;
		int companyId = 0;
		for(int index=0; index<allowedCompanies.size(); index++){
			allowedCompanyId = ((UserAllowedCompanies)allowedCompanies.get(index)).getId().getCompanyId();
			for(int index1 = 0; index1<companyList.size(); index1++){
				companyId = ((Company)companyList.get(index1)).getCompanyId();
				if(allowedCompanyId==companyId){
					allowedCompanyList.add(companyList.get(index1));
					companyList.remove(index1);
					index1--;
				}
			}
		}
		if(allowedCompanyList.size()==0 && companyList.size()==0){
			throw new PoolFatalException("There are no companies in the system yet!");
		}else{
		
		response.setContentType("application/json");
			
		String jSonResponse = "{\"status\":\"SUCCESS\",\"allowedCompanies\" : [";
		StringBuffer allowedCompaniesTmp = new StringBuffer("");
		Company company = null;
		String isMyCompany="N";
		for(int index=0; index<allowedCompanyList.size(); index++){
			company = (Company)allowedCompanyList.get(index);
			if(session.getAttribute("companyId").equals(company.getCompanyId())){
				System.out.println("my company : "+company.getCompanyName());
				isMyCompany="Y";
			}
			else{
				isMyCompany="N";
			}
			allowedCompaniesTmp.append("{\"companyId\":"+company.getCompanyId()+", \"companyName\":"+"\""+company.getCompanyName()+"\", \"myCompany\":"+"\""+isMyCompany+"\"}");
			if(index!=allowedCompanyList.size()-1){
				allowedCompaniesTmp.append(",");
			}
		}
		if(companyList.size()>=0){
			allowedCompaniesTmp.append("],\"restrictedCompanies\" : [");
		}
		for(int index=0; index<companyList.size(); index++){
			company = (Company)companyList.get(index);
			allowedCompaniesTmp.append("{\"companyId\":"+company.getCompanyId()+", \"companyName\":"+"\""+company.getCompanyName()+"\"}");
			if(index!=companyList.size()-1){
				allowedCompaniesTmp.append(",");
			}
		}
		jSonResponse = jSonResponse+allowedCompaniesTmp.toString()+"]}";
		System.out.println("jsonResponse : \n"+jSonResponse);
		response.getWriter().print(jSonResponse);
		return CarPoolConstants.SUCCESS;
		}
		
	}
	
	private void setUIDataToVO(HttpServletRequest request, HttpSession session) {
		
		String tempStr = session.getAttribute("userId").toString();
		
			userId=Integer.parseInt(tempStr);
			System.out.println("user id : "+userId);
		
	}

}
