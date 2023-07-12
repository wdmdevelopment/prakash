package com.booking.service;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.booking.constant.Role;
import com.booking.constant.UserStatus;
import com.booking.dto.LoginDTO;
import com.booking.dto.LoginResponseDTO;
import com.booking.dto.UserDTO;
import com.booking.entity.User;
import com.booking.repository.UserRepository;
import com.booking.security.jwt.JwtUtils;
import com.booking.security.service.UserDetailsImpl;
import com.booking.utils.ImageUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserService {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepo;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtUtils jwtutils;

	@Autowired
	ObjectMapper objectMapper;

	@Value("${file.upload-profile-dir}")
	private String fileUploadProfileDir;

	public User registerUser(String user, MultipartFile file) throws Exception
			 {

		try { 
		UserDTO userDTO = objectMapper.readValue(user, UserDTO.class);

		if (userRepo.existsByEmailIdIgnoreCase(userDTO.getEmailId())) {

			throw new NotFoundException();
		}
		
		User createUser = new User();
		createUser.setUserName(userDTO.getUserName());
		createUser.setEmailId(userDTO.getEmailId());
	 
		createUser.setFirstName(userDTO.getFirstName());
		createUser.setLastName(userDTO.getLastName());
		
		createUser.setStatus(UserStatus.PENDING.name());
		
		createUser.setRole(Role.ROLE_ADMIN.name());
	
		 String fileName = ImageUtils.uploadFile(file, fileUploadProfileDir);

 		if (fileName != null) {
 			//userDTO.setProfilePicture(fileUploadProfileDir + File.separator + fileName);
 			createUser.setPicture(fileUploadProfileDir + File.separator + fileName);
 		}
		createUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
 
		 
		return userRepo.save(createUser);
		}
		catch (Exception e) {
			 throw new Exception(e.getMessage());
		}
		 
		 
	}

	public LoginResponseDTO login(LoginDTO loginDTO) throws Exception {
		try {

			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);

			String jwt = jwtutils.generateJwtToken(authentication);

			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

			return new LoginResponseDTO(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getFirstName(),
					userDetails.getLastName(), userDetails.getPicture(),
					userDetails.getUsername(),
					userDetails.getAuthorities().stream().map(e -> e.getAuthority()).collect(Collectors.toList()), userDetails.getStatus());

		} catch (Exception e) {

			throw new 	Exception(e.getMessage());
		}

	}

}
