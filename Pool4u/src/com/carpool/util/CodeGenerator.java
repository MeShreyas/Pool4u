/**
 * 
 */
package com.carpool.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Deepak
 *
 */
public class CodeGenerator {

	/**
	 * 
	 */
	public CodeGenerator() {
		// TODO Auto-generated constructor stub
	}
	public static String generateCodeForUser(String userName,String corporateName){
		
		/* change the implementation to generate the code using MD5 hash
		 * use user name + corporate name + // one more component to decide
		 */
		System.out.println("userName : "+userName+" : "+userName.hashCode());
		System.out.println("corporate : "+corporateName+" : "+corporateName.hashCode());
		
		int code = userName.hashCode()*corporateName.hashCode()/10000;
		if(code<0){
			code = code*(-1);
		}
		
		System.out.println("confirmation code : "+code);
		
		return code+"";
		
	}
public static String generateCodeForUser(String userName,String corporateName, Date expiryDate){
		
		/* change the implementation to generate the code using MD5 hash
		 * use user name + corporate name + // one more component to decide
		 */
		System.out.println("userName : "+userName+" : "+userName.hashCode());
		System.out.println("corporate : "+corporateName+" : "+corporateName.hashCode());
		
		long code = (userName.hashCode()*corporateName.hashCode()/10000) + expiryDate.getTime();
		if(code<0){
			code = code*(-1);
		}
		System.out.println("confirmation code : "+code);
		
		return code+"";
		
	}
public static List generateCodeForVendor(String userName,String corporateName,int numberOfCodes){
		
		/* change the implementation to generate the code using MD5 hash
		 * use user name + corporate name + // one more component to decide
		 */
		System.out.println("userName : "+userName);
		System.out.println("corporate : "+corporateName);
		List codes = new ArrayList();
		long code = System.currentTimeMillis();
		
		for(int index=0;index<numberOfCodes;index++){
			code = code+index;
			System.out.println(index +" : "+code);
			codes.add(code+"");
		}
		return codes;
		
	}
}
