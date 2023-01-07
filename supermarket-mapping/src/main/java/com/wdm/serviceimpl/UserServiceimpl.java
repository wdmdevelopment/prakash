package com.wdm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wdm.entity.UserAccount;
import com.wdm.model.RequestUserAccount;
import com.wdm.repository.UserAccountRespository;
import com.wdm.service.UserService;

@Service
public class UserServiceimpl implements UserService {

	@Autowired
	UserAccountRespository userRepo;
	
	public UserAccount saveuser(RequestUserAccount user) {
			
		UserAccount userAccount = new UserAccount();
		
		userAccount.setFirstName(user.getFirstName());
		
		userAccount.setLastName(user.getLastName());
		
		userAccount.setEmailId(user.getEmailId());
		
		userAccount.setPassword(user.getPassword());
		userAccount.setuserRoll(user.getUserRoll());
		
		
		
		return userRepo.save(userAccount);
	}

	
	public void delete(long id) {
		
		userRepo.deleteById(id);
		
	}

}
