/**
 * 
 */
package com.carpool.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.carpool.data.GeoRoute;
import com.carpool.data.RouteData;
import com.carpool.util.mongo.MongoConnectionManager;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

/**
 * @author Deepak
 * 
 */
public class GeoUtil {

	private static final String radiusStart = "RSTART";
	private static final String radiusEnd = "REND";
	private static DB mongoDB = MongoConnectionManager.getDBInstance();

	public static List<Integer> getRoutes(GeoRoute geoRoute) {

		Map<String, Double> radiusData = getRadiusToSearch(geoRoute);

		List<Integer> integerList = new ArrayList<Integer>();

		double actualStartRadius = radiusData.get(radiusStart) / 6367.0;
		double actualEndRadius = radiusData.get(radiusEnd) / 6367.0;

		double fromLat = geoRoute.getStartPoint().getLat();
		double fromLng = geoRoute.getStartPoint().getLng();
		double toLat = geoRoute.getEndPoint().getLat();
		double toLng = geoRoute.getEndPoint().getLng();

		if (mongoDB != null) {
			List<Integer> fromRoute = new ArrayList<Integer>();
			DBCollection coll = mongoDB.getCollection("routes");

			// Fetching all the route id's that match the start
			BasicDBObject findQuery = new BasicDBObject();

			BasicDBObject nearSphere = new BasicDBObject();
			nearSphere.put("$nearSphere", new Double[] { fromLng, fromLat });
			nearSphere.put("$maxDistance", actualStartRadius);
			findQuery.put("l", nearSphere);

			DBCursor cur = coll.find(findQuery);
			Gson gson = null;
			while (cur.hasNext()) {
				gson = new Gson();
				
				RouteData dob = gson.fromJson(cur.next().toString(),
						RouteData.class);
				//fromRoute.add(Integer.parseInt(dob.getR()));
				fromRoute.add(dob.getR().intValue());
			}

			List<Integer> toRoute = new ArrayList<Integer>();

			// Fetching all the route id's that match the end
			findQuery = new BasicDBObject();

			nearSphere = new BasicDBObject();
			nearSphere.put("$nearSphere", new Double[] { toLng, toLat });
			nearSphere.put("$maxDistance", actualEndRadius);
			findQuery.put("l", nearSphere);

			DBCursor curNew = coll.find(findQuery);
			while (curNew.hasNext()) {
				gson = new Gson();
				RouteData dob = gson.fromJson(curNew.next().toString(),
						RouteData.class);
				//toRoute.add(Integer.parseInt(dob.getR()));
				toRoute.add(dob.getR().intValue());
			}

			// compare the two list for routes
			Iterator<Integer> iter = fromRoute.iterator();
			while (iter.hasNext()) {
				int i = iter.next();
				if (toRoute.contains(i)) {
					integerList.add(i);
				}
			}

		}
		return integerList;

	}

	private static Map<String, Double> getRadiusToSearch(GeoRoute geoRoute) {

		double distance = calculateApproxDistanceBetweenPoints(geoRoute);

		Map<String, Double> radius = new HashMap<String, Double>();

		// Calculate the radius here ... Needs some work to be done on this
		radius.put(radiusStart, distance * 0.05 < 0.5 ? 0.5 : distance * 0.05);
		radius.put(radiusEnd, distance * 0.1 < 1 ? 1 : distance * 0.1);

		return radius;
	}

	private static double calculateApproxDistanceBetweenPoints(GeoRoute geoRoute) {

		// http://mathforum.org/library/drmath/view/51879.html
		/*
		 * Formula dlon = lon2 - lon1 dlat = lat2 - lat1 a = (sin(dlat/2))^2 +
		 * cos(lat1) * cos(lat2) * (sin(dlon/2))^2 c = 2 * atan2(sqrt(a),
		 * sqrt(1-a)) d = R * c
		 */

		// Convert to radians first
		double toRadians = (Math.PI / 180);

		double flat = geoRoute.getStartPoint().getLat() * toRadians;
		double flon = geoRoute.getStartPoint().getLng() * toRadians;
		double tlat = geoRoute.getEndPoint().getLat() * toRadians;
		double tlon = geoRoute.getEndPoint().getLng() * toRadians;

		double deltaLat = tlat - flat;
		double deltaLon = tlon - flon;

		double a = Math.pow((Math.sin(deltaLat / 2)), 2) + Math.cos(flat)
				* Math.cos(tlat) * Math.pow((Math.sin(deltaLon / 2)), 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = 6367 * c;
		return distance;
	}

	public static boolean saveRoute(GeoRoute geoRoute) {
		int rid = geoRoute.getRouteId();
		if (mongoDB != null) {
			DBCollection coll = mongoDB.getCollection("routes");

			BasicDBObject insertQuery = new BasicDBObject();
			insertQuery.put("r", rid);
			insertQuery.put("l", new Double[] {
					geoRoute.getStartPoint().getLng(),
					geoRoute.getStartPoint().getLat() });
			coll.save(insertQuery);

			insertQuery = new BasicDBObject();
			insertQuery.put("r", rid);
			insertQuery.put("l", new Double[] {
					geoRoute.getEndPoint().getLng(),
					geoRoute.getEndPoint().getLat() });
			coll.save(insertQuery);

			return true;
		}
		return false;
	}

}
