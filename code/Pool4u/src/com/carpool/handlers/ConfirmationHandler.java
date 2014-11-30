/**
 * 
 */
package com.carpool.handlers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carpool.entities.UserCredentials;
import com.carpool.entities.UserData;
import com.carpool.exceptions.PoolNonFatalException;
import com.carpool.factory.ServiceFactory;
import com.carpool.services.BaseService;
import com.carpool.services.ConfirmationService;
import com.carpool.services.UserService;
import com.carpool.util.CarPoolConstants;
import com.pool4u.services.session.SessionManager;

/**
 * @author Deepak
 *
 */
public class ConfirmationHandler implements ActionHandler {

	private UserCredentials userCredentials = new UserCredentials();
	private UserData userData = new UserData();
	private String confirmationCode = "";
	private boolean isError;
	private int errorCode;
	/* (non-Javadoc)
	 * @see com.carpool.handlers.ActionHandler#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.http.HttpSession)
	 */
	public String execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		
		setUIDataToVO(request, session);
	
		// with this vo as argument call the service class
		BaseService service = ServiceFactory.getService(CarPoolConstants.CONFIRMATION);
		ConfirmationService confirmationService = (ConfirmationService)service;
		service = ServiceFactory.getService(CarPoolConstants.USER_SIGN_UP);
		UserService userService = (UserService) service;
		try{
			String user_agent = request.getHeader("user-agent");
			user_agent = user_agent.substring(0,user_agent.indexOf("/"));
			System.out.println("user agent : "+user_agent);
			confirmationService.confirmUser(userCredentials, confirmationCode,user_agent);
			userCredentials = userService.getUserCredentials(userCredentials);
			//userData.setUserCredentials(userCredentials);
			
		
		}
		catch (PoolNonFatalException e) {
			// TODO: handle exception
			System.out.println("error message : "+e.getMessage());
			e.printStackTrace();
			errorCode = e.getErrorCode();
			isError= true;
		}
		response.setContentType("application/json");
		if(isError){
			String resp = "{\"status\":\"FAILURE\",\"errorCode\":\""+errorCode+"\"}";
			System.out.println(resp);
			response.getWriter().print(resp);
			return CarPoolConstants.FAILURE;
		}
		else{
			String resp = "{\"status\":\"SUCCESS\",\"url\":\"http://sony-vaio:8080/Pool4u/profile.html\"}";
			System.out.println("resp : "+resp);
			response.getWriter().print(resp);
			SessionManager.createNewSession(request, userCredentials);
			return CarPoolConstants.SUCCESS;
		}
		
	}
	private void setUIDataToVO(HttpServletRequest request, HttpSession session) {
		// TODO Auto-generated method stub
		
		String tempStr = request.getParameter("userName");
		
		if(tempStr==null || tempStr.trim().length()==0){
			System.out.println("Please enter the user name!");
			isError=true;
		}
		else{
			userCredentials.setUserName(tempStr.trim());
		}
		
		
		tempStr=request.getParameter("auth_data");
		if(tempStr==null || tempStr.trim().length()==0){
			System.out.println("Please enter the confirmation code!");
			isError=true;
		}
		else{
			System.out.println("conf code : "+confirmationCode);
			confirmationCode = tempStr.trim();
		}
		if(isError){
			System.out.println("there is an error in input");
		}
		
	}
}
