package com.wdm.service;

import org.springframework.stereotype.Service;

import com.wdm.entity.UserAccount;
import com.wdm.model.RequestUserAccount;

@Service
public interface UserService {

	public UserAccount saveuser(RequestUserAccount user);

	public void delete(long id);
	
	public UserAccount updateUser(RequestUserAccount user, long id);

}
