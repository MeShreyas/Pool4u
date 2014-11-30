/**
 * 
 */
package com.carpool.handlers;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carpool.entities.UserCredentials;
import com.carpool.entities.UserData;
import com.carpool.exceptions.PoolNonFatalException;
import com.carpool.factory.ServiceFactory;
import com.carpool.services.BaseService;
import com.carpool.services.LoginService;
import com.carpool.services.UserService;
import com.carpool.util.CarPoolConstants;
import com.pool4u.services.session.SessionManager;

/**
 * @author Deepak
 *
 */
public class LoginHandler implements ActionHandler {

	private UserCredentials userCredentials = new UserCredentials();
	private boolean isError;
	private int errorCode;
	/**
	 * 
	 */
	public LoginHandler() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.carpool.handlers.ActionHandler#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.http.HttpSession)
	 */
	public String execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		setUIDataToVO(request, session);
		
		// with this vo as argument call the service class
		BaseService service = ServiceFactory.getService(CarPoolConstants.LOGIN);
		
		LoginService loginService = (LoginService)service;
		String result = "";
		
		try{
			
			String user_agent = request.getHeader("user-agent");
			user_agent = user_agent.substring(0,user_agent.indexOf("/"));
			System.out.println("user agent : "+user_agent);
		 result = loginService.doLogin(userCredentials,user_agent);
		 
		 if(CarPoolConstants.SUCCESS.equals(result)){
			 service = ServiceFactory.getService(CarPoolConstants.USER_SIGN_UP);
			 UserService userService = (UserService)service;
			 UserCredentials userCred = userService.getUserCredentials(userCredentials);
			 SessionManager.createNewSession(request, userCred);
			 
		 }
		 
		}
		catch (PoolNonFatalException e) {
			System.out.println("error message : "+e.getMessage());
			e.printStackTrace();
			errorCode = e.getErrorCode();
			isError= true;
		}
		response.setContentType("application/json");
		if(isError){
			String resp = "{\"status\":\"FAILURE\",\"errorCode\":\""+errorCode+"\"}";
			response.getWriter().print(resp);
			return CarPoolConstants.FAILURE;
		}
		else{
			String resp = "{\"status\":\""+result+"\",\"url\":\"http://sony-vaio:8080/Pool4u/profile.html\"}";
			System.out.println("resp : "+resp);
			response.getWriter().print(resp);
			return CarPoolConstants.SUCCESS;
		}
		
		
	}
	private void setUIDataToVO(HttpServletRequest request, HttpSession session) {
		
		String tempStr = request.getParameter("userName");
		
		if(tempStr==null || tempStr.trim().length()==0){
			
			isError=true;
		}
		else{
			userCredentials.setUserName(tempStr);
		}
		
		tempStr=request.getParameter("password");
		if(tempStr==null || tempStr.trim().length()==0){
			
			isError=true;
		}
		else{
			userCredentials.setPassword(tempStr);
		}
		
		
		
	}
}
