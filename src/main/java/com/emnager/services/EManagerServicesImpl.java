package com.emnager.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.emnager.entity.UserFile;
import com.emnager.entity.Users;
import com.emnager.model.AuthenticationRequest;
import com.emnager.model.UserLoginDetails;
import com.emnager.repository.UserRepository;
import com.emnager.repository.UserfileRepository;
import com.emnager.security.JWTGenerator;

@Service
public class EManagerServicesImpl implements EManagerServices {

	@Value("${file.path}")
	private static String UPLOADED_FOLDER;

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private AuthenticationManager authenticationManager;
	private JWTGenerator jwtGenerator;
	private UserfileRepository fileRepository;

	@Autowired
	public EManagerServicesImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
			AuthenticationManager authenticationManager, JWTGenerator jwtGenerator, UserfileRepository fileRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.jwtGenerator = jwtGenerator;
		this.fileRepository = fileRepository;
	}

	@Override
	public UserLoginDetails validateUser(AuthenticationRequest authenticationRequest) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationRequest.getsUserName(), authenticationRequest.getsPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtGenerator.generateToken(authentication);
		Users user = userRepository.findByuserName(authenticationRequest.getsUserName())
				.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
		return new UserLoginDetails(user.getUserId(), user.getUserName(), user.getRoleType(), token);
	}

	@Override
	public List<Users> getAllUser() {
		return userRepository.findAll().stream().sorted((o1, o2) -> o1.getFirstName().compareTo(o2.getFirstName()))
				.collect(Collectors.toList());
	}

	@Override
	public Users createUser(Users users) {

		if (users.getUserId() == 0) {
			users.setPassword(passwordEncoder.encode(users.getPassword()));
			users.setRoleType("USER");
			users.setActive(true);
			users.setCreatedDate(LocalDate.now());
		}
		return userRepository.save(users);
	}

	@Override
	public Users getUser(int id) {
		return userRepository.findById(id).get();
	}

	@Override
	public void deleteUser(int id) {
		userRepository.deleteById(id);
	}

	@Override
	public List<UserFile> getAllFiles() {

		return fileRepository.findAll().stream().sorted((o1, o2) -> o1.getFileName().compareTo(o2.getFileName()))
				.collect(Collectors.toList());
	}

	@Override
	public UserFile createUserFile(MultipartFile[] userfiles) throws IOException {
		UserFile userFile = null;
		for (MultipartFile multipartFile : userfiles) {

			byte[] bytes = multipartFile.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + multipartFile.getOriginalFilename());

			Files.write(path, bytes);

			userFile = fileRepository.save(new UserFile(multipartFile.getOriginalFilename(), multipartFile.getSize(),
					LocalDate.now(), path.toString()));
		}

		return userFile;
	}

	@Override
	public void deleteFile(int id) throws IOException {

		UserFile userFile = fileRepository.findById(id).get();
		if (userFile != null) {
			Files.delete(Paths.get(userFile.getFilePath()));
		}
		fileRepository.delete(userFile);
	}

	@Override
	public UserFile downloadFile(int id) throws IOException {
		return fileRepository.findById(id).get();
	}

}
