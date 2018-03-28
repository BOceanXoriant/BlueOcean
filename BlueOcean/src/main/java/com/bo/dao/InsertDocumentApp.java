package com.bo.dao;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.bo.model.Status;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;



public class InsertDocumentApp {
	public static void save(Status status) {
		try {			
			DBCollection collection = getDbConnection();
			
			Map<String, Object> documentMap = new HashMap<String, Object>();
			documentMap.put("fileName", status.getFileName());
			documentMap.put("status", status.getStatus());
			collection.insert(new BasicDBObject(documentMap));

			DBCursor cursorDocJSON = collection.find();
			while (cursorDocJSON.hasNext()) {
				System.out.println(cursorDocJSON.next());
			}


		} catch (MongoException e) {
			e.printStackTrace();
		}
	}
	
	public static void update(Status update) {
		try {		
			DBCollection collection = getDbConnection();
			DBObject query = new BasicDBObject("fileName",update.getFileName());
			DBObject dbupdate=new BasicDBObject();
			dbupdate.put("$set", new BasicDBObject("status",update.getStatus()));
			WriteResult result=collection.update(query, dbupdate);				
			System.out.println("*********"+result);
		} catch (MongoException e) {
			e.printStackTrace();
		}
	}
	
	public static DBCollection getDbConnection() {
		Mongo mongo = new Mongo("localhost", 27017);
		DB db = mongo.getDB("BlueOcean");
		DBCollection collection = db.getCollection("FileStatus");
		return collection;
	}
	
	public static void saveJSON() throws IOException {
		 URL url = null;
		Mongo mongo = new Mongo("localhost", 27017);
		DB db = mongo.getDB("BlueOcean");
		DBCollection collection = db.getCollection("StoreJSON");
		 url = InsertDocumentApp.class.getClassLoader().getResource("Sample_json.txt");
		 InputStream  input = url.openStream();
		 String jsonTxt = IOUtils.toString(input);
		 DBObject dbObject = (DBObject)JSON.parse(jsonTxt);
		 collection.insert(dbObject);

    	 DBCursor cursorDocJSON = collection.find();
	    	while (cursorDocJSON.hasNext()) {
	    		System.out.println(cursorDocJSON.next());
	    	}
	}
}
