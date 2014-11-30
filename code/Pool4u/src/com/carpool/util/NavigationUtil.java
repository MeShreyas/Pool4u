/**
 * 
 */
package com.carpool.util;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.PropertyResourceBundle;

/**
 * @author dhagedee
 *
 */
public class NavigationUtil {

	private static Properties navigationProperties = new Properties();
	static{
		try{
			FileInputStream in = new FileInputStream("I:/Deepak/workspaces/pool4uWS/BasicServletCarPool/data/Navigation.properties");
			navigationProperties.load(in);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static String getNextPage(String requestId, String result){
		String nextPage = navigationProperties.getProperty(requestId+"."+result);
		if(nextPage==null){
			return "";
		}
		return nextPage;
		
	}
	
}
