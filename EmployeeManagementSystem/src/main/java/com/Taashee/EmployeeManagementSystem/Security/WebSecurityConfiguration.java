package com.Taashee.EmployeeManagementSystem.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final PasswordEncoder passwordEncoder;
	private final DaoUserDetailsService daoUserDetailsService;
	
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private final jwtRequestFilter jwtRequestFilter;
	@Autowired
	private CustomSuccessHandler successController;
	@Autowired
	public WebSecurityConfiguration(PasswordEncoder passwordEncoder, DaoUserDetailsService daoUserDetailsService,JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, jwtRequestFilter jwtRequestFilter) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.daoUserDetailsService = daoUserDetailsService;
		this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
		this.jwtRequestFilter = jwtRequestFilter;
	}
	@Bean		
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception
	{
		return super.authenticationManagerBean();
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.cors();
		http.csrf().disable()
		.authorizeRequests()
		
		.antMatchers("/authenticate","/register","index","/css/*").permitAll()
		//.antMatchers(").permitAll()
		//.antMatchers(HttpMethod.POST,"/**").hasAuthority(UserPermissions.WRITE.getPermission())
	    // .antMatchers(HttpMethod.GET,"/**").hasAuthority(UserPermissions.READ.getPermission())
		.anyRequest()
		.authenticated()
	
		.and()
		.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		
		
		//.defaultSuccessUrl("/", true).permitAll()
		
	}

	
	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception { // TODO Auto-generated method stub
	 * auth.authenticationProvider(daoAuthenticationProvider()); }
	 * 
	 * @Bean DaoAuthenticationProvider daoAuthenticationProvider() {
	 * DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	 * provider.setPasswordEncoder(passwordEncoder);
	 * provider.setUserDetailsService(daoUserDetailsService); return provider; }
	 */
	 @Autowired
	 public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception
	 {
		 authenticationManagerBuilder.userDetailsService(daoUserDetailsService).passwordEncoder(passwordEncoder);
	 }
}
