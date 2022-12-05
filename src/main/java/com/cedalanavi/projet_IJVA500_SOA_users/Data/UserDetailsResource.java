package com.cedalanavi.projet_IJVA500_SOA_users.Data;

import java.util.List;

public class UserDetailsResource {

	String username;

	List<String> authorities;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}
	
	
}
