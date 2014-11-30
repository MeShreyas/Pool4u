/**
 * 
 */
package com.carpool.data;

import java.util.HashMap;
import java.util.List;

import com.carpool.dao.helper.EntityManagerHelper;
import com.carpool.entities.IRoutesDAO;
import com.carpool.entities.Routes;
import com.carpool.entities.RoutesDAO;
import com.carpool.util.CarPoolUtil;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

/**
 * @author Deepak
 *
 */
public class TestPoint {

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		
		//insert into routes (start,end) values (GeomFromText('POINT(1.1 3.1)'),GeomFromText('POINT(2.1 5.6)'));
		Routes route = new Routes();
		String wktPoint1 = "POINT(1 5)";
		String wktPoint2 = "POINT(2 5)";
		WKTReader fromText = new WKTReader();
	    Geometry geom = null;
	    geom = fromText.read(wktPoint1);
	    Geometry geom2 = null;
	    geom2 = fromText.read(wktPoint2);
		route.setStart((Point)geom);
		route.setEnd((Point)geom2);
		System.out.println("point : "+(Point)geom);
		
		
		EntityManagerHelper.beginTransaction();
		IRoutesDAO dao = new RoutesDAO();
		//dao.saveNative(route);
		//route = dao.findById(25);
		HashMap propertyMap = new HashMap();
		propertyMap.put("start", route.getStart());
		propertyMap.put("end", route.getEnd());
		
		
		//List<Routes> routes = dao.getRouteId(propertyMap);
		//System.out.println("route id : "+routes.get(0));
		System.out.println("start : "+route.getStart());
		System.out.println("end : "+route.getEnd());
		String str = "(18.49634974764459, 73.85382539672815)";
		CarPoolUtil.getPoint(str);
		
		EntityManagerHelper.commit();
		
		
	}

}
