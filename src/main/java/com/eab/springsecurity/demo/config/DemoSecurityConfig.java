package com.eab.springsecurity.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	// add a ref to the security datasource created
	@Autowired
	private DataSource securityDataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		/*// not using in memory authentication
		// add users for in memory authentication
		UserBuilder users = User.withDefaultPasswordEncoder();
		auth.inMemoryAuthentication()
				.withUser(users.username("anj").password("test").roles("EMPLOYEE", "MANAGER"))
				.withUser(users.username("anoj").password("test").roles("EMPLOYEE", "ADMIN"))
				.withUser(users.username("asw").password("test").roles("EMPLOYEE", "MANAGER", "ADMIN"))
				.withUser(users.username("eab").password("test").roles("EMPLOYEE"));*/
		
		// using jdbc authentication
		auth.jdbcAuthentication().dataSource(securityDataSource); 
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// add login
		http.authorizeRequests()
		
		/*
			.anyRequest().authenticated() // need to restrict access based on user role, commenting this 
			*/
		
		// access base on role
			.antMatchers("/").hasRole("EMPLOYEE")
			.antMatchers("/leader/**").hasRole("MANAGER")
			.antMatchers("/admin/**").hasRole("ADMIN")
			.and()
			.formLogin().loginPage("/showMyFancyLoginPage")
				.loginProcessingUrl("/authenticateTheUser").permitAll()

				// add logout
				.and().logout().permitAll()
				.and()
				.exceptionHandling().accessDeniedPage("/access-denied");
	}

}
