package com.wdm.repository;
 
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wdm.entity.PasswordResetOtp;

 

@Repository
public interface IOtpRepository extends JpaRepository<PasswordResetOtp, Long> {
	
	public PasswordResetOtp findByOneTimePasswordAndEmailAndExpiryTime(String otp, String email, LocalDateTime expiryTime);
	
	public List<PasswordResetOtp> findByEmail(String email);
	
 //	public PasswordResetOtp findLastOTPByEmailAndExpiryTimeDesc(String email, LocalDateTime expiryTime);

}
