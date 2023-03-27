package com.wdm.response;

import java.util.List;

public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private Long id;
  private String username;
  private String email;
  private String roles;

  
  
  
  

  public JwtResponse(String token, Long id, String username, String email, String roles) {
	super();
	this.token = token;
	this.type = type;
	this.id = id;
	this.username = username;
	this.email = email;
	this.roles = roles;
}

public String getAccessToken() {
    return token;
  }

  public void setAccessToken(String accessToken) {
    this.token = accessToken;
  }

  public String getTokenType() {
    return type;
  }

  public void setTokenType(String tokenType) {
    this.type = tokenType;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

public String getRoles() {
	return roles;
}

public void setRoles(String roles) {
	this.roles = roles;
}

  
}
