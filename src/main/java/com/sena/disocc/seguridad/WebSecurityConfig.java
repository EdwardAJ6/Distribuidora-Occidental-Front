package com.sena.disocc.seguridad;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.sena.disocc.service.CustomUserDetailService;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailService();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userDetailsService());
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
		
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/Dashboard/admin").hasAuthority("Admin")
		.antMatchers("/Dashboard/usuario").hasAnyAuthority("User")
	
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.successHandler(successHnadler)
		.loginPage("/login")
		.usernameParameter("email")
		.permitAll()
		.and()
		.logout()
		.logoutSuccessUrl("/login")
		.permitAll();
		
	}
	
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/js/**","/css/**", "/registro**","/scss/**","/vendor/**","/procesar**","/error**"
				,"/img/**","/cuatro**","cuatro**");
	}
	
	@Autowired
	private LogginSuccessHnadler successHnadler;
}
