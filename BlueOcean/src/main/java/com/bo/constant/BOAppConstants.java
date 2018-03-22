package com.bo.constant;

import java.io.File;

public class BOAppConstants {
	 
	public static final String UPLOADED_FOLDER = "D:\\upload";
	public static final String Separtor=File.separator;
	
	public static final String successfulMsg="You successfully uploaded";
	public static final String unSuccessfulMsg="You failed to upload";
	public static final String fileEmpty="because the file was empty.";
	
	//file size constants
	public static int maxUploadSizeInMb = 5 * 1024 * 1024; // 5 MB
	
	
}
