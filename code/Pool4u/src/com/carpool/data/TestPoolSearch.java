/**
 * 
 */
package com.carpool.data;

import java.lang.reflect.Type;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.carpool.entities.PoolDAO;
import com.carpool.util.CarPoolUtil;
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
public class TestPoolSearch {

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		
		PoolDAO poolDao = new PoolDAO();
		List<Integer> routeIds = new ArrayList<Integer>();
		/*routeIds.add(61);
		routeIds.add(62);
		routeIds.add(66);*/
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("auto_approve", "N");
		params.put("smoking", "Y");
		params.put("womenOnly", "N");
		
		String strDate = "10/02/2011 04:30 pm";
		
		SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
		java.util.Date departDate = formatDate.parse(strDate);
		System.out.println("date : "+departDate);
		Timestamp tempTime = new Timestamp(departDate.getTime());
		System.out.println("timeStamp : "+tempTime);
		Timestamp start = (Timestamp)tempTime.clone();
		start.setMinutes(tempTime.getMinutes()-30); 
		System.out.println("start : "+start);
		System.out.println("timeStamp after start: "+tempTime);
		Timestamp end = (Timestamp) tempTime.clone();
		end.setMinutes(tempTime.getMinutes()+30);
		System.out.println("end : "+end);
		HashMap startDate = new HashMap<String, String>();
		
		startDate.put("startTime1", start.toString());
		startDate.put("startTime2", end.toString());
	//	List<PoolDetails> poolDetailsList = poolDao.searchPools(new ArrayList<Integer>(), new HashMap<String, String>(), new HashMap<String, String>());
		List<PoolDetails> poolDetailsList = poolDao.searchPools(routeIds, params,startDate,false);
		List<PoolDetailsForUI> poolsForUI = CarPoolUtil.getPoolDetailsForUI(poolDetailsList);
		 GsonBuilder gsons = new GsonBuilder();
		gsons.registerTypeAdapter(Timestamp.class, new sqlTimestampConverter());
		Gson gson2 = gsons.create();
		Gson gson = new Gson();
		
		String myGson = gson2.toJson(poolsForUI);
		
		System.out.println("pools fetched : "+poolDetailsList.size());
		StringBuffer responseString = new StringBuffer();
		responseString = responseString.append("{\"status\":\"SUCCESS\"");
		responseString = responseString.append(",\"pools\": ");
		responseString = responseString.append(myGson);
		
			responseString = responseString.append(",\"userLoggedIn\": \"true\"");
		
		responseString = responseString.append("}");
		System.out.println(responseString.toString());

	}
	static private class sqlTimestampConverter implements JsonSerializer<Timestamp> {
		//Thu Feb 10 16:30:00
        static SimpleDateFormat sdf = new SimpleDateFormat("''EEE, MMM d, yyyy hh:mm aaa''");

       
        public JsonElement serialize(Timestamp src, Type srcType,JsonSerializationContext context) {
            return new JsonPrimitive(sdf.format(src));
        }
    }

}
