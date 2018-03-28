package com.bo.model;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.bo.dao.InsertDocumentApp;
import com.bo.parser.XMLtoJsonConverter;

public class ReadFile {
	
	public static void readFile() throws InterruptedException {
		FileInputStream fin=null;
		BufferedInputStream bis=null;
		int partCounter = 1,i=1;
		double kilobytes = (XMLtoJsonConverter.getLastUploadedFile().length() / 1024);
		System.out.println(kilobytes);
		int sizeOfFiles =1024*(int) kilobytes/10;// 1MB
		byte[] buffer = new byte[sizeOfFiles];
		Status saveStatus=new Status();
		try {
			 	fin=new FileInputStream(XMLtoJsonConverter.getLastUploadedFile());
			 	bis=new BufferedInputStream(fin);
			 	
			 	int bytesAmount = 0;
	            while ((bytesAmount = bis.read(buffer)) > 0) {
	                String filePartName = String.format("%s.%03d", XMLtoJsonConverter.getLastUploadedFile().getName(), partCounter++);
	                File newFile = new File(XMLtoJsonConverter.getLastUploadedFile().getParentFile(), filePartName);
	                try (FileOutputStream out = new FileOutputStream(newFile)) {
	                    out.write(buffer, 0, bytesAmount);
	                }
	                if(i==1) {
	                String result =10*i++ +"% ";
	                saveStatus.setFileName(filePartName);
	                saveStatus.setStatus(result);
	                InsertDocumentApp.save(saveStatus);
	                Thread.sleep(1000);
	                }else {
	                	String result =10*i++ +"% ";
		                saveStatus.setStatus(result);
		                InsertDocumentApp.update(saveStatus);
		                Thread.sleep(1000);
	                }
	            } 
	            InsertDocumentApp.saveJSON();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				fin.close();
				bis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
}
