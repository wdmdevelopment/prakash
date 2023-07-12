package com.wdm.repository;
 

import org.springframework.data.jpa.repository.JpaRepository;

import com.wdm.entity.UserAccount;
 

public interface IUserAccountRespository extends JpaRepository<UserAccount, Long>{
	
	
	
	public UserAccount findByEmailIdIgnoreCase(String emailId);
	
	public UserAccount findByUserName(String userName);
	
	
	public boolean existsByUserName(String userName);
	
	public boolean existsByEmailIdIgnoreCase(String emailId);

}
