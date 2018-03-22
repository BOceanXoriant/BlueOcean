package com.bo.ctrls;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.ServletContext;
import javax.servlet.annotation.MultipartConfig;

import org.springframework.beans.factory.annotation.Autowired;

import com.bo.constant.BOAppConstants;
import com.bo.parser.XMLtoJsonConverter;

import net.sf.json.JSON;

@RestController
@MultipartConfig(maxFileSize = 10737418240L, maxRequestSize = 10737418240L, fileSizeThreshold = 52428800)
public class ExportCtrl {
	
	@Autowired
	ServletContext context;
    
	
	@RequestMapping(method=RequestMethod.GET,value="/exportjson",produces= {"application/json","application/xml"})
	public JSON convertJson() throws IOException {
		return XMLtoJsonConverter.convertJOSN();
	}
	
	      
      @RequestMapping(value="/uploadfile", method=RequestMethod.POST)
      public @ResponseBody String handleFileUpload( 
              @RequestParam("file") MultipartFile file){
              String originalFilename = file.getOriginalFilename();
  
          if (!file.isEmpty()) {
              try {
                  byte[] bytes = file.getBytes();
                  BufferedOutputStream stream = 
                          new BufferedOutputStream(new FileOutputStream(new File(BOAppConstants.UPLOADED_FOLDER+  BOAppConstants.Separtor + originalFilename)));
                  stream.write(bytes);
                  stream.close();
                  return BOAppConstants.successfulMsg + originalFilename + " into " + originalFilename + "-uploaded !";
              } catch (Exception e) {
                  return BOAppConstants.unSuccessfulMsg + originalFilename + " => " + e.getMessage();
              }
          } else {
              return BOAppConstants.unSuccessfulMsg + originalFilename + BOAppConstants.fileEmpty;
          }
      }

}
