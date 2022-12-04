package com.cedalanavi.projet_IJVA500_SOA_users.Services;


import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cedalanavi.projet_IJVA500_SOA_users.Data.CreateUserRequest;
import com.cedalanavi.projet_IJVA500_SOA_users.Data.UpdateUserRequest;
import com.cedalanavi.projet_IJVA500_SOA_users.Entities.User;
import com.cedalanavi.projet_IJVA500_SOA_users.Repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	public User createUser(CreateUserRequest userRequest) {
		User userExist = userRepository.findByUsername(userRequest.username);
		
		// Test si user existe déjà + le cas du username/mdp vide
		if(userExist == null && userRequest.username != "" && userRequest.password != "") {
			
			User newUser = new User();
			newUser.setUsername(userRequest.username);
			newUser.setPassword(bCryptPasswordEncoder.encode(userRequest.password));
			
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


    @Override
    @Transactional(readOnly = true)
    // Override the UserDetailsService method using by Spring Security
    public UserDetails loadUserByUsername(String username) {
        com.cedalanavi.projet_IJVA500_SOA_users.Entities.User user = userRepository.findByUsername(username);
        
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("admin"));

        UserDetails userDetail = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
        
        return userDetail;
    }
}
