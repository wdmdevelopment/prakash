package com.wdm.repository;

 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wdm.entity.UserAccount;
 

public interface UserAccountRespository extends JpaRepository<UserAccount, Long>{
	
	public UserAccount findByEmailIdAndPassword(String emailId, String password);
	
	

}
