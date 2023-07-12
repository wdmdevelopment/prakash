package com.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

	public User findByEmailIdIgnoreCase(String emailId);

	public boolean existsByEmailIdIgnoreCase(String emailId);

}
