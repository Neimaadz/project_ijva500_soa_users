package com.cedalanavi.projet_IJVA500_SOA_users.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cedalanavi.projet_IJVA500_SOA_users.Data.AuthenticationRequest;
import com.cedalanavi.projet_IJVA500_SOA_users.Data.AuthenticationResource;
import com.cedalanavi.projet_IJVA500_SOA_users.Services.UserService;
import com.cedalanavi.projet_IJVA500_SOA_users.Utils.JwtTokenUtil;

@RestController
@RequestMapping("authentication-user")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userService;

	@PostMapping("/authenticate")
	public AuthenticationResource createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.username, authenticationRequest.password);
		final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.username);
		
		AuthenticationResource authenticationResource = new AuthenticationResource();
		authenticationResource.token = jwtTokenUtil.generateToken(userDetails);
		
		return authenticationResource;
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
