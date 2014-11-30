/**
 * 
 */
package com.carpool.util;


import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;


//import com.carpool.data.ApplicationCache;

import com.carpool.data.ApplicationCache;
import com.carpool.data.GeoRoute;
import com.carpool.data.LatLng;
import com.carpool.data.PoolDetails;
import com.carpool.data.PoolDetailsForUI;
import com.carpool.data.PoolDetailsNew;
import com.carpool.entities.AlertDelivery;
import com.carpool.entities.AlertDeliveryDAO;
import com.carpool.entities.AlertHistory;
import com.carpool.entities.AlertHistoryDAO;
import com.carpool.entities.Company;
import com.carpool.entities.IAlertDeliveryDAO;
import com.carpool.entities.IAlertHistoryDAO;
import com.carpool.entities.NotificationEvents;
import com.carpool.entities.NotificationType;
import com.carpool.entities.UserData;
import com.carpool.exceptions.PoolFatalException;


/**
 * @author Deepak
 *
 */
public class CarPoolUtil {

	
	
	/**
	 * 
	 */
	public CarPoolUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public static boolean sendConfirmationCode(Integer userId, String userName, String corporateName){
		System.out.println("user name : "+userName);
		System.out.println("corporate : "+corporateName);
		String confirmationCode = CodeGenerator.generateCodeForUser(userName, corporateName);
		/*ICorporateDAO corporateDAO = new CorporateDAO();
		Corporate corp = corporateDAO.findById(corporateName);
		String corpEmailId = corp.getEmailId();
		String compDomain = corpEmailId.substring(corpEmailId.indexOf('@'));
		//String userId =userName.substring(0, userName.indexOf("_"+corporateName));
		String emailId = userName+compDomain;*/
		String emailId = userName;
		
		HashMap map = new HashMap();
		map.put(CarPoolConstants.EVENT_ID,CarPoolConstants.CONF_CODE_EVENT);
		map.put(CarPoolConstants.USER_ID,userId);
		map.put(CarPoolConstants.MESSAGE_ID,CarPoolConstants.CONF_CODE);
		map.put(CarPoolConstants.TO,emailId);
		map.put(CarPoolConstants.CHANNEL,CarPoolConstants.EMAIL);
		map.put(CarPoolConstants.CODE,confirmationCode);
		
		sendAlert(map);
		
		return true;
	}

	public static void sendAlert(List mapList){
		
		if(mapList == null || mapList.size() == 0){
			throw new PoolFatalException(CarPoolErrorMessages.NO_ALERTS);
		}
		HashMap map = null;
		
		for(int index = 0; index<mapList.size(); index++){
			map = (HashMap)mapList.get(index);
			sendAlert(map);
		}
	}
	public static void sendAlert(Map map){
		
	
			String alert = formatAlert(map);
			map.put(CarPoolConstants.ALERT, alert);
			map.put(CarPoolConstants.SUBJECT, ApplicationCache.getAlertProperties().getProperty(
					(String)map.get(CarPoolConstants.MESSAGE_ID)+"."+CarPoolConstants.SUBJECT));
			String mode = ApplicationCache.getAlertProperties().getProperty(
					(String)map.get(CarPoolConstants.MESSAGE_ID)+"."+CarPoolConstants.MODE);
			saveAlertToDispatch(map);
			switch (mode.charAt(0)) {
			case 'B':
				
				break;
			case 'R':
				if(map.get(CarPoolConstants.CHANNEL).equals(CarPoolConstants.EMAIL)){
					try {
						
						AlertDispatcher.sendMail(map);
					} catch (AddressException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						throw new PoolFatalException(e);
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						throw new PoolFatalException(e);
					}
				}
				else if(map.get(CarPoolConstants.CHANNEL).equals(CarPoolConstants.SMS)){
					AlertDispatcher.sendSMS(map);
				}
				break;
			default:
				throw new PoolFatalException("Alert mode '"+mode.charAt(0)+"' is not supported. Only R (real-time) and B (batch) modes are suppported!");
				
			}
			
			
			
			
	}

	
	
	private static AlertHistory saveAlertToDispatch(Map map) {
		// TODO Auto-generated method stub
		
		IAlertDeliveryDAO alertDeliveryDao = new AlertDeliveryDAO();
		IAlertHistoryDAO alertHistoryDao = new AlertHistoryDAO();
		AlertDelivery alertDelivery = new AlertDelivery();
		UserData userData = new UserData();
		userData.setUserId((Integer)map.get(CarPoolConstants.USER_ID));
		System.out.println("user data : "+userData.getUserId());
		alertDelivery.setUserData(userData);
		NotificationEvents notificationEvents = new NotificationEvents();
		notificationEvents.setEventId((Integer)map.get(CarPoolConstants.EVENT_ID));
		alertDelivery.setNotificationEvents(notificationEvents);
		NotificationType notificationType = new NotificationType();
		if(CarPoolConstants.SMS.equals((String)map.get(CarPoolConstants.CHANNEL))){
			notificationType.setNotificationType(CarPoolConstants.SMS_ID);
			notificationType.setNotificationDesc(CarPoolConstants.SMS);
		}
		else if(CarPoolConstants.EMAIL.equals((String)map.get(CarPoolConstants.CHANNEL))){
			notificationType.setNotificationType(CarPoolConstants.EMAIL_ID);
			notificationType.setNotificationDesc(CarPoolConstants.EMAIL);
		}
		alertDelivery.setNotificationType(notificationType);
		
		try {
			alertDelivery.setMessage(((String)map.get(CarPoolConstants.ALERT)).getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new PoolFatalException(e);
			
		}
		alertDelivery.setSubject((String)map.get(CarPoolConstants.SUBJECT));
		alertDelivery.setStatus(CarPoolConstants.PENDING);
		Timestamp now = new Timestamp(System.currentTimeMillis());
		alertDelivery.setRecCreDate(now);
		
		alertDeliveryDao.save(alertDelivery);
		if(map.get(CarPoolConstants.MODE).equals(CarPoolConstants.REAL_TIME)){
			alertDeliveryDao.delete(alertDelivery);
		}
		AlertHistory alertHistory = new AlertHistory();
		alertHistory.setUserData(userData);
		alertHistory.setAlertId(alertDelivery.getAlertId());
		alertHistory.setAlertDeliveryDate(now);
		alertHistory.setMessage(alertDelivery.getMessage());
		alertHistory.setNotificationEvents(notificationEvents);
		alertHistory.setNotificationType(notificationType);
		alertHistory.setRecCreDate(now);
		alertHistory.setStatus(alertDelivery.getStatus());
		alertHistory.setSubject(alertDelivery.getSubject());
		alertHistoryDao.save(alertHistory);
		return alertHistory;
	}

	/**
	 * This method returns the formatted alert message to deliver to car pool user.
	 * @param map
	 * @return formatted message	
	 */
	private static String formatAlert(Map map){
		String MessageId = (String)map.get(CarPoolConstants.MESSAGE_ID);
		String messageTemplate = ApplicationCache.getAlertProperties().getProperty(MessageId+"."+CarPoolConstants.MESSAGE);
		if(messageTemplate==null){
			throw new PoolFatalException(CarPoolErrorMessages.NO_ALERT_TEMPLATE);
		}
		/* create the string buffer from template so that the 
		 * dynamic place holders can be replaced by values */ 
		StringBuffer messageBuffer = new StringBuffer(messageTemplate);
		Set keySet =  map.keySet();
		Iterator keyIterator = keySet.iterator();
		String key = "";
		String value = "";
		while(keyIterator.hasNext()){
			key = (String)keyIterator.next();
			int index = messageBuffer.indexOf(key);
			if(index!=-1 
					&& messageBuffer.charAt(index-1)=='$' && messageBuffer.charAt(index-2)=='$'
					&& messageBuffer.charAt(index+key.length())=='$' && messageBuffer.charAt(index+key.length()+1)=='$'){
				value = (String)map.get(key);
				/* replace the $$dynamic_place_holder$$ by value */
				messageBuffer.replace(index-2, index+key.length()+2, value);
			}
		}
		System.out.println("formatted message = "+messageBuffer.toString());
		return messageBuffer.toString(); 
		
	}
	public static boolean isNullOrBlank(Object var) {
		// TODO Auto-generated method stub
		if(var==null){
			return true;
		}
		else if (var instanceof String) {
			String varString = (String) var;
			if(varString.trim().length()==0){
				return true;
			}
		}
		return false;
	}

	public static boolean validateConfirmation(String userName, String corporate, String code) {
	
		String generatedCode = CodeGenerator.generateCodeForUser(userName,corporate);
		if(generatedCode.equals(code)){
			return true;
		}
		return false;
		
	}
	
	/**
	 * This method gives the list of corporate names which are not vendors
	 * @return List of corporate names
	 */
	public static List getCorporateList (){
	  Map corporateMap = ApplicationCache.getCorporateMap();
	  Set corporateSet = corporateMap.keySet();
	  List corporateList = new ArrayList();
	  Iterator mapIterator = corporateSet.iterator();
	  Integer companyId=0;
	  String corporateName = null;
	  Company corporate = null;
	  while(mapIterator.hasNext()){
		companyId = (Integer)mapIterator.next();
		corporate = (Company)corporateMap.get(companyId);
	  	corporateName = corporate.getCompanyName();
	  	if(corporate!=null){
	  		corporateList.add(corporateName);
	  	}
	  }
	  // sort the list of corporate names.
	  Collections.sort(corporateList);
	 
	  return corporateList;
	}

	public static LatLng getLatLng(String tempStr) {
		// TODO Auto-generated method stub
		System.out.println("point : "+tempStr);
		String points[] = tempStr.split(",");
		String str1  = points[0];
		str1 = str1.substring(1, str1.length());
		//str1 = str1.trim();
		String str2  = points[1];
		str2 = str2.substring(0, str2.length()-1);
		str2 = str2.substring(1);
		Double d1 = new Double(str1);
		
	
		d1=new BigDecimal(d1,new MathContext(8)).doubleValue();
		System.out.println("d1 : "+d1);
		Double d2 = new Double(str2);
		
		d2=new BigDecimal(d2,new MathContext(8)).doubleValue();
		System.out.println("d1 : "+d2);
		
		LatLng latLng = new LatLng();
		latLng.setLat(d1);
		latLng.setLng(d2);
		System.out.println("point : "+latLng);
		return latLng;
		  
	}
	public static List<PoolDetailsForUI> getPoolDetailsForUI(List<PoolDetails> poolDetails){
		List<PoolDetailsForUI> poolDetailsForUI = new ArrayList<PoolDetailsForUI>();
		for(PoolDetails pool : poolDetails){
			poolDetailsForUI.add(new PoolDetailsForUI(pool));
		}
		return poolDetailsForUI;
	
	}
	public static List<PoolDetailsForUI> getPoolDetailsNewForUI(List<PoolDetailsNew> poolDetails){
		List<PoolDetailsForUI> poolDetailsForUI = new ArrayList<PoolDetailsForUI>();
		for(PoolDetailsNew pool : poolDetails){
			poolDetailsForUI.add(new PoolDetailsForUI(pool));
		}
		return poolDetailsForUI;
	
	}


}
