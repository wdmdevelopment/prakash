package com.wdm.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wdm.entity.Orders;
import com.wdm.entity.UserAccount;
import com.wdm.exception.IdNotFoundException;
import com.wdm.exception.ProductCustomException;
import com.wdm.exception.UserNotFoundException;
import com.wdm.model.RequestUserAccount;
import com.wdm.repository.OrderRepository;
import com.wdm.repository.UserAccountRespository;
import com.wdm.response.UserResponse;
import com.wdm.service.UserService;

@Service
public class UserServiceimpl implements UserService {

	@Autowired
	UserAccountRespository userRepo;
	
	@Autowired
	OrderRepository orderRepo;

	public UserAccount saveuser(RequestUserAccount user) {
		try {
			
		UserAccount userAccount = new UserAccount();

		userAccount.setFirstName(user.getFirstName());

		userAccount.setLastName(user.getLastName());

		userAccount.setEmailId(user.getEmailId());

		userAccount.setPassword(user.getPassword());
		
		userAccount.setuserRoll(user.getUserRoll());

		return userRepo.save(userAccount);
		}
		catch (Exception e) {
			throw new UserNotFoundException("Please Enter the valid input");
		}
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

	public UserAccount updateUser(RequestUserAccount user, long id) {

		UserAccount account = userRepo.findById(id).orElseThrow(() -> new IdNotFoundException("user id not found"));
		
		account.setEmailId(user.getEmailId());
		account.setFirstName(user.getFirstName());
		account.setLastName(user.getLastName());
		account.setPassword(user.getPassword());
		account.setuserRoll(user.getUserRoll());
		
			Orders orders = orderRepo.findById(user.getOrderId()).orElseThrow(() -> new IdNotFoundException("order Id not found"));
			
			account.setOrder((List<Orders>) orders);
		
		return userRepo.save(account);
	}

	 
	public List<UserAccount> getAlluser() {
		 
		return userRepo.findAll();
	}

	 
	public UserResponse getuserId(long id) {
		UserAccount user;
		try { 
			
			user = userRepo.findById(id).get();
			
		
		}
		catch (UserNotFoundException notfoundException) {
			 throw new UserNotFoundException("Not found "+ notfoundException.getMessage());
		}
		
		UserResponse userRes = new UserResponse();
		userRes.setFirstName(user.getFirstName());
		userRes.setUserRole(user.getuserRoll());
		return userRes;
	}
	
	public UserAccount getuserbyEmail(String email, String password) throws Exception {
		
		UserAccount user = userRepo.findByEmailIdAndPassword(email, password);
		
		if(user==null) {
			throw new UserNotFoundException("Your Email and password is incorrect");
		}
		return user;
	}
		

	

}
