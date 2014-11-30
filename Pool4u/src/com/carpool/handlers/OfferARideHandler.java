/**
 * 
 */
package com.carpool.handlers;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.carpool.data.GeoRoute;
import com.carpool.data.LatLng;
import com.carpool.entities.Pool;
import com.carpool.entities.Routes;
import com.carpool.exceptions.PoolFatalException;
import com.carpool.factory.ServiceFactory;
import com.carpool.services.BaseService;
import com.carpool.services.OfferRideService;
import com.carpool.util.CarPoolConstants;
import com.carpool.util.CarPoolUtil;
import com.pool4u.services.session.SessionManager;


/**
 * @author Deepak
 *
 */
public class OfferARideHandler implements ActionHandler {
	private String gender = "";
	private Pool pool = new Pool();
	private Routes route = new Routes();
	private boolean isError;
	private String frequency;
	private String isReturn;
	private GeoRoute geoRoute = new GeoRoute();
	
	/* (non-Javadoc)
	 * @see com.carpool.handlers.ActionHandler#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.http.HttpSession)
	 */
	public String execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		boolean isValidSession = SessionManager.isSessionValid(request);
		if(isValidSession){
			gender = session.getAttribute("gender").toString();
		}
		System.out.println("I am in OfferARideHandler");
		setUIDataToVO(request, session);
		BaseService service = ServiceFactory.getService(CarPoolConstants.OFFER_A_RIDE) ;
		OfferRideService offerRideService = (OfferRideService) service;
		pool=offerRideService.OfferARide(pool,geoRoute);
		//List filtersList = (ArrayList)poolInitData.get("PoolFilters");
		
		String jSonResponse = "{\"status\":\"SUCCESS\"}";
		response.getWriter().print(jSonResponse);
		return CarPoolConstants.SUCCESS;
		
		
	}
	
	private void setUIDataToVO(HttpServletRequest request, HttpSession session) {
		
		
		String tempStr = request.getParameter("from");
		String from = "";
		int lastIndex= -1;
		
		
		//LatLng tempLatLng = new LatLng();
		System.out.println("from place : "+tempStr);
		if(tempStr==null || tempStr.trim().length()==0){
			
			isError=true;
		}
		else{
			lastIndex = tempStr.lastIndexOf(",");
			if(lastIndex>0){
				tempStr = tempStr.substring(0,lastIndex);
			}
			lastIndex = tempStr.lastIndexOf(",");
			if(lastIndex>0){
				tempStr = tempStr.substring(0,lastIndex);
			}
			
			from = tempStr;
		}
		tempStr = request.getParameter("to");
		String to = "";
		lastIndex = -1;
		
		
		//LatLng tempLatLng = new LatLng();
		System.out.println("to place : "+tempStr);
		if(tempStr==null || tempStr.trim().length()==0){
			
			isError=true;
		}
		else{
			lastIndex = tempStr.lastIndexOf(",");
			if(lastIndex>0){
				tempStr = tempStr.substring(0,lastIndex);
			}
			lastIndex = tempStr.lastIndexOf(",");
			if(lastIndex>0){
				tempStr = tempStr.substring(0,lastIndex);
			}
			
			to =tempStr;
		}
		
		pool.setPoolTitle(from +"|"+ to);
		
		tempStr = request.getParameter("fromLatLng");
		
		//LatLng tempLatLng = new LatLng();
		System.out.println("from : "+tempStr);
		if(tempStr==null || tempStr.trim().length()==0){
			
			isError=true;
		}
		else{
			//Point startPoint =  CarPoolUtil.getPoint(tempStr);
			LatLng startPoint =  CarPoolUtil.getLatLng(tempStr);
			geoRoute.setStartPoint(startPoint);
			//get the VO to store in mongo DB
		}
		
		
		tempStr = request.getParameter("toLatLng");
		System.out.println("to : "+tempStr);
		if(tempStr==null || tempStr.trim().length()==0){
			
			isError=true;
		}
		else{
			//Point startPoint =  CarPoolUtil.getPoint(tempStr);
			LatLng endPoint =  CarPoolUtil.getLatLng(tempStr);
			 
			geoRoute.setEndPoint(endPoint);
			//get the VO to store in mongo DB
		}
		//pool.setRoutes(route);
		tempStr = request.getParameter("seatsAvbl");
		try{	
			int seats = Integer.parseInt(tempStr);
			pool.setSeats(seats);
		}
		catch (NumberFormatException e) {
			// TODO: handle exception
			isError = true;
		}
		
		tempStr = request.getParameter("isReturn");
		isReturn = tempStr;
		SimpleDateFormat formatDate = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
		// for one time 
		try {
			Date departDate=null;
			Timestamp tempTime=null;
			
				
				// pool is one time one way
				tempStr = request.getParameter("depart_date");
				String time = request.getParameter("depart_time");
				
				
					departDate = formatDate.parse(tempStr+" "+time);
					System.out.println("depart date : "+departDate);
					tempTime = new Timestamp(departDate.getTime());
					pool.setStartTime(tempTime);
					if("true".equals(isReturn)){
						// get the return time
						tempStr = request.getParameter("return_date");
						time = request.getParameter("return_time");
						Date returnDate = formatDate.parse(tempStr+" "+time);
						tempTime = new Timestamp(returnDate.getTime());
						pool.setReturnTime(tempTime);
					}
					else{
						pool.setReturnTime(null);
					}
						
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			throw new PoolFatalException(e);
		}
		
		// get contribution, description
		tempStr = request.getParameter("contribution");		
		pool.setContribution(tempStr);
		// set description - rideDescription
		tempStr = request.getParameter("rideDescription");
		pool.setDescription(tempStr);
		// check if approval is required for this ride
		tempStr = request.getParameter("isAuthorized");
		System.out.println("auto approve in handler : {" + tempStr+"}");
		if("true".equals(tempStr)){
			pool.setAutoApprove(CarPoolConstants.YES);
		}
		else{
			pool.setAutoApprove(CarPoolConstants.NO);
		}
		// get the filters
		tempStr = request.getParameter("isSmoking");
		
		if("true".equals(tempStr)){
			//filter.setFilterValue(CarPoolConstants.YES);
			pool.setSmoking(CarPoolConstants.YES);
		}
		else{
			//filter.setFilterValue(CarPoolConstants.NO);
			pool.setSmoking(CarPoolConstants.NO);
		}
		
		tempStr = request.getParameter("isWomenOnly");
		
		if(gender!=null || gender.trim().length()>0){
		
			if("true".equals(tempStr)){
				// if logged in user is woman, show womenOnly pools
				if(CarPoolConstants.FEMALE.equals(gender)){
					//filter.setFilterValue(CarPoolConstants.YES);
					pool.setWomenOnly(CarPoolConstants.YES);
				}
				else{ // if logged in user is man, do not show women-only pools
					pool.setWomenOnly(CarPoolConstants.NO);
				}
			}
			else{
					pool.setWomenOnly(CarPoolConstants.NO);
				
			}
		}
		
		//pool.getPoolFilterMaps().add(filter);
		pool.getUserData().setUserId(Integer.parseInt(session.getAttribute("userId").toString()));
	}
		

}

