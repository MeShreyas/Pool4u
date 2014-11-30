package com.carpool.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carpool.factory.HandlerFactory;
import com.carpool.handlers.ActionHandler;
import com.carpool.util.NavigationUtil;
import com.carpool.util.mongo.MongoConnectionManager;
import com.mongodb.DB;

/**
 * Servlet implementation class DispatcherServlet
 */
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DB mongoDB = MongoConnectionManager.getDBInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispatcherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Get Method");
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Post Method");
		String requestId = request.getParameter("requestId");
		System.out.println("request_id "+requestId);
		String isAjax = request.getParameter("isAjax");
		if(requestId==null || requestId.trim().length()==0 ){
			throw new ServletException("Null request Id");
		}
		try{
			ActionHandler userSignUpHandler = HandlerFactory.getHandler(requestId);
			HttpSession session = request.getSession();
			String nextPage = "";
			String result = userSignUpHandler.execute(request, response,session);
			
			/*if(!"true".equals(isAjax)){
				System.out.println("I Am not ajax");
				nextPage = NavigationUtil.getNextPage(requestId, result);
				RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
				dispatcher.forward(request, response);
				//response.sendRedirect(nextPage);
			}*/
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e.getMessage());
		}
		
	}

}
