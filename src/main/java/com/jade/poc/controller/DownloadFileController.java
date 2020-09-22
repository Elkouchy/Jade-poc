package com.jade.poc.controller;

import java.io.ByteArrayOutputStream;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.jade.poc.service.S3Services;


@RestController

public class DownloadFileController {
 	
	@Autowired
	S3Services s3Services;

	@GetMapping("/api/file/{keyname}")
	public ResponseEntity<byte[]> downloadFile(@PathVariable String keyname) throws Exception{
		try {
			ByteArrayOutputStream downloadInputStream = s3Services.downloadFile(keyname) ;

			return ResponseEntity.ok()
					.contentType(contentType(keyname))
					.header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + keyname + "\"")
					.body(downloadInputStream.toByteArray());

		}catch (Exception ex){
			System.out.println("File note found");
			return null;
		}

	}
	
	private MediaType contentType(String keyname) {
		String[] arr = keyname.split("\\.");
		String type = arr[arr.length-1];
		switch(type) {
			case "txt": return MediaType.TEXT_PLAIN;
			case "png": return MediaType.IMAGE_PNG;
			case "jpg": return MediaType.IMAGE_JPEG;
			default: return MediaType.APPLICATION_OCTET_STREAM;
		}
	}
}
