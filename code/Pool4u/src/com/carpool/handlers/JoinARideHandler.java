/**
 * 
 */
package com.carpool.handlers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carpool.data.GeoRoute;
import com.carpool.data.LatLng;
import com.carpool.entities.Pool;
import com.carpool.exceptions.PoolFatalException;
import com.carpool.exceptions.PoolNonFatalException;
import com.carpool.factory.ServiceFactory;
import com.carpool.services.BaseService;
import com.carpool.services.JoinARideService;
import com.carpool.util.CarPoolConstants;
import com.carpool.util.CarPoolUtil;
import com.pool4u.services.session.SessionManager;

/**
 * @author Deepak
 *
 */
public class JoinARideHandler implements ActionHandler {
	private GeoRoute geoRoute = new GeoRoute();
	private Pool pool = new Pool();
	private int user_id;
	private boolean isError;
	private int errorCode;
	/* (non-Javadoc)
	 * @see com.carpool.handlers.ActionHandler#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.http.HttpSession)
	 */
	public String execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		SessionManager.isSessionValid(request);
		setUIDataToVO(request, session);
		BaseService service = ServiceFactory.getService(CarPoolConstants.JOIN_RIDE) ;
		JoinARideService joinARideService = (JoinARideService) service;
		try{
		joinARideService.joinRide(pool, user_id);
		}
		catch (PoolNonFatalException e) {
			// TODO: handle exception
			System.out.println("error message : "+e.getMessage());
			e.printStackTrace();
			errorCode = e.getErrorCode();
			isError= true;
		}
		catch (PoolFatalException e) {
			// TODO: handle exception
			System.out.println("error message : "+e.getMessage());
			e.printStackTrace();
			throw e;
			
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("error message : "+e.getMessage());
			e.printStackTrace();
			throw new PoolFatalException(e);
			
		}
		if(isError){
			String resp = "{\"status\":\"FAILURE\",\"errorCode\":\""+errorCode+"\"}";
			System.out.println(resp);
			response.getWriter().print(resp);
			return CarPoolConstants.FAILURE;
		}
		else{
			String resp = "{\"status\":\"SUCCESS\",\"url\":\"http://localhost:8080/Pool4u/ride_offer.html\"}";
			System.out.println("resp : "+resp);
			response.getWriter().print(resp);
			return CarPoolConstants.SUCCESS;
		}
		
	}
	private void setUIDataToVO(HttpServletRequest request, HttpSession session)  {
		
		String tempStr = request.getParameter("pool_id");
		
		if(tempStr==null || tempStr.trim().length()==0){
			
			isError=true;
		}
		else{
			try{
				pool.setPoolId(Integer.parseInt(tempStr));
			}
			catch (NumberFormatException e) {
				throw new PoolFatalException(e);
			}
		}
		tempStr = session.getAttribute(CarPoolConstants.USER_ID).toString();
		
		try{
			user_id = Integer.parseInt(tempStr);
		}
		catch (NumberFormatException e) {
			throw new PoolFatalException(e);
		}
	}
}