package com.cedalanavi.projet_IJVA500_SOA_users.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cedalanavi.projet_IJVA500_SOA_users.CreateUserRequest;
import com.cedalanavi.projet_IJVA500_SOA_users.UpdateUserRequest;
import com.cedalanavi.projet_IJVA500_SOA_users.Entities.User;
import com.cedalanavi.projet_IJVA500_SOA_users.Repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User createUser(CreateUserRequest userRequest) {
		
		
		User userExist = userRepository.findByUsername(userRequest.username);
		
		// Test si user existe déjà + le cas du username/mdp vide
		if(userExist == null && userRequest.username != "" && userRequest.password != "") {
			
			User newUser = new User();
			newUser.setUsername(userRequest.username);
			newUser.setPassword(userRequest.password);
			
			return userRepository.save(newUser);
		} else {
			return null;
		}
		
		
	}
	
	public void deleteUser(int id) {
		userRepository.deleteById(id);
	}
	
	public void updateUser(UpdateUserRequest userRequest, int id) {
		User updatedUser = userRepository.findById(id).get();

		updatedUser.setPassword(userRequest.password);
		
		userRepository.save(updatedUser);
		
	}
	
}
