package com.cedalanavi.projet_IJVA500_SOA_users.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UpdateUserRequest {
	
	@JsonIgnore(value = false)
	public String username;
}
