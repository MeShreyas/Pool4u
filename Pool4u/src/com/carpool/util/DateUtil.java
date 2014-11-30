/**
 * 
 */
package com.carpool.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Deepak
 *
 */
public class DateUtil {

	/**
	 * 
	 */
	public DateUtil() {
		// TODO Auto-generated constructor stub
	}
	public static int compareDate(Date date1, Date date2){
		
			return date1.compareTo(date2);
	}
	public static int compareDateWithoutTime(Date date1, Date date2) {
		// TODO Auto-generated method stub
		Calendar cal = new GregorianCalendar(date1.getYear(), 
				date1.getMonth(), date1.getDay());
		Calendar today = new GregorianCalendar(date2.getYear(), 
				date2.getMonth(), date2.getDay());
		System.out.println("date difference : "+cal+" X "+today+" is "+cal.compareTo(today));
		return cal.compareTo(today);
	}
	public static long getTimeDeifference(Date from, Date date) {
		// TODO Auto-generated method stub
		long timeDiff = ((from.getTime() - date.getTime())/(1000*60));
		
		return timeDiff;
	}
}
