package com.carpool.util.mongo;

import java.io.IOException;
import java.util.Properties;

import com.mongodb.DB;
import com.mongodb.Mongo;

public class MongoConnectionManager {
	
	private  String MONGO_HOST="dbh56.mongolab.com";
	private  int MONGO_PORT=27567;
	private Mongo mongoInstance=null;
	private static DB mongoDB=null;
	
	private MongoConnectionManager() {
		try {
			mongoInstance = new Mongo(MONGO_HOST,MONGO_PORT);	
			
			System.out.println(mongoInstance);
			mongoDB = mongoInstance.getDB("carpool");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static DB getDBInstance()
	{
		if(mongoDB==null)
		{
			MongoConnectionManager monConn = new MongoConnectionManager();
			if(monConn.getDBInstance().authenticate("root", "alpine".toCharArray()))
				return monConn.getDBInstance();
			else
				return null;
		}
		else
			return mongoDB;
		
	}

	private static DB getMongoDB() {
		return mongoDB;
	}


	
}
