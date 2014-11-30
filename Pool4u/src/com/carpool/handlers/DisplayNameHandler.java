/**
 * 
 */
package com.carpool.handlers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carpool.factory.ServiceFactory;
import com.carpool.services.BaseService;
import com.carpool.services.ConfirmationService;
import com.carpool.services.UserService;
import com.carpool.util.CarPoolConstants;
import com.carpool.util.CarpoolErrorCodes;

/**
 * @author Deepak
 *
 */
public class DisplayNameHandler implements ActionHandler {

	private boolean isError;
	private String displayName;
	/* (non-Javadoc)
	 * @see com.carpool.handlers.ActionHandler#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.http.HttpSession)
	 */
	public String execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		setUIDataToVO(request, session);
		BaseService service = ServiceFactory.getService(CarPoolConstants.USER_SIGN_UP);
		UserService userService = (UserService)service;
		boolean available = userService.checkDisplayName(displayName);
		if(available){
			String resp = "{\"status\":\"FAILURE\",\"errorCode\":\""+CarpoolErrorCodes.DISPLAY_NAME_UNAVAILABLE+"\"}";
			System.out.println(resp);
			response.getWriter().print(resp);
			return CarPoolConstants.FAILURE;
		}
		else{
			String resp = "{\"status\":\"SUCCESS\",\"url\":\"http://sony-vaio:8080/Pool4u/profile.html\"}";
			System.out.println("resp : "+resp);
			response.getWriter().print(resp);
			return CarPoolConstants.SUCCESS;
		}
		
		
		
	}
	private void setUIDataToVO(HttpServletRequest request, HttpSession session) {
		// TODO Auto-generated method stub
		
		String tempStr = request.getParameter("displayName");
		
		if(tempStr==null || tempStr.trim().length()==0){
			System.out.println("Please enter the display name!");
			isError=true;
		}
		else{
			displayName = tempStr.trim();
		}
		
		
			
	}
}
