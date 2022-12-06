package com.cedalanavi.projet_IJVA500_SOA_users.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cedalanavi.projet_IJVA500_SOA_users.Data.UserCreateRequest;
import com.cedalanavi.projet_IJVA500_SOA_users.Data.UserUpdateRequest;
import com.cedalanavi.projet_IJVA500_SOA_users.Entities.User;
import com.cedalanavi.projet_IJVA500_SOA_users.Repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	public User createUser(UserCreateRequest userRequest) {
		User userExist = userRepository.findByUsername(userRequest.username);
		
		// Test if user already exist and handle empty password
		if(userExist == null && userRequest.username != "") {
			
			User newUser = new User();
			newUser.setUsername(userRequest.username);
			
			return userRepository.save(newUser);
		} else {
			return null;
		}
	}
	
	public void deleteUser(int id) {
		userRepository.deleteById(id);
	}
	
	public void updateUser(UserUpdateRequest userRequest, int id) {
		User updatedUser = userRepository.findById(id).get();
		userRepository.save(updatedUser);
	}
}
