package com.emnager.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.emnager.entity.UserFile;
import com.emnager.entity.Users;
import com.emnager.services.EManagerServices;

@CrossOrigin
@RestController
public class Controller {

	private EManagerServices eManagerServices;

	@Autowired
	public Controller(EManagerServices eManagerServices) {
		this.eManagerServices = eManagerServices;
	}

	@GetMapping("getAllUsers")
	public ResponseEntity<Object> getAllUsers() {
		return new ResponseEntity<>(eManagerServices.getAllUser(), HttpStatus.OK);
	}

	@PostMapping("createUser")
	public ResponseEntity<Object> createUsers(@RequestBody Users users) {
		return new ResponseEntity<>(eManagerServices.createUser(users), HttpStatus.OK);
	}

	@GetMapping("getUser")
	public ResponseEntity<Object> getUser(@RequestParam int userId) {
		return new ResponseEntity<>(eManagerServices.getUser(userId), HttpStatus.OK);
	}

	@DeleteMapping("deleteUser")
	public ResponseEntity<Object> deleteUser(@RequestParam int userId) {
		eManagerServices.deleteUser(userId);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	@GetMapping("getAllFiles")
	public ResponseEntity<Object> getAllFiles() {
		return new ResponseEntity<>(eManagerServices.getAllFiles(), HttpStatus.OK);
	}

	@PostMapping("uploadfile")
	public ResponseEntity<Object> uploadMultipleFiles(MultipartFile[] userfiles) throws IOException {

		return new ResponseEntity<>(eManagerServices.createUserFile(userfiles), HttpStatus.OK);
	}

	@DeleteMapping("deleteFile")
	public ResponseEntity<Object> deleteFile(@RequestParam int fileId) throws IOException {
		eManagerServices.deleteFile(fileId);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	@GetMapping("downloadFile")
	public ResponseEntity<Object> createUsers(@RequestParam int fileId) throws IOException {
		
		Resource fileResource=null;
		try {
			fileResource=eManagerServices.downloadFile(fileId);
		}catch (IOException e) {
			return ResponseEntity.internalServerError().build();
		}
		
		if(fileResource==null) {
			return new ResponseEntity<>("File Not Found",HttpStatus.NOT_FOUND);
		}
		
		String userFileName=eManagerServices.getUserFile(fileId).getFileName();
		
		
		String contenttype="application/octet-stream";
		
		String sExt = userFileName.substring(userFileName.lastIndexOf("."));
		
		sExt = sExt.replace(".", "");
		if (sExt.equalsIgnoreCase("png"))
			contenttype="image/png";
		if (sExt.equalsIgnoreCase("jpg"))
			contenttype="image/pjpeg";
		if (sExt.equalsIgnoreCase("jpeg"))
			contenttype="image/jpeg";
		if (sExt.equalsIgnoreCase("tif"))
			contenttype="image/tif";
		if (sExt.equalsIgnoreCase("tiff"))
			contenttype="image/tiff";
		if (sExt.equalsIgnoreCase("bmp"))
			contenttype="image/bmp";
		if (sExt.equalsIgnoreCase("gif"))
			contenttype="image/gif";
		if (sExt.equalsIgnoreCase("pdf"))
			contenttype="application/pdf";
		if (sExt.equalsIgnoreCase("doc"))
			contenttype="application/msword";
		if (sExt.equalsIgnoreCase("docx"))
			contenttype="application/msword";
		if (sExt.equalsIgnoreCase("rtf"))
			contenttype="application/rtf";
		if (sExt.equalsIgnoreCase("xls"))
			contenttype="application/vnd.ms-excel";
		if (sExt.equalsIgnoreCase("txt"))
			contenttype="text/plain";
		
		
		String headerValue="attachment; filename=\"" + fileResource.getFilename() + "\"";
		
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contenttype)).
				header(HttpHeaders.CONTENT_DISPOSITION,headerValue).body(fileResource);
		
	}
}
