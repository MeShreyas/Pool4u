/**
 * 
 */
package com.carpool.util;

import java.util.HashMap;
import java.util.Map;



/**
 * @author Deepak
 *
 */
public class AlertDeliveryThread implements Runnable {

	private Map alertMap = new HashMap();
	
	/**
	 * 
	 */
	public AlertDeliveryThread( Map alertMap) {
		this.alertMap = alertMap;
		// TODO Auto-generated constructor stub
	}
	

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		// TODO Auto-generated method stub
		CarPoolUtil.sendAlert(alertMap);
		
	}
	

}
