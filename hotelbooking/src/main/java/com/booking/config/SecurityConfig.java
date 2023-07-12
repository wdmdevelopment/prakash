package com.booking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import com.booking.security.jwt.AuthEntryPointJwt;
import com.booking.security.jwt.AuthTokenFilter;
import com.booking.security.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

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
	    public void configure(WebSecurity web) throws Exception {
	        web.ignoring().antMatchers("/swagger-ui.html");
	    }

		@Override
		protected void configure(HttpSecurity http) throws Exception {

			http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
					.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
					.antMatchers("/auth/user/**").permitAll()
					.antMatchers("/swagger-ui/**",
				    		  "/v2/api-docs/**",
				    		  "/v3/api-docs/**",
				              "/configuration/ui",
				              "/swagger-resources/**",
				              "/configuration/security",
				              "/swagger-ui.html",
				              "/webjars/**").permitAll()
					.anyRequest().authenticated();
			http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

		}

	 

}
