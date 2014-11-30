/**
 * 
 */
package com.carpool.factory;

import com.carpool.services.BaseService;
import com.carpool.services.CompanyService;
import com.carpool.services.ConfirmationService;
import com.carpool.services.LoginService;
import com.carpool.services.OfferRideService;
import com.carpool.services.SearchRideService;
import com.carpool.services.UpdateAllowedCompaniesService;
import com.carpool.services.UserService;
import com.carpool.util.CarPoolConstants;

/**
 * @author dhagedee
 *
 */
public class ServiceFactory {
	
	public static BaseService getService(String serviceName) throws Exception {
		if(CarPoolConstants.USER_SIGN_UP.equals(serviceName)){
			return UserService.getService();
		}
		else if(CarPoolConstants.CONFIRMATION.equals(serviceName)){
			return ConfirmationService.getService();
		}
		else if(CarPoolConstants.LOGIN.equals(serviceName)){
			return LoginService.getService();
		}
		else if(CarPoolConstants.FETCH_POOL_DATA.equals(serviceName) 
				|| CarPoolConstants.OFFER_A_RIDE.equals(serviceName)){
			return OfferRideService.getService();
		}
		else if(CarPoolConstants.FETCH_COMPANY_LIST.equals(serviceName)){
			return CompanyService.getService();
		}
		else if(CarPoolConstants.UPDATE_ALLOWED_COMPANIES.equals(serviceName)){
			return UpdateAllowedCompaniesService.getService();
		}
		else if(CarPoolConstants.SEARCH_RIDE.equals(serviceName)){
			return SearchRideService.getService();
		}
		else if(CarPoolConstants.POOL_QUERY.equals(serviceName)){
			return SearchRideService.getService();
		}
		else{
			throw new Exception("Invalid request Id");
		}
		
		
	}
}
