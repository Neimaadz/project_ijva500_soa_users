package com.cedalanavi.projet_IJVA500_SOA_users.Controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cedalanavi.projet_IJVA500_SOA_users.CreateUserRequest;
import com.cedalanavi.projet_IJVA500_SOA_users.UpdateUserRequest;
import com.cedalanavi.projet_IJVA500_SOA_users.Entities.User;
import com.cedalanavi.projet_IJVA500_SOA_users.Services.UserService;

@RestController
@RequestMapping("manage-user")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	/* Méthode createUser 
	 * Vérifie que user.username ne soit pas nul et n'existe pas déjà
	 * Vérifie que user.password ne soit pas nul
	 */
	@PostMapping("/create")
	public void createUser(@RequestBody CreateUserRequest userRequest, HttpServletResponse response) {
		if (userService.createUser(userRequest) != null) {
			response.setStatus(HttpServletResponse.SC_OK);
		}
		else {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
	}
	
	@PutMapping("/update/{id}")
	public void updateUser(@RequestBody UpdateUserRequest userRequest, @PathVariable int id) {
		userService.updateUser(userRequest, id);
	}
	
}
