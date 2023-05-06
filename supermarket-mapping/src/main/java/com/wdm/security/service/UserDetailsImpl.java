package com.wdm.security.service;

import java.util.Collection; 
import java.util.Objects; 

import org.springframework.security.core.GrantedAuthority; 
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wdm.entity.UserAccount;
 

public class UserDetailsImpl implements UserDetails {
	
  private static final long serialVersionUID = 1L;

  private Long id;

  private String username;

  private String email;

  @JsonIgnore
  private String password;
  
  private String userRole;

  private Collection<? extends GrantedAuthority> authorities;

  public UserDetailsImpl(Long id, String username, String email, String password, String userRole) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.setUserRole(userRole);
    
  }

  public static UserDetailsImpl build(UserAccount user) {
     
	
	  
    return new UserDetailsImpl(
        user.getUserId(), 
        user.getUserName(), 
        user.getEmailId(),
        user.getPassword(),
        user.getUserRole()
        );
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  public Long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
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

public String getUserRole() {
	return userRole;
}

public void setUserRole(String userRole) {
	this.userRole = userRole;
}

 
}
