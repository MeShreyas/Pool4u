/**
 * 
 */
package com.carpool.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carpool.exceptions.PoolFatalException;
import com.carpool.exceptions.PoolNonFatalException;
import com.carpool.factory.ServiceFactory;
import com.carpool.services.BaseService;
import com.carpool.services.UpdateAllowedCompaniesService;
import com.carpool.util.CarPoolConstants;
import com.pool4u.services.session.SessionManager;

/**
 * @author Deepak
 *
 */	
public class UpdateAllowedCompaniesHandler implements ActionHandler {

	List companyList = new ArrayList();
	String flag="";
	private int userId;
	private boolean isError=false;
	/* (non-Javadoc)
	 * @see com.carpool.handlers.ActionHandler#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.http.HttpSession)
	 */
	public String execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		SessionManager.isSessionValid(request);
		setUIDataToVO(request, session);
		if(isError){
			throw new PoolFatalException("request parameter is missing!");
		}
		else{
			BaseService service = ServiceFactory.getService(CarPoolConstants.UPDATE_ALLOWED_COMPANIES);
			UpdateAllowedCompaniesService updateAllowedCompanies = (UpdateAllowedCompaniesService)service;
			try{
				updateAllowedCompanies.update(companyList,flag,userId);
				UserCompanyMappingHandler handler = new UserCompanyMappingHandler();
				return handler.execute(request, response, session);
			}
			catch (PoolNonFatalException e) {
				// TODO: handle exception
				
				String resp = "{\"status\":\"FAILURE\",\"errorCode\":\""+e.getErrorCode()+"\"}";
				System.out.println(resp);
				response.getWriter().print(resp);
				return CarPoolConstants.FAILURE;
			}
			
			
		}
		
	}
	
	private void setUIDataToVO(HttpServletRequest request, HttpSession session) {
		String tempStr;
		for(int index=1;;index++){
			tempStr = request.getParameter("company"+index);
			if(tempStr==null){
				break;
			}
			companyList.add(Integer.parseInt(tempStr));
		}
		tempStr=request.getParameter("updateFlag");
		System.out.println("flag ="+tempStr);
		if(tempStr==null){
			isError=true;
		}
		flag= tempStr;
		tempStr = session.getAttribute("userId").toString();
		userId=Integer.parseInt(tempStr);
		System.out.println("user id 222: "+userId);
	}

}
