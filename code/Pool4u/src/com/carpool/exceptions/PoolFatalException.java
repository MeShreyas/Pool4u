/**
 * 
 */
package com.carpool.exceptions;

/**
 * @author Deepak
 *
 */
public class PoolFatalException extends RuntimeException {

	public PoolFatalException(RuntimeException re){
		super(re.getMessage());
	}
	public PoolFatalException(Exception e){
		super(e.getMessage());
	}
	public PoolFatalException(String message){
		super(message);
	}
}

