package com.bo.parser;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


import org.apache.commons.io.IOUtils;

import com.bo.constant.BOAppConstants;

import net.sf.json.JSON;
import net.sf.json.xml.XMLSerializer;

public class XMLtoJsonConverter {
	private static InputStream input = null;

	public static JSON convertJOSN() throws IOException {
		JSON json = null;
		try {			
			input = new FileInputStream(getLastUploadedFile());
			String xmlData = IOUtils.toString(input);
			XMLSerializer xmlSerializer = new XMLSerializer();
			json = xmlSerializer.read(xmlData);
			System.out.println("JSON format : " + json);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			input.close();
		}
		return json;
	}

	public static File getLastUploadedFile() {
	    File choice = null;
	    try {
	        File fl = new File(BOAppConstants.UPLOADED_FOLDER);
	        File[] files = fl.listFiles(new FileFilter() {
	            public boolean accept(File file) {
	                return file.isFile();
	            }
	        });
	        
	        long lastMod = Long.MIN_VALUE;

	        for (File file : files) {
	            if (file.lastModified() > lastMod) {
	                choice = file;
	                lastMod = file.lastModified();
	            }
	        }
	    } catch (Exception e) {
	       e.getMessage();
	    }	   	    
	    return choice;
	}
	
	
}
