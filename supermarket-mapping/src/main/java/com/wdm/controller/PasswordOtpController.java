package com.wdm.controller;

import java.security.SecureRandom;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wdm.entity.PasswordResetOtp;
import com.wdm.entity.UserAccount;
import com.wdm.exception.ProductCustomException;
import com.wdm.repository.OtpRepository;
import com.wdm.repository.UserAccountRespository;
import com.wdm.response.MessageResponse;
 

@RestController
@RequestMapping("/forget-password")
@CrossOrigin
public class PasswordOtpController {

	@Autowired
	OtpRepository otpRepo;

	@Autowired
	UserAccountRespository UserRepo;

	@Autowired
	JavaMailSender javaMailSender;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@PostMapping
	public ResponseEntity<Object> findByEmail(@Valid @RequestParam("email") String email) {
			
			
		 
		System.out.println("---48-----");
		
		System.out.println("========    "+email);
		
		try {
		UserAccount userAccount = UserRepo.findByEmailId(email);
		
			
		
		if (userAccount != null) {

			String otp = generateOTP();
			
				System.out.println(otp);

			PasswordResetOtp passwordOtp = new PasswordResetOtp();
			
			System.out.println(email);
			 
			passwordOtp.setEmail(email); 
			passwordOtp.setOneTimePassword(otp);
			passwordOtp.setExpiryTime(LocalDateTime.now());
			
		 

			otpRepo.save(passwordOtp);

			sendOtpByEmail(userAccount.getEmailId(), otp);
			System.out.println("===============");
			
		}	
		else {
				throw new ProductCustomException("Email id not found");
		}
		}
		catch (Exception e) {
			
			throw new ProductCustomException(e.getMessage()); 
		}
		 

		 return ResponseEntity.ok(new MessageResponse("otp send your email id..!"));

	}

	private String generateOTP() {
		SecureRandom random = new SecureRandom();
		return String.format("%06d", random.nextInt(999999));
	}

	private void sendOtpByEmail(String email, String otp) {
		
		System.out.println("------88-----");
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("prakashgeevi19998@gmail.com"); 
		message.setTo(email);
		message.setSubject("Reset Password OTP");
		message.setText("Your OTP is " + otp);
		javaMailSender.send(message);

	}

	@PostMapping("/otp")
	public ResponseEntity<Object> getOneTimePassword(@RequestParam("otp") String otp,
			@RequestParam("email") String email) {

		 

		PasswordResetOtp resetOtp = otpRepo.findByOneTimePasswordAndEmail(otp, email);
		
		
		if(resetOtp == null) {
			throw new ProductCustomException("InValid Otp");
		}
		
		LocalDateTime createdAt = resetOtp.getExpiryTime();
	    LocalDateTime now = LocalDateTime.now();
	    long minutes = ChronoUnit.MINUTES.between(createdAt, now);
	    
	    System.out.println(minutes);
	    
	    if (minutes > 10) {
	        return ResponseEntity.badRequest().body("OTP has expired");
	    }
		
		 
	    return ResponseEntity.ok(new MessageResponse("Otp is Valid..!"));
	}
	
	
		@PostMapping("/reset-password")
		public ResponseEntity<Object> getResetPassword(@RequestParam("email") String email,
				@RequestParam("password") String resetPassword
				){
			
			UserAccount userAccount = UserRepo.findByEmailId(email);
			
			   
				userAccount.setPassword(passwordEncoder.encode(resetPassword));
				
				UserRepo.save(userAccount);
				  
				 return ResponseEntity.ok(new MessageResponse("New password changed..!"));
			
		}
		
		
		
		
		
		
		
		
		
		

}
