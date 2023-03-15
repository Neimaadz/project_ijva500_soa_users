package com.cedalanavi.project_ijva500_soa_users.Services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cedalanavi.project_ijva500_soa_users.Data.UserCreateRequest;
import com.cedalanavi.project_ijva500_soa_users.Data.UserUpdateRequest;
import com.cedalanavi.project_ijva500_soa_users.Entities.User;
import com.cedalanavi.project_ijva500_soa_users.Repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> getUsers() {
		return userRepository.findAll();
	}
	
	public User getUserById(int idUser) {
		return userRepository.findById(idUser).get();
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
