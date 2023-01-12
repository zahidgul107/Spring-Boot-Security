package com.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
	/*	auth.inMemoryAuthentication()
		.withUser("riyaz")
		.password("riyaz")
		.roles("USER")
		.and()
		.withUser("zahid")
		.password("zahid")
		.roles("ADMIN");		*/
		
	/*	auth.jdbcAuthentication()	
				.dataSource(dataSource)
				.withDefaultSchema()
				.withUser(
						User.withUsername("user")
						.password("pass")
						.roles("USER")
				)
				.withUser(
						User.withUsername("admin")
						.password("pass")
						.roles("ADMIN")
						);			*/
				
			auth.userDetailsService(userDetailsService);	
		
		
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		//		.antMatchers("/", "static/css", "static/js").permitAll()
				
				.antMatchers("/admin").hasRole("ADMIN")
				.antMatchers("/user").hasAnyRole("USER", "ADMIN")
				.antMatchers("/").permitAll()
				.and().formLogin();
	}
}
