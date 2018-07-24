package com.eab.springsecurity.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		// add users for in memory authentication
		UserBuilder users = User.withDefaultPasswordEncoder();
		auth.inMemoryAuthentication()
		.withUser(users.username("anj").password("test").roles("ADMIN"))
		.withUser(users.username("anoj").password("test").roles("MANAGER"))
		.withUser(users.username("asw").password("test").roles("ADMIN"))
		.withUser(users.username("eab").password("test").roles("EMPLOYEE"));
		
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// add login
		 http.authorizeRequests()
		 	.anyRequest().authenticated()
		 	.and()
		 	.formLogin()
		 		.loginPage("/showMyFancyLoginPage")
		 		.loginProcessingUrl("/authenticateTheUser")
		 		.permitAll()
		 		
		 		// add logout 
	 		.and()
	 		.logout().permitAll();
		 		
		super.configure(http);
	}
	
	

}