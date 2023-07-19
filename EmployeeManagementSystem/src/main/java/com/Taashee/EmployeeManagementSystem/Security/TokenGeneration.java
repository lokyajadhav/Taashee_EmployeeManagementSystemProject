package com.Taashee.EmployeeManagementSystem.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.Taashee.EmployeeManagementSystem.Dao.ManagementDao;
import com.Taashee.EmployeeManagementSystem.JwtUtils.JwtUtil;
import com.Taashee.EmployeeManagementSystem.Models.JWTModel;
import com.Taashee.EmployeeManagementSystem.Models.JWTResponse;
import com.Taashee.EmployeeManagementSystem.Models.Users;
@Component
public class TokenGeneration {
	
    private ManagementDao managementDao;
	
	private JwtUtil jwtUtil;
	
	private PasswordEncoder passwordEncoder;
	
	
	private AuthenticationManager authenticationManager;
	private final UserDetailsService daoUserDetailsService;
	
	@Autowired
	public TokenGeneration(ManagementDao managementDao, JwtUtil jwtUtil, PasswordEncoder passwordEncoder,
			AuthenticationManager authenticationManager, UserDetailsService daoUserDetailsService) {
		super();
		this.daoUserDetailsService = daoUserDetailsService;
		
		this.managementDao = managementDao;
		this.jwtUtil = jwtUtil;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
	}


	public ResponseEntity<JWTResponse> createToken(JWTModel jwtModel)
	{
		String username=jwtModel.getUsername();
		String password =jwtModel.getPassword();
		String encPassword=passwordEncoder.encode(password);
		System.out.println(encPassword);
		authenticate(username, password);
		 if (!authenticate(username, password)) {
		        JWTResponse errorResponse = new JWTResponse(null, null, "Invalid credentials! Please check your username and password.");
		        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
		    }
		final UserDetails userDetails=daoUserDetailsService.loadUserByUsername(username);
		String jwtToken=jwtUtil.generateJWTToken(userDetails);
		Users user= (Users) managementDao.getUserByUserName(username);
		JWTResponse successResponse = new JWTResponse(user, jwtToken, null);
	    return ResponseEntity.ok(successResponse);
		
	}
	private boolean  authenticate(String username, String password)
	{
		try
		{
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			return true;
		}catch(DisabledException e)
		{
			System.out.println("user is disabled");
			return false;
		}catch (BadCredentialsException e) {
			// TODO: handle exception
			System.out.println("Bad Credintials entered!");
			return false;
		}
	}
}
