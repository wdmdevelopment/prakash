package com.booking.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.booking.entity.User;

 
 
public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String id;

	private String firstName;

	private String lastName;

	private String email;

	private String password;

	private String picture;
	
	private String userName;
	
	private String status;
	 
	private Collection<? extends GrantedAuthority> authorities;


	public UserDetailsImpl(String id, String firstName, String lastName, String email, String password, String picture,
			Collection<? extends GrantedAuthority> authorities, String userName, String status) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.picture = picture;
		this.authorities = authorities;
		this.setUserName(userName);
		this.setStatus(status);
	}

	 

	public static UserDetailsImpl build(User user) {

		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.getRole()));
		

		return new UserDetailsImpl(user.getUserId(), user.getFirstName(), user.getLastName(), user.getEmailId(),
				user.getPassword(), user.getPicture(), authorities, user.getUserName(), user.getStatus()
				);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	 
		 
		return authorities;
	}

	public String getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}

 

 

}
