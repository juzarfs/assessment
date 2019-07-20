package com.assessment.webservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

import com.assessment.webservice.security.UserDetailsServiceImpl;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public static DefaultRolesPrefixPostProcessor defaultRolesPrefixPostProcessor() {
		return new DefaultRolesPrefixPostProcessor();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.addFilterBefore(authFilter(),RequestHeaderAuthenticationFilter.class)
		.authenticationProvider(preauthAuthenticationProvider())
		.authorizeRequests()
		.antMatchers("/actuator/**").permitAll()
		.anyRequest().authenticated()
		.and().csrf().disable();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// this funtion register the preauthAuthenicationProvider with Spring
		auth.authenticationProvider(preauthAuthenticationProvider());
	}
	
	@Bean
	public RequestHeaderAuthenticationFilter authFilter() throws Exception {
		RequestHeaderAuthenticationFilter filter = new RequestHeaderAuthenticationFilter();
		filter.setExceptionIfHeaderMissing(false);
		//filter.setPrincipalRequestHeader("SM_USER");
		filter.setAuthenticationManager(authenticationManager());
		return filter;
	}
	
	@Bean 
	PreAuthenticatedAuthenticationProvider preauthAuthenticationProvider() {
		PreAuthenticatedAuthenticationProvider preauthAuthProvider = new PreAuthenticatedAuthenticationProvider();
		preauthAuthProvider.setPreAuthenticatedUserDetailsService(userDetailsServiceWrapper());
		return preauthAuthProvider;
	}
	
	@Bean
	public UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken> userDetailsServiceWrapper() {
		UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken> wrapper = new UserDetailsByNameServiceWrapper<>();
		wrapper.setUserDetailsService(userDetailsService());
		return wrapper;
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}
	
}
