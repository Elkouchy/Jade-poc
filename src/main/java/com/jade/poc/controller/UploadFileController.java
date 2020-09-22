package com.jade.poc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.jade.poc.service.S3Services;


@RestController
public class UploadFileController {
	
	@Autowired
	S3Services s3Services;
	
    @PostMapping("/api/file/upload")
    public String uploadMultipartFile(@RequestParam("keyname") String keyName, @RequestParam("uploadfile") MultipartFile file) {
		s3Services.uploadFile(keyName, file);
		return "Upload Successfully. -> KeyName = " + keyName;
    }

}

