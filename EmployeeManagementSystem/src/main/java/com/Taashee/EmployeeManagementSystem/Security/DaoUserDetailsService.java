package com.Taashee.EmployeeManagementSystem.Security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Taashee.EmployeeManagementSystem.Dao.ManagementDao;
import com.Taashee.EmployeeManagementSystem.JwtUtils.JwtUtil;
import com.Taashee.EmployeeManagementSystem.Models.JWTModel;
import com.Taashee.EmployeeManagementSystem.Models.JWTResponse;
import com.Taashee.EmployeeManagementSystem.Models.Users;


@Service
public class DaoUserDetailsService implements UserDetailsService{
	@Autowired
	private ManagementDao managementDao;
	
//	private JwtUtil jwtUtil;
//	
//	private PasswordEncoder passwordEncoder;
//	
//	
//	private AuthenticationManager authenticationManager;
	
	//@Autowired
	//
//	public DaoUserDetailsService(ManagementDao managementDao, JwtUtil jwtUtil, PasswordEncoder passwordEncoder,
//			AuthenticationManager authenticationManager) {
//		super();
//		this.managementDao = managementDao;
//		this.jwtUtil = jwtUtil;
//		this.passwordEncoder = passwordEncoder;
//		this.authenticationManager = authenticationManager;
//	}
//	public JWTResponse createToken(JWTModel jwtModel)
//	{
//		String username=jwtModel.getUsername();
//		String password =jwtModel.getPassword();
//		String encPassword=passwordEncoder.encode(password);
//		System.out.println(encPassword);
//		authenticate(username, password);
//		final UserDetails userDetails=loadUserByUsername(username);
//		String jwtToken=jwtUtil.generateJWTToken(userDetails);
//		Users user= (Users) managementDao.getUserByUserName(username);
//		return new JWTResponse(user, jwtToken);
//		
//	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Users user= (Users) managementDao.getUserByUserName(username);
		if(user!=null)
		{
			return new User(user.getUsername(),user.getPassword(), getAuthorities(user));
		}
		else
		{
			throw new UsernameNotFoundException("username not valid");
		}
	}
	private Set getAuthorities(Users user)
	{
		Set authorities=new HashSet();
		String role=user.getRole();
	
		authorities.add(new SimpleGrantedAuthority("ROLE_"+role)); 
		return authorities;
		
			
	}
//	private void authenticate(String username, String password)
//	{
//		try
//		{
//			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//		}catch(DisabledException e)
//		{
//			System.out.println("user is disabled");
//		}catch (BadCredentialsException e) {
//			// TODO: handle exception
//			System.out.println("Bad Credintials entered!");
//		}
//	}

}
