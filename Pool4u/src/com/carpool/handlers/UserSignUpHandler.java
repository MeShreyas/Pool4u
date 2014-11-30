/**
 * 
 */
package com.carpool.handlers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.carpool.entities.UserCredentials;
import com.carpool.entities.UserData;
import com.carpool.exceptions.PoolNonFatalException;
import com.carpool.factory.ServiceFactory;
import com.carpool.services.BaseService;
import com.carpool.services.UserService;
import com.carpool.util.CarPoolConstants;

/**
 * @author dhagedee
 *
 */
public class UserSignUpHandler implements ActionHandler {

	private boolean isError = false;
	private int errorCode = 0;
	private UserData userData = new UserData();
	private UserCredentials userCredentials = new UserCredentials();
	/* (non-Javadoc)
	 * @see com.carpool.Handler.ActionHandler#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	
	public String execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		/*System.out.println("userName  : "+request.getParameter("userName"));
		System.out.println("password  : "+request.getParameter("password"));
		System.out.println("First Name  : "+request.getParameter("firstName"));*/
		
		setUIDataToVO(request, session);
		
		// with this vo as argument call the service class
		BaseService service = ServiceFactory.getService(CarPoolConstants.USER_SIGN_UP);
		UserService userService = (UserService)service; 
		System.out.println("is there any error : "+isError);
		try{
			System.out.println("call service from here");
			userService.registerUser(userData, userCredentials);
		}
		catch (PoolNonFatalException e) {
			// TODO: handle exception
			System.out.println("error message : "+e.getMessage());
			e.printStackTrace();
			errorCode = e.getErrorCode();
			//response.getWriter().println(e.getMessage());
			isError= true;
		}
		
		//request.setAttribute("user",userVo);
		response.setContentType("application/json");
		
		if(isError){
			String resp = "{\"status\":\"FAILURE\",\"errorCode\":\""+errorCode+"\"}";
			response.getWriter().print(resp);
			System.out.println("resp : "+resp);
			return CarPoolConstants.FAILURE;
		}
		else{
			String resp = "{\"status\":\"SUCCESS\"}";
			System.out.println("resp : "+resp);
			response.getWriter().print(resp);
			return CarPoolConstants.SUCCESS;
		}
	
		
		
	}

	private void setUIDataToVO(HttpServletRequest request, HttpSession session) {
		// TODO Auto-generated method stub
		
		//userCredentials.setId();
		String tempStr = request.getParameter("userName");
		
		if(tempStr==null || tempStr.trim().length()==0){
			request.setAttribute("userNameErr","Please enter the user name!");
			isError=true;
		}
		else{
			userCredentials.setUserName(tempStr);
			
		}
		
		tempStr=request.getParameter("password");
		if(tempStr==null || tempStr.trim().length()==0){
			request.setAttribute("passwordErr","Please enter the password!");
			isError=true;
		}
		else{
			userCredentials.setPassword(tempStr);
		}
		tempStr=request.getParameter("firstName");
		if(tempStr==null || tempStr.trim().length()==0){
			request.setAttribute("firstNameErr","Please enter the first name!");
			isError=true;
		}
		else{
			userData.setFirstName(tempStr);
		}
		
		tempStr=request.getParameter("company");
		if(tempStr==null || tempStr.trim().length()==0){
			request.setAttribute("employerErr","Please select the employer!");
			isError=true;
		}
		else{
			userData.getCompany().setCompanyId(Integer.parseInt(tempStr));
		}
		tempStr=request.getParameter("role");
		if(tempStr==null || tempStr.trim().length()==0){
			request.setAttribute("roleErr","Please enter the role!");
			isError=true;
		}
		else{
			userData.getUserType().setUserTypeId(Integer.parseInt(tempStr));
		}
		tempStr=request.getParameter("displayName");
		if(tempStr==null || tempStr.trim().length()==0){
			request.setAttribute("roleErr","Please enter the displayName!");
			isError=true;
		}
		else{
			userData.setDisplayName(tempStr);
		}
		tempStr=request.getParameter("gender");
		if(tempStr==null || tempStr.trim().length()==0){
			request.setAttribute("roleErr","Please enter the gender!");
			isError=true;
		}
		else{
			userData.setGender(tempStr);
		}
		
	}

}
