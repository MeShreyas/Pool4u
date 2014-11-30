/**
 * 
 */
package com.carpool.handlers;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.carpool.data.GeoRoute;
import com.carpool.data.LatLng;
import com.carpool.data.PoolDetails;
import com.carpool.data.PoolDetailsForUI;
import com.carpool.entities.Pool;
import com.carpool.entities.Routes;
import com.carpool.exceptions.PoolFatalException;
import com.carpool.factory.ServiceFactory;
import com.carpool.services.BaseService;
import com.carpool.services.SearchRideService;
import com.carpool.util.CarPoolConstants;
import com.carpool.util.CarPoolUtil;
import com.carpool.util.CarpoolErrorCodes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.pool4u.services.session.SessionManager;

/**
 * @author Deepak
 *
 */
public class SearchRideHandler implements ActionHandler {

	private boolean isError;
	private Routes route = new Routes();
	
	private GeoRoute geoRoute = new GeoRoute();
	private Pool pool = new Pool();
	private String gender = "";
	/* (non-Javadoc)
	 * @see com.carpool.handlers.ActionHandler#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.http.HttpSession)
	 */
	public String execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		
		boolean isValidSession = SessionManager.isSessionValid(request);
		if(isValidSession){
			gender = session.getAttribute("gender").toString();
			System.out.println("gender from session : "+gender);
		}
		setUIDataToVO(request, session);
		BaseService service = ServiceFactory.getService(CarPoolConstants.SEARCH_RIDE) ;
		SearchRideService searchService = (SearchRideService)service;
		List<PoolDetails> pools = searchService.searchARide(pool,geoRoute);
		StringBuffer responseString = new StringBuffer();
		if(pools==null || pools.size()==0){
			responseString = responseString.append("{\"status\":\"FAILURE\",\"errorCode\":\""+CarpoolErrorCodes.NO_POOLS_FOUND+"\"}");
			response.getWriter().print(responseString.toString());
			return CarPoolConstants.FAILURE;
		}
		List<PoolDetailsForUI> poolsForUI = CarPoolUtil.getPoolDetailsForUI(pools);
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Timestamp.class, new sqlTimestampConverter());
		Gson gson = gsonBuilder.create();
		String myGson = gson.toJson(poolsForUI);
		
		responseString = responseString.append("{\"status\":\"SUCCESS\"");
		responseString = responseString.append(",\"pools\": ");
		responseString = responseString.append(myGson);
		if(SessionManager.isSessionValid(request)){
			responseString = responseString.append(",\"userLoggedIn\": \"true\"");
		}
		else{
			responseString = responseString.append(",\"userLoggedIn\": \"false\"");
		}
		responseString = responseString.append("}");
		System.out.println(responseString.toString());
		response.getWriter().print(responseString.toString());
		return CarPoolConstants.SUCCESS;
	}
	private void setUIDataToVO(HttpServletRequest request, HttpSession session) {
		String tempStr = request.getParameter("fromLatLng");
		
		if(tempStr==null || tempStr.trim().length()==0){
			
			isError=true;
		}
		else{
			LatLng startPoint =  CarPoolUtil.getLatLng(tempStr);
			geoRoute.setStartPoint(startPoint);
		}
		
		tempStr = request.getParameter("toLatLng");
		if(tempStr==null || tempStr.trim().length()==0){
			
			isError=true;
		}
		else{
			 LatLng endPoint =  CarPoolUtil.getLatLng(tempStr);
			 
			geoRoute.setEndPoint(endPoint);
		}
		pool.setRoutes(route);
		
		SimpleDateFormat formatDate = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
		// for one time 
		try {
			Date departDate=null;
			String tempTime="";
			
			tempStr = request.getParameter("depart_date");
			String time = request.getParameter("depart_time");
			System.out.println("tempStr time --- "+tempStr+" "+time);
			departDate = formatDate.parse(tempStr+" "+time);
			System.out.println("depart date : "+departDate);
			
			pool.setStartTime(new Timestamp(departDate.getTime()));
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			throw new PoolFatalException(e);
		}
		
		tempStr = request.getParameter("isAuthorized");
		if("true".equals(tempStr)){
			pool.setAutoApprove(CarPoolConstants.YES);
		}
		else {
			pool.setAutoApprove(CarPoolConstants.NO);
		}
		
		// get the filters
		tempStr = request.getParameter("isSmoking");
		
		if("true".equals(tempStr)){
			//filter.setFilterValue(CarPoolConstants.YES);
			pool.setSmoking(CarPoolConstants.YES);
		}
		else if("false".equals(tempStr)){
			//filter.setFilterValue("");
			pool.setSmoking(CarPoolConstants.NO);
		}
		else{
			pool.setSmoking("");
		}
		tempStr = request.getParameter("isWomenOnly");
		System.out.println("isWomenOnly : {" + tempStr+"}");
		// if user is logged in 
		if(gender!=null || gender.trim().length()>0){
			System.out.println("isWomenOnly 1 : {" + tempStr+"}");
		if("true".equals(tempStr)){
			System.out.println("isWomenOnly 2 : {" + gender+"}");
			// if logged in user is woman, show womenOnly pools
			if(CarPoolConstants.FEMALE.equals(gender)){
				System.out.println("isWomenOnly 3 : {" + tempStr+"}");
				pool.setWomenOnly(CarPoolConstants.YES);
			}
			else{ // if logged in user is man, do not show women-only pools
				System.out.println("isWomenOnly 4 : {" + tempStr+"}");
				pool.setWomenOnly(CarPoolConstants.NO);
			}
		}
		else {// if logged in user is woman, show all pools 
			if(CarPoolConstants.FEMALE.equals(gender)){
				System.out.println("isWomenOnly 5 : {" + tempStr+"}");
				pool.setWomenOnly("");
			}
			else{// if logged in user is man, do not show women-only pools
				System.out.println("isWomenOnly 6 : {" + tempStr+"}");
				pool.setWomenOnly(CarPoolConstants.NO);
			}
		}
		
		}
		else{
			// user has not logged in
			// in this case, do not show women-only pools.
			System.out.println("isWomenOnly : 7 {" + tempStr+"}");
			pool.setWomenOnly(CarPoolConstants.NO);
		}
		
		pool.getUserData().setUserId(Integer.parseInt(session.getAttribute("userId").toString()));
		
	}
	static private class sqlTimestampConverter implements JsonSerializer<Timestamp> {
		//Thu Feb 10 16:30:00
        static SimpleDateFormat sdf = new SimpleDateFormat("''EEE, MMM d, yyyy hh:mm aaa''");

       
        public JsonElement serialize(Timestamp src, Type srcType,JsonSerializationContext context) {
            return new JsonPrimitive(sdf.format(src));
        }
    }
}

