/**
 * 
 */
package com.carpool.handlers;

import javax.mail.internet.AddressException;
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
import com.carpool.util.CarpoolErrorCodes;
import com.pool4u.services.session.SessionManager;

/**
 * @author Deepak
 *
 */
public class GetUserDetailsHandler implements ActionHandler {

	private int userId;
	/* (non-Javadoc)
	 * @see com.carpool.handlers.ActionHandler#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.http.HttpSession)
	 */
	public String execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		SessionManager.isSessionValid(request);
		setUIDataToVO(request, session);
		BaseService service = ServiceFactory.getService(CarPoolConstants.USER_SIGN_UP);
		UserService userService = (UserService)service; 
		UserCredentials userCredentials = userService.getUserCredentials(userId);
		
		String returnStr = "";
		String resp = "";
		if(userCredentials==null){
			resp = "{\"status\":\"FAILURE\",\"errorCode\":\""+CarpoolErrorCodes.USER_NOT_EXISTS+"\"}";
			returnStr = CarPoolConstants.FAILURE;
		}
		else{
			System.out.println("userData.getUserId() : "+userCredentials.getUserId()+", userName : "+userCredentials.getUserName());
			resp = "{\"status\":\"SUCCESS\"," +
					"\"userData\":{\"userId\":\""+userCredentials.getUserId()+"\",\"userName\":\""+userCredentials.getUserName()+
					"\",\"firstName\":\""+userCredentials.getUserData().getFirstName()+"\",\"lastName\":\""+userCredentials.getUserData().getLastName()
					+"\",\"email\":\""+userCredentials.getUserData().getEmail()+
					"\",\"phone\":\""+userCredentials.getUserData().getPhone()+
					"\",\"companyName\":\""+userCredentials.getUserData().getCompany().getCompanyName()+
					"\",\"companyId\":\""+userCredentials.getUserData().getCompany().getCompanyId()+
					"\"}}";
			
			returnStr = CarPoolConstants.SUCCESS;
		}
		System.out.println("resp : "+resp);
		response.setContentType("application/json");
		response.getWriter().print(resp);
		return returnStr;
		
	}
	private void setUIDataToVO(HttpServletRequest request, HttpSession session) {
		// TODO Auto-generated method stub
		
		String tempStr = request.getParameter("userId");
		System.out.println("user id from req : "+tempStr);
		if(tempStr==null){
			tempStr = session.getAttribute("userId").toString();
			System.out.println("user id from session ="+tempStr);
		}
		userId  = Integer.parseInt(tempStr);
		System.out.println("finally : "+userId);
	}
}
