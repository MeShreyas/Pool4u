/**
 * 
 */
package com.carpool.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Deepak
 *
 */
public class AlertDeliveryExecutor {

	private static ExecutorService threadExecutor; 
		
	
	static{
		threadExecutor = Executors.newFixedThreadPool(10);
	}
	/**
	 * 
	 */
	public AlertDeliveryExecutor() {
		// TODO Auto-generated constructor stub
	}
	
	public void triggerAlert(AlertDeliveryThread alertDeliveryThread){
		threadExecutor.execute(alertDeliveryThread);
	}

}
