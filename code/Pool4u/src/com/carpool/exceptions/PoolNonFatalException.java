/**
 * 
 */
package com.carpool.exceptions;

//import com.carpool.data.ApplicationCache;

/**
 * @author Deepak
 *
 */
public class PoolNonFatalException extends Exception {

	int errorCode;
	public PoolNonFatalException(int errorCode){
		
		//super((String)ApplicationCache.getErrorMessages().get(errorCode+""));
		this.errorCode = errorCode;
	}
	public int getErrorCode(){
		return errorCode;
	}
}
