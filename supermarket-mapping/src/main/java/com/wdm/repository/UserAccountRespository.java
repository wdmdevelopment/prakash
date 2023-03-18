package com.wdm.repository;
 

import org.springframework.data.jpa.repository.JpaRepository;

import com.wdm.entity.UserAccount;
 

public interface UserAccountRespository extends JpaRepository<UserAccount, Long>{
	
	public UserAccount findByEmailIdAndPassword(String emailId, String password);
	
	public UserAccount findByEmailId(String emailId);
	
	public UserAccount findByUserName(String userName);
	
	
	public boolean existsByUserName(String userName);
	
	public boolean existsByEmailId(String emailId);

}
