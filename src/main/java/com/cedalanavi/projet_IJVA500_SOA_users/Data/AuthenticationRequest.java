package com.cedalanavi.projet_IJVA500_SOA_users.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class AuthenticationRequest {

	@JsonIgnore(value = false)
	public String username;
	
	@JsonIgnore(value = false)
	public String password;
}
