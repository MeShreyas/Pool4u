/**
 * 
 */
package com.carpool.handlers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carpool.entities.UserCredentials;
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
public class ChangePasswordHandler implements ActionHandler {
	private UserCredentials userCredentials = new UserCredentials();
	/* (non-Javadoc)
	 * @see com.carpool.handlers.ActionHandler#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.http.HttpSession)
	 */
	public String execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		SessionManager.isSessionValid(request);
		setUIDataToVO(request, session);
		BaseService service = ServiceFactory.getService(CarPoolConstants.USER_SIGN_UP) ;
		UserService userService = (UserService)service;
		try{
		userService.changePassword(userCredentials);
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
	private void setUIDataToVO(HttpServletRequest request, HttpSession session) throws PoolNonFatalException {
		String tempStr="";
		tempStr=request.getParameter("password");
		if(tempStr==null || tempStr.trim().length()==0){
			throw new PoolNonFatalException(CarpoolErrorCodes.PASSWORD_NOT_ENTERED);
		}
		else{
			userCredentials.setPassword(tempStr);
		}
		userCredentials.setUserId(Integer.parseInt(session.getAttribute("userId").toString()));
	}

}
