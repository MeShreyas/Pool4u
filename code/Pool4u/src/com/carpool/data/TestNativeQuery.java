/**
 * 
 */
package com.carpool.data;

import javax.persistence.EntityManager;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Query;
import javax.persistence.SqlResultSetMapping;

import com.carpool.dao.helper.EntityManagerHelper;
import com.carpool.entities.UserData;

/**
 * @author Deepak
 *
 */


public class TestNativeQuery {

	/**
	 * 
	 */
	public TestNativeQuery() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String sql = "select user_data.user_id as userId, user_data.first_name as first," +
				" CONCAT(user_data.first_name, user_data.first_name) as fullName from user_data" +
				" having userId =8 and fullName='deepakdeepak'";
		EntityManager em = EntityManagerHelper.getEntityManager();
		
		//EntityManagerHelper.beginTransaction();
		Query q = em.createNativeQuery(sql, "getItem");
		//Query q = em.createNativeQuery(sql, com.carpool.entities.UserData.class);
		UserDataTable user = (UserDataTable)q.getSingleResult();
		/*Object[] userObj = (Object[])q.getSingleResult();
		UserDataTable user = (UserDataTable)userObj[0];
		user.setFullName(userObj[1].toString());*/
		System.out.println("user name : "+user.getFullName());
		//EntityManagerHelper.commit();
		
		String sql2 = "select pool.contribution as contri, routes.start as startPoint, " +
				"routes.end as endPoint, routes.route_id as route from pool, routes where " +
				"pool.pool_id = 36 and routes.route_id = pool.route_id";
		//, routes.route_id as route
		Query q2 = em.createNativeQuery(sql2, "getRoute");

		RouteContri routeContri = (RouteContri) q2.getSingleResult();
		System.out.println("contri : "+routeContri.getContri()+" start : "+routeContri.getStart()+" end : "+routeContri.getEnd());
		
		
	}

}
