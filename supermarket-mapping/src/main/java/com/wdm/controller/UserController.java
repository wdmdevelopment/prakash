package com.wdm.controller;

 
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wdm.entity.UserAccount;
import com.wdm.exception.IdNotFoundException;
import com.wdm.model.RequestLogin;
import com.wdm.model.RequestSocialLogin;
import com.wdm.model.RequestUserAccount;
import com.wdm.repository.UserAccountRespository;
import com.wdm.response.JwtResponse;
import com.wdm.response.MessageResponse;
import com.wdm.response.UserResponse;
import com.wdm.security.jwt.JwtUtils;
import com.wdm.security.service.UserDetailsImpl;
import com.wdm.service.UserService;
 

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserAccountRespository userRepo;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtUtils jwtutils;

	private static final Logger logger = LogManager.getLogger(UserController.class);

	@PostMapping("/create")

	public ResponseEntity<UserAccount> saveuser(@Valid @RequestBody RequestUserAccount resquestProduct) {

		logger.info("To create account by user/customer");

		return new ResponseEntity<>(userService.saveuser(resquestProduct), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<UserAccount>> getAll() {
		logger.info("To get all by user/customer");
		return new ResponseEntity<List<UserAccount>>(userService.getAlluser(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> getByid(@PathVariable("id") long id) {
		UserResponse user = userService.getuserId(id);
		logger.info("To get  by id user/customer" + id);
		if (user == null) {

			logger.info("if user id is null" + id);

			throw new IdNotFoundException("Id not found" + user);
		}

		return new ResponseEntity<UserResponse>(userService.getuserId(id), HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteproduct(@PathVariable("id") long id) throws Exception {
		logger.info("user account will delete" + id);
		userService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

	}
	
	
	@PostMapping("/signup")
	  public UserAccount  registerUser(@Valid @RequestBody RequestUserAccount signUpRequest) {
	   
		System.out.println("-----105--------------");
			
		System.out.println(signUpRequest.getPassword());
		
		if (userRepo.existsByUserName(signUpRequest.getUserName())) {
			
			throw new IdNotFoundException("Error: Username is already taken!");
			
//			return ResponseEntity
//	          .badRequest()
//	          .body(new MessageResponse("Error: Username is already taken!"));
	    }

	    if (userRepo.existsByEmailId(signUpRequest.getEmailId())) {
	     
	    	throw new IdNotFoundException("Error: Email is already in use!");
	    	
//	    	return ResponseEntity
//	          .badRequest()
//	          .body(new MessageResponse("Error: Email is already in use!"));
	    }
	  
	    logger.info("User create success username={}",signUpRequest.getUserName() );
	    
	    
	   
	    UserAccount user = new UserAccount();
	    
	    user.setEmailId(signUpRequest.getEmailId());
	    
	    user.setFirstName(signUpRequest.getFirstName());
	    user.setLastName(signUpRequest.getLastName());
	    user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
	    user.setUserName(signUpRequest.getUserName());
	    user.setUserRole(signUpRequest.getUserRoll());
	    
	    System.out.println("---------135----------");
 
	    

	    return 	userRepo.save(user);
	    
 
	  }
	
	 

	@PostMapping("/signin")	
	public ResponseEntity<?> loggingValidation(@RequestBody RequestLogin requst) throws Exception{
		try {	
		 
		
		 	System.out.println(requst.getUserName());
		
		 Authentication authentication = authenticationManager.authenticate(
			        new UsernamePasswordAuthenticationToken(requst.getUserName(), requst.getPassword()));
		 	
		 	 
		
		 SecurityContextHolder.getContext().setAuthentication(authentication);
		 
		    String jwt = jwtutils.generateJwtToken(authentication);
		    
		    System.out.println(jwt);
		    
		    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		    
		 
		    
	
	return ResponseEntity.ok(new JwtResponse(jwt, 
            userDetails.getId(), 
            userDetails.getUsername(), 
            userDetails.getEmail(),
            userDetails.getUserRole()
            ));
	
	
		}catch (Exception e) {
			 
			throw new IdNotFoundException(e.getMessage());
		}

		 
		 
	}
	
	
	@PostMapping("/socialLogin")
	public ResponseEntity<?> socialLogin (@RequestBody RequestSocialLogin socialLogin) throws Exception {
			
		 
		UserAccount userAccount = userRepo.findByEmailId(socialLogin.getEmail());
		
	  
		
		
		System.out.println(socialLogin.getPassword());
			
		if(userAccount!=null) {
			 
			String jwt = jwtutils.generateTokenSocial(socialLogin.getUserName(), socialLogin.getEmail());
			
			  System.out.println(jwt);
			  

				return ResponseEntity.ok(new JwtResponse(jwt, 
						userAccount.getUserId(), 
						userAccount.getUserName(), 
						userAccount.getEmailId(),
						userAccount.getUserRole()
			            ));
		
		}
		
		else {
			RequestUserAccount user = new RequestUserAccount();
				
			System.out.println("-------------  "+socialLogin.getPassword());
			
			
			  user.setEmailId(socialLogin.getEmail());
			  user.setFirstName(socialLogin.getFirstName());
			  user.setLastName(socialLogin.getLastName());
			  user.setPassword(passwordEncoder.encode(socialLogin.getPassword()));
			  user.setUserName(socialLogin.getUserName());
			  user.setUserRoll("Customer");
			  
			  		 UserAccount registerUser = registerUser(user);
			  	
			  		String jwt = jwtutils.generateTokenSocial(socialLogin.getUserName(), socialLogin.getEmail());
					
					  System.out.println(jwt);
					  

						return ResponseEntity.ok(new JwtResponse(jwt, 
								registerUser.getUserId(), 
								registerUser.getUserName(), 
								registerUser.getEmailId(),
								registerUser.getUserRole()
					            ));
					
		      
 
		}
	 
	 
	}
	
	
	 
	
	
	
	
	
	
	
	
	
	

}
