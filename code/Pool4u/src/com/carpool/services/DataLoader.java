/**
 * 
 */
package com.carpool.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.SortedSet;
import java.util.TreeSet;

import com.carpool.dao.helper.EntityManagerHelper;

import com.carpool.data.ApplicationCache;
import com.carpool.entities.CompanyDAO;
import com.carpool.entities.ICompanyDAO;
import com.carpool.exceptions.PoolFatalException;

/**
 * @author Deepak
 *
 */
public class DataLoader extends BaseService{

 private static BaseService service = null;
	
	public static synchronized BaseService getService(){
		if(service==null){
			service = new DataLoader();
		}
		return service;
	}
	/**
	 * 
	 */
	private DataLoader() {
		// TODO Auto-generated constructor stub
	}
	
	public static void loadData(){
		
		if(ApplicationCache.getCorporateMap().size()==0){
			ICompanyDAO corporateDao = new CompanyDAO();
			List corporateList = corporateDao.findAll();
			System.out.println("in load data : "+corporateList.size());
			ApplicationCache.setCorporateMap(corporateList);
		}
		
		
	}

}
