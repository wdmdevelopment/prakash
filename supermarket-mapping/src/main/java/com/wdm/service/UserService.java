package com.wdm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wdm.entity.UserAccount;
import com.wdm.model.RequestUserAccount;
import com.wdm.response.UserResponse;

@Service
public interface UserService {

	public UserAccount saveuser(RequestUserAccount user);

	public void delete(long id) throws Exception;
	
	public UserAccount updateUser(UserAccount user, long id);
	
	public List<UserAccount> getAlluser();
	
	public UserResponse getuserId(long id);

}
