/**
 * 
 */
package com.carpool.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 * @author Deepak
 *
 */
public class AlertDispatcher {

	static final String host = "smtp.pool4u.in";
	static	String from = "contact@pool4u.in";
	static String to = "deepakndhage@gmail.com";
	static Properties properties  = System.getProperties();
	 

	/**
	 * 
	 */
	public AlertDispatcher() {
		// TODO Auto-generated constructor stub
	}
	static{
		
		 // Get system properties
        properties = System.getProperties();
        // Setup mail server
       properties.setProperty("mail.smtp.host", host);
       properties.put("mail.debug", "true");
       
   	properties.setProperty("mail.smtp.auth", "true");
   	properties.setProperty("mail.smtp.port", "25");
	}
	
	public static boolean sendMail() throws AddressException, MessagingException {
		 // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);
        // Create a default MimeMessage object.
        MimeMessage message = new MimeMessage(session);
        // Set the RFC 822 "From" header field using the
        // value of the InternetAddress.getLocalAddress method.
        
		message.setFrom(new InternetAddress(from));
		
        // Add the given addresses to the specified recipient type.
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        // Set the "Subject" header field.
        message.setSubject("hi..!");
        // Sets the given String as this part's content,
        // with a MIME type of "text/plain".
        message.setText("Hi ......");
        // Send message
        Transport.send(message);
         System.out.println("Message Send.....");
       
		return true;
	}
	public static boolean sendMail(Map alertMap) throws AddressException, MessagingException {
		 // Get the default Session object.
		String username = "contact@pool4u.in";
		String password = "password4u";
		PasswordAuthentication authentication = new PasswordAuthentication(username, password);
		Authenticator authenticator = new Authenticator();
		properties.setProperty("mail.smtp.submitter", authenticator.getPasswordAuthentication().getUserName());
       Session session = Session.getDefaultInstance(properties);
       // Create a default MimeMessage object.
       MimeMessage message = new MimeMessage(session);
       // Set the RFC 822 "From" header field using the
       // value of the InternetAddress.getLocalAddress method.
      
       message.setFrom(new InternetAddress(from));
       // Add the given addresses to the specified recipient type.
       message.addRecipient(Message.RecipientType.TO,
        		   new InternetAddress((String)alertMap.get(CarPoolConstants.TO)));
       // Set the "Subject" header field.
       message.setSubject((String)alertMap.get(CarPoolConstants.SUBJECT));
       // Sets the given String as this part's content,
       // with a MIME type of "text/plain".
       message.setText((String)alertMap.get(CarPoolConstants.ALERT));
       // Send message
       //Transport.send(message);
        System.out.println("Message Sent....."+message);
     System.out.println("Mail has not been sent.. code is commented");
		return true;
	}
	public static void main(String[] args) {
		try {
		//	sendMail();
			sendSMS(new HashMap());
		} 
		catch(Exception e){
			e.printStackTrace();
		}
		
		/*catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	// this method is sending mail using apache mail API
	// change it to send SMS
	public static void sendSMS(Map map) {
		// TODO Auto-generated method stub
		String username = "contact@pool4u.in";
		String password = "password4u";
		 Email email = new SimpleEmail();
         email.setHostName(host);
         email.setSmtpPort(25);
         email.setAuthenticator(new DefaultAuthenticator(username, password));
         //email.setTLS(true);
         try {
        	 email.setFrom(from);
		
	        // email.setSubject((String)alertMap.get(CarPoolConstants.SUBJECT));
	         //email.setMsg((String)alertMap.get(CarPoolConstants.ALERT));
	         email.setSubject("bara");
	         email.setMsg("naay");
	       
	         email.addTo("deepakndhage@gmail.com");//(String)alertMap.get(CarPoolConstants.TO)
	         email.setDebug(true);
	         //919970169928@smscountry.net,
	         email.send();
         } catch (EmailException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
	}
	
	

}
 class Authenticator extends javax.mail.Authenticator {
	private PasswordAuthentication authentication;

	public Authenticator() {
		String username = "contact@pool4u.in";
		String password = "password4u";
		authentication = new PasswordAuthentication(username, password);
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return authentication;
	}
}
