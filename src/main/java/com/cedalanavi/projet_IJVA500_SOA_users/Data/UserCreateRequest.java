package com.cedalanavi.projet_IJVA500_SOA_users.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserCreateRequest {

	@JsonIgnore(value = false)
	public String username;
}
