package com.wdm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wdm.entity.PasswordResetOtp;

@Repository
public interface OtpRepository extends JpaRepository<PasswordResetOtp, Long> {
	
	public PasswordResetOtp findByOneTimePasswordAndEmail(String otp, String email);

}
