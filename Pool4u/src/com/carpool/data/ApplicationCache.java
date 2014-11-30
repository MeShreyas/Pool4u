/**
 * 
 */
package com.carpool.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.PropertyResourceBundle;


import com.carpool.entities.Company;
import com.carpool.entities.ConfingParam;
import com.carpool.entities.ConfingParamDAO;
import com.carpool.entities.IConfingParamDAO;
import com.carpool.exceptions.PoolFatalException;
import com.carpool.util.CarPoolErrorMessages;

/**
 * @author Deepak
 *
 */
public class ApplicationCache {

	private static Map corporateMap = new HashMap();
	
	private static Properties errorMessages = new Properties();
  private static Properties alertProperties = new Properties();
	private static Map configParams = new HashMap();
	
	

	static{
		try{
			
			FileInputStream alertFile = new FileInputStream(System.getProperties().getProperty("user.home")+"/Pool4u/data/Alerts.properties");//"I:/Deepak/workspaces/pool4uWS/
			alertProperties.load(alertFile);
			FileInputStream errorFile = new FileInputStream(System.getProperties().getProperty("user.home")+"/Pool4u/data/ErrorMessages.properties");
			errorMessages.load(errorFile);
			System.out.println("errors in file "+errorMessages.size());
		}
		catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new PoolFatalException(e);
		}
	}
	
	/**
	 * 
	 */
	public ApplicationCache() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return the alertProperties
	 */
	public static Properties getAlertProperties() {
		return alertProperties;
	}
	
	/**
	 * @return the corporateList
	 */
	public static Map getCorporateMap() {
		return corporateMap;
	}
	/**
	 * @param corporateMap the corporateList to set
	 */
	public static void setCorporateMap(List corporates) {
		if(corporates.size()>0){
			Company corporate = null;
			if(corporateMap.size()==0){
				System.out.println("adding data to map");
				for(int index=0; index<corporates.size(); index++){
					
					corporate = (Company)corporates.get(index);
					System.out.println("corporate : "+corporate.getCompanyName());
					corporateMap.put(corporate.getCompanyId(), corporate);
					System.out.println("data stored");
				}
			}
		}
		
	}
	public static void addCorporate(Company corporate){
		if(!corporateMap.containsKey(corporate.getCompanyId())){
			corporateMap.put(corporate.getCompanyId(), corporate);
		}
		
	}
	public static void updateCorporate(Company corporate){
		if(corporateMap.containsKey(corporate.getCompanyId())){
			corporateMap.put(corporate.getCompanyId(), corporate);
		}
		
	}
	public static void deleteCorporate(Company corporate){
		if(corporateMap.containsKey(corporate.getCompanyId())){
			corporateMap.remove(corporate.getCompanyId());
		}
		
	}
	
	public static Properties getErrorMessages(){
		if(errorMessages!=null){
			return errorMessages;
			
		}
		
		throw new PoolFatalException(CarPoolErrorMessages.NO_ERRORS);	
		
	}
	public static Map getConfigParams(){
		
		if(configParams.size()==0){
			
			IConfingParamDAO dao = new ConfingParamDAO();
			List config_paramList = dao.findAll();
			ConfingParam configParam = null;
			for(int i = 0; i<config_paramList.size(); i++){
				configParam = (ConfingParam)config_paramList.get(i);
				configParams.put(configParam.getParamName(), configParam.getParamValue());
			}
			
		}
		return configParams;
		
	}
	
	
	
	
	

}
