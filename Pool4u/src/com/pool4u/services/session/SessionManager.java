package com.pool4u.services.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.carpool.entities.UserCredentials;
import com.carpool.entities.UserData;


public class SessionManager {
	
	public static boolean createNewSession(HttpServletRequest request,UserCredentials userCredentials)
	{	UserData userData = userCredentials.getUserData();
		if(userData==null){
			return false;
		}
		HttpSession session = request.getSession(true);
		
		session.setMaxInactiveInterval(60*60);
		
		session.setAttribute("userName", userCredentials.getUserName());
		System.out.println("userName : "+ userCredentials.getUserName());
		session.setAttribute("userId", userData.getUserId());
		System.out.println("userName : "+ userData.getUserId());
		session.setAttribute("gender", userData.getGender());
		session.setAttribute("companyName", userData.getCompany().getCompanyName());
		session.setAttribute("companyId", userData.getCompany().getCompanyId());
		session.setAttribute("userTypeId", userData.getUserType().getUserTypeId());
		session.setAttribute("userType", userData.getUserType().getUserTypeDesc());
		session.setAttribute("sessionId", session.getId());
		
		return true;
	}
		
	public static boolean isSessionValid(HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		
		if(session == null)
		{
			return false;
		}
		
		String requestSessionId = session.getId();
		String sessionId = (String)session.getAttribute("sessionId");
		
		if(sessionId!=null && sessionId.trim().length() > 0)
		{
			if(sessionId.trim().equalsIgnoreCase(requestSessionId))
			{
				return true;
			}
		}		
		return false;		
	}
	
	public static void invalidateSession(HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		
		if(session == null)
		{
			return;
		}
		
		session.invalidate();
	}

	public static String fetchUser(HttpServletRequest request) {
		
		HttpSession session = request.getSession(false);
		
		return (String)request.getAttribute("username");
	}
	
}
