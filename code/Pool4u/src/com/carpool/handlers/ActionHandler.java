/**
 * 
 */
package com.carpool.handlers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author dhagedee
 *
 */
public interface ActionHandler {
	
	public String execute(HttpServletRequest request, HttpServletResponse response, HttpSession session)throws Exception;
}
