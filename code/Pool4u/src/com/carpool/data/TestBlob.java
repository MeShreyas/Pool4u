package com.carpool.data;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import com.carpool.entities.AlertDelivery;
import com.carpool.entities.AlertDeliveryDAO;
import com.carpool.entities.IAlertDeliveryDAO;

public class TestBlob {

	public TestBlob() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AlertDeliveryDAO alertDeliveryDao = new AlertDeliveryDAO();
		StandardPBEStringEncryptor strongEncryptor = new StandardPBEStringEncryptor();
        strongEncryptor.setAlgorithm("PBEWithMD5AndDES");
        strongEncryptor.setPassword("KaayRe");
        System.out.println("password : "+strongEncryptor.encrypt("admin"));
        System.out.println("password : "+strongEncryptor.decrypt(strongEncryptor.encrypt("admin")));
		
		List<AlertDelivery> alerts = alertDeliveryDao.findAll();
		byte[] alertBytes;
		//Byte bytes[] = new Bytealert.getMe
		for(AlertDelivery alert : alerts){
			
			try {
				System.out.println("message : "+new String(alert.getMessage(),"UTF-8"));
				alertBytes = alert.getMessage();//.getBytes("UTF-8");
				//new String( alert.getMessage(),"UTF-8")
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
