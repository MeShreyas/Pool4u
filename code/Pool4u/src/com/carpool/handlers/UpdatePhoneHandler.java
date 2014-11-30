/**
 * 
 */
package com.carpool.handlers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carpool.entities.UserData;
import com.carpool.exceptions.PoolNonFatalException;
import com.carpool.factory.ServiceFactory;
import com.carpool.services.BaseService;
import com.carpool.services.UserService;
import com.carpool.util.CarPoolConstants;
import com.carpool.util.CarpoolErrorCodes;
import com.pool4u.services.session.SessionManager;

/**
 * @author Deepak
 *
 */
public class UpdatePhoneHandler implements ActionHandler {

	private UserData userData = new UserData();
	/* (non-Javadoc)
	 * @see com.carpool.handlers.ActionHandler#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.http.HttpSession)
	 */
	public String execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		System.out.println("session validity : "+SessionManager.isSessionValid(request));
		try{
			setUIDataToVO(request, session);
			BaseService service = ServiceFactory.getService(CarPoolConstants.USER_SIGN_UP);
			UserService userService = (UserService)service;
			userService.updateUserPhone(userData);
			String resp = "{\"status\":\"SUCCESS\"}";
			response.getWriter().print(resp);
			return CarPoolConstants.SUCCESS;
		}
		catch(PoolNonFatalException e){
			String resp = "{\"status\":\"FAILURE\",\"errorCode\":\""+e.getErrorCode()+"\"}";
			System.out.println(resp);
			response.getWriter().print(resp);
			return CarPoolConstants.FAILURE;
		}
	}
	private void setUIDataToVO(HttpServletRequest request, HttpSession session) throws PoolNonFatalException  {
		
		String str = request.getParameter("phone");
		if(str==null|| str.trim().length()==0){
			throw new PoolNonFatalException(CarpoolErrorCodes.PHONE_NOT_ENTERED);
		}
		else{
			userData.setPhone(str);
		}
		userData.setUserId(Integer.parseInt(session.getAttribute("userId").toString())); 
		
		
	}


}
