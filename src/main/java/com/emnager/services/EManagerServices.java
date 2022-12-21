package com.emnager.services;

import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.emnager.entity.UserFile;
import com.emnager.entity.Users;
import com.emnager.model.AuthenticationRequest;
import com.emnager.model.UserLoginDetails;

public interface EManagerServices {

	public UserLoginDetails validateUser(AuthenticationRequest authenticationRequest);

	public List<Users> getAllUser();

	public Users createUser(Users users);

	public Users getUser(int id);

	public void deleteUser(int id);

	public List<UserFile> getAllFiles();

	public UserFile createUserFile(MultipartFile[] userfiles) throws IOException;

	public void deleteFile(int id) throws IOException;

	public UserFile downloadFile(int id) throws IOException;
}
