package com.cedalanavi.projet_IJVA500_SOA_users.Security;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	@Value("${authentication.service.url}")
	String authenticationUrl;

	@Autowired
    @Qualifier("myRestTemplate")
	RestTemplate restTemplate;
	
	private List<String> skipUrls = Arrays.asList("/manage-user/create");
	
	private AntPathMatcher pathMatcher = new AntPathMatcher();

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		final String requestTokenHeader = request.getHeader("Authorization");
		
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {

			// Call Authentication service to check token validity
			UserDetailsResource userDetailsResource = restTemplate.getForEntity(authenticationUrl + "/isAuthenticated", UserDetailsResource.class).getBody();
	        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
	        userDetailsResource.getAuthorities().forEach(authoritie -> {
	        	grantedAuthorities.add(new SimpleGrantedAuthority(authoritie));
	        });
	        UserDetails userDetail = new org.springframework.security.core.userdetails.User(userDetailsResource.getUsername(), "", grantedAuthorities);

			// if token is valid
			if (userDetail.getUsername() != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				// Set user information got from Authentication service
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
						new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// Set the Authentication in Spring Security context
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		} else {
			logger.warn("JWT Token does not begin with Bearer String");
		}
		chain.doFilter(request, response);
	}
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		return skipUrls.stream().anyMatch(p -> pathMatcher.match(p, request.getRequestURI()));
	}
}