package com.pool4u.services.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FillerServlet
 */
public class CodeFillerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static HashMap<Integer,LabelObj> menuOptions = new HashMap<Integer,LabelObj>();
	
	private void initMenu() {
		menuOptions.put(1,new LabelObj("Home","index.html"));
		menuOptions.put(2,new LabelObj("Profile","profile.html"));
		menuOptions.put(3,new LabelObj("Offer Ride","offer_ride.html"));
		menuOptions.put(4,new LabelObj("Search Ride","searchAndJoin.html"));
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CodeFillerServlet() {
        super();
        initMenu();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int option = Integer.parseInt(request.getParameter("option"));
		int selected = Integer.parseInt(request.getParameter("selectedPage"));
		String resp="";
		
		switch(option)
		{
		case 1:
			
			// Header code here
			StringBuffer strBuff=new StringBuffer();
			
			strBuff.append("<ul id='menu'>");
			
			Set<Integer> keySet = menuOptions.keySet();
			
			Iterator<Integer> iter = keySet.iterator();
			
			while(iter.hasNext())
			{
				int key = iter.next();
				LabelObj value = menuOptions.get(key);
				strBuff.append("<li ");
				if(selected==key)
					strBuff.append("class='selected'");
				strBuff.append(">");
				strBuff.append("<a href='").append(value.getLink()).append("'>");
				strBuff.append(value.getLabel()).append("</a>").append("</li>");
			}
			
			strBuff.append("</ul>");
			
			resp = "{\"htmlCode\":\""+strBuff.toString()+"\"}";
			
			break;
		case 2:
			// Footer code
			resp = "This is the footer code";
			break;
		case 3:
			resp = "The video part here";
			break;
		}
		
		response.setContentType("application/json");
		response.getWriter().print(resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

	private class LabelObj 
	{	
		public LabelObj(String label, String link) {
			this.label = label;
			this.link = link;
		}
		private String label;
		private String link;
		/**
		 * @return the label
		 */
		public String getLabel() {
			return label;
		}
		/**
		 * @param label the label to set
		 */
		public void setLabel(String label) {
			this.label = label;
		}
		/**
		 * @return the link
		 */
		public String getLink() {
			return link;
		}
		/**
		 * @param link the link to set
		 */
		public void setLink(String link) {
			this.link = link;
		}
		
	}
}
