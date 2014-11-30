/**
 * 
 */
package com.carpool.factory;

import com.carpool.handlers.ActionHandler;
import com.carpool.handlers.AskPoolQueryHandler;
import com.carpool.handlers.ChangePasswordHandler;
import com.carpool.handlers.CompanyListHandler;
import com.carpool.handlers.ConfirmationHandler;
import com.carpool.handlers.DisplayNameHandler;
import com.carpool.handlers.GetUserDetailsHandler;
import com.carpool.handlers.LoginHandler;
import com.carpool.handlers.OfferARideHandler;
import com.carpool.handlers.OfferRideInitHandler;
import com.carpool.handlers.SearchRideHandler;
import com.carpool.handlers.UpdateAllowedCompaniesHandler;
import com.carpool.handlers.UpdateEMailHandler;
import com.carpool.handlers.UpdatePhoneHandler;
import com.carpool.handlers.UserCompanyMappingHandler;
import com.carpool.handlers.UserSignUpHandler;
import com.carpool.services.SearchRideService;
import com.carpool.util.CarPoolConstants;

/**
 * @author dhagedee
 *
 */
public class HandlerFactory {

	public static ActionHandler getHandler(String requestId) throws Exception{
		if(CarPoolConstants.USER_SIGN_UP.equals(requestId)){
			return new UserSignUpHandler();
		}
		else if(CarPoolConstants.CONFIRMATION.equals(requestId)){
			return new ConfirmationHandler();
		}
		else if(CarPoolConstants.LOGIN.equals(requestId)){
			return new LoginHandler();
		}
		else if(CarPoolConstants.DISPLAY_NAME.equals(requestId)){
			return new DisplayNameHandler();
		}
		else if(CarPoolConstants.FETCH_POOL_DATA.equals(requestId)){
			return new OfferRideInitHandler();
		}
		else if(CarPoolConstants.FETCH_COMPANY_LIST.equals(requestId)){
			return new CompanyListHandler();
		}
		else if(CarPoolConstants.FETCH_ALLOWED_COMPANY_LIST.equals(requestId) 
				|| CarPoolConstants.FETCH_RISTRICTED_COMPANY_LIST.equals(requestId)){
			return new UserCompanyMappingHandler();
		}
		else if(CarPoolConstants.UPDATE_ALLOWED_COMPANIES.equals(requestId)){
			return new UpdateAllowedCompaniesHandler();
		}
		else if(CarPoolConstants.OFFER_A_RIDE.equals(requestId)){
			return new OfferARideHandler();
		}
		else if(CarPoolConstants.GET_USER_DETAILS.equals(requestId)){
			return new GetUserDetailsHandler();
		}
		else if(CarPoolConstants.UPDATE_EMAIL.equals(requestId)){
			return new UpdateEMailHandler();
		}
		else if(CarPoolConstants.UPDATE_PHONE.equals(requestId)){
			return new UpdatePhoneHandler();
		}
		else if(CarPoolConstants.CHANGE_PASSWORD.equals(requestId)){
			return new ChangePasswordHandler();
		}
		else if(CarPoolConstants.SEARCH_RIDE.equals(requestId)){
			return new SearchRideHandler();
		}
		else if(CarPoolConstants.POOL_QUERY_ASK.equals(requestId)){
			return new AskPoolQueryHandler();
		}
		else{
			throw new Exception("Invalid request Id");
		}//getCustData.success = /BasicServletCarPool/UserSignUp2.jsp
	}
	
}
