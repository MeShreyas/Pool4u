/**
 * 
 */
package com.carpool.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import com.carpool.data.UserSessionData;
import com.carpool.exceptions.PoolFatalException;
import com.carpool.factory.ServiceFactory;
import com.carpool.services.BaseService;
import com.carpool.services.OfferRideService;
import com.carpool.util.CarPoolConstants;
import com.carpool.util.CarpoolErrorCodes;
import com.pool4u.services.session.SessionManager;

/**
 * @author Deepak
 *
 */
public class OfferRideInitHandler implements ActionHandler {

	/* (non-Javadoc)
	 * @see com.carpool.handlers.ActionHandler#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.http.HttpSession)
	 */
	public String execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		
		// with this vo as argument call the service class
		boolean isValidSession = false;
		isValidSession = SessionManager.isSessionValid(request);
		System.out.println("session validity : "+isValidSession);
		BaseService service = ServiceFactory.getService(CarPoolConstants.FETCH_POOL_DATA);
		OfferRideService offerRideService = (OfferRideService)service;
		UserSessionData userSessionData = new UserSessionData();
		if(isValidSession){
			userSessionData.setUserTypeId((Integer)session.getAttribute("userTypeId"));
			userSessionData.setGender((String)session.getAttribute("gender"));
		}
		Map poolInitData = offerRideService.fetchInitData(userSessionData);
		
		if(poolInitData==null){
			throw new PoolFatalException("No filters defined yet!");
		}
		else{
			List filtersList = (ArrayList)poolInitData.get("PoolFilters");
			System.out.println("filters : "+filtersList);
			String poolFilter = null;
			String jSonResponse = "{\"status\":\"SUCCESS\",\"poolFilters\" : [";
			StringBuffer tmpString = new StringBuffer("");
			for(int index=0; index<filtersList.size(); index++){
				poolFilter = (String)filtersList.get(index);
				tmpString.append("{\"filterDesc\":"+"\""+poolFilter+"\"}");
				if(index!=filtersList.size()-1){
					tmpString.append(",");
				}
			}
			jSonResponse = jSonResponse+tmpString.toString()+"]}";
			
			System.out.println("jsonResponse : \n"+jSonResponse);
			// instead of URL below put the filters in array
			/*String resp = "{\"status\":\"SUCCESS\",\"url\":\"http://localhost:8080/Pool4u/profile.html\"}";
			System.out.println("resp : "+resp);*/
			response.getWriter().print(jSonResponse);
			return CarPoolConstants.SUCCESS;
		}
	
	}

}

