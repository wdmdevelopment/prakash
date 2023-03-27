package com.wdm.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wdm.entity.UserAccount;
import com.wdm.exception.IdNotFoundException;
import com.wdm.repository.UserAccountRespository;
 
 

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserAccountRespository userRepo;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String email_Id) throws UsernameNotFoundException {
	  	
	  		
	 UserAccount user = userRepo.findByEmailId(email_Id);
	 
	 if(user==null) {
		 throw new IdNotFoundException("Email Id not found");
	 }
	 
	 
    
  
     
    return UserDetailsImpl.build(user);
  }

}
