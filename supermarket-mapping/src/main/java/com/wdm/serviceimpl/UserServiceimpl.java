package com.wdm.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wdm.entity.UserAccount;
import com.wdm.exception.IdNotFoundException;
import com.wdm.exception.UserNotFoundException;
import com.wdm.model.RequestUserAccount;
import com.wdm.repository.UserAccountRespository;
import com.wdm.response.UserResponse;
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

	public void delete(long id) throws Exception {
		try {
			userRepo.deleteById(id);	
		}
		catch (IdNotFoundException e) {
			throw new IdNotFoundException("Id not found"+e);
			
		}
		catch (Exception e) {
			throw new Exception(e);
		}

	}

	public UserAccount updateUser(UserAccount user, long id) {

		return userRepo.save(user);
	}

	 
	public List<UserAccount> getAlluser() {
		 
		return userRepo.findAll();
	}

	 
	public UserResponse getuserId(long id) {
		UserAccount user;
		try { 
			
			user = userRepo.findById(id).get();
			
		
		}
		catch (Exception notfoundException) {
			 throw new UserNotFoundException("Not found"+ notfoundException);
		}
		
		UserResponse userRes = new UserResponse();
		userRes.setFirstName(user.getFirstName());
		userRes.setUserRole(user.getuserRoll());
		return userRes;
	}

}
