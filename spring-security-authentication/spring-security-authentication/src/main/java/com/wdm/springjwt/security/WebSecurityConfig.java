package com.wdm.springjwt.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider; 
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.wdm.springjwt.security.jwt.AuthEntryPointJwt;
import com.wdm.springjwt.security.jwt.AuthTokenFilter;
import com.wdm.springjwt.security.services.UserDetailsServiceImpl;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter { 
  @Autowired
  UserDetailsServiceImpl userDetailsService;

  @Autowired
  private AuthEntryPointJwt unauthorizedHandler;

  @Bean
  public AuthTokenFilter authenticationJwtTokenFilter() {
    return new AuthTokenFilter();
  }

  
  

@Bean
  public DaoAuthenticationProvider authenticationProvider() {
      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
       
      authProvider.setUserDetailsService(userDetailsService);
      authProvider.setPasswordEncoder(passwordEncoder());
   
      return authProvider;
  }

  
  
  
  
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
    return authConfig.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  
  
  
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
				.authorizeRequests()
				 .antMatchers("/api/auth/signup", "/api/auth/signin").permitAll()
				 .anyRequest().authenticated();
	}



	
//	@Bean
//	  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//	    http.cors().and().csrf().disable()
//	        .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
//	        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//	        .authorizeRequests().antMatchers("/api/auth/**").permitAll()
//	        .antMatchers("/api/test/mod").permitAll()
//	       	.anyRequest().authenticated();
//	    
//	    http.authenticationProvider(authenticationProvider());
//	
//	    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//	    
//	    return http.build();
//	  }
	
	
	
	}
