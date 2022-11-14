package com.cedalanavi.projet_IJVA500_SOA_users.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cedalanavi.projet_IJVA500_SOA_users.UserRequest;
import com.cedalanavi.projet_IJVA500_SOA_users.Entities.User;
import com.cedalanavi.projet_IJVA500_SOA_users.Repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User createUser(UserRequest userRequest) {
		
		
		User userExist = userRepository.findByUsername(userRequest.Username);
		
		// Test si user existe déjà + le cas du username/mdp vide
		if(userExist == null && userRequest.Username != "" && userRequest.Password != "") {
			
			User newUser = new User();
			newUser.setUsername(userRequest.Username);
			newUser.setPassword(userRequest.Password);
			
			return userRepository.save(newUser);
		} else {
			return null;
		}
		
		
	}
	
	public void deleteUser(int id) {
		userRepository.deleteById(id);
	}
	
}