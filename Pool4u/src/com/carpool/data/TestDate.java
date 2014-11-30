/**
 * 
 */
package com.carpool.data;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.persistence.Query;

import com.carpool.dao.helper.EntityManagerHelper;
import com.carpool.entities.IPoolDAO;
import com.carpool.entities.IUserCredentialsDAO;
import com.carpool.entities.Pool;
import com.carpool.entities.PoolDAO;

import com.carpool.entities.Routes;
import com.carpool.entities.UserCredentials;
import com.carpool.entities.UserCredentialsDAO;
import com.carpool.entities.UserData;

/**
 * @author Deepak
 *
 */
public class TestDate {

	/**
	 * 
	 */
	public TestDate() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
		try {
			Date departDate = formatDate.parse("06-08-2011 05:00 PM");
			System.out.println("depart date : "+departDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Properties props = System.getProperties();
		Set keySet = props.keySet();
		Iterator propIterator = keySet.iterator();
		while (propIterator.hasNext()) {
			String object = (String) propIterator.next();
			System.out.println("Prop : "+object+"; value : "+props.getProperty(object));
		}*/
		
		
		/*EntityManagerHelper.beginTransaction();
		Timestamp now = new Timestamp(System.currentTimeMillis());
		Timestamp then = new Timestamp(now.getTime());
		then.setDate(now.getDate()+30);
		IUserCredentialsDAO dao = new UserCredentialsDAO();
		
		List<UserCredentials> allUsers = dao.findAll();
		for(UserCredentials userCred : allUsers){
			userCred.setAccountExpiryDate(then);
			dao.update(userCred);
			
		}
		
		EntityManagerHelper.commit();
		*/
		/*Pool pool = new Pool();
		pool.setAutoApprove("N");
		pool.setContribution("120");
		pool.setDescription("test2");
		pool.setPoolType(new PoolType());
		pool.getPoolType().setPoolTypeId(1);
		pool.setSeats(3);
		pool.setRoutes(new Routes());
		pool.getRoutes().setRouteId(11);
		pool.setRecCreDate(new Timestamp(System.currentTimeMillis()));
		pool.setUserData(new UserData());
		pool.getUserData().setUserId(8);
		EntityManagerHelper.beginTransaction();
		IPoolDAO poolDao = new PoolDAO();
		poolDao.save(pool);
		EntityManagerHelper.commit();*/
		
		
		String strDate = "02/09/2011 05:00 am";
		SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
		Date departDate=null;
		try {
			departDate = formatDate.parse(strDate);
			System.out.println("date : "+departDate);
			Timestamp tempTime = new Timestamp(departDate.getTime());
			System.out.println("timeStamp : "+tempTime);
			Timestamp start = tempTime;
			start.setMinutes(tempTime.getMinutes()-30); 
			System.out.println("half hour before : "+start.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
