package com.cedalanavi.projet_IJVA500_SOA_users;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class UserRequest {

	@JsonIgnore(value = false)
	public String Username;
	
	@JsonIgnore(value = false)
	public String Password;
}
