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
		UserFile usr = eManagerServices.downloadFile(fileId);
		Resource resource = new ClassPathResource(usr.getFilePath());
		byte[] dataArr = FileCopyUtils.copyToByteArray(resource.getInputStream());
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"wallpaperflare.com_wallpaper.jpg\"");
		return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/octet-stream"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(dataArr);
	}
}
