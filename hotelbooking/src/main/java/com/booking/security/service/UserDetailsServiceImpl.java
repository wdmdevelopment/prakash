package com.booking.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.booking.entity.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	com.booking.repository.UserRepository userRepo;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {

		User user = userRepo.findByEmailIdIgnoreCase(emailId);

		if (user == null) {

			throw new UsernameNotFoundException(emailId + " Not found");
		}

		return UserDetailsImpl.build(user);
	}

}
