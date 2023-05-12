package com.cedalanavi.project_ijva500_soa_users.Controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cedalanavi.project_ijva500_soa_users.Data.UserCreateRequest;
import com.cedalanavi.project_ijva500_soa_users.Data.UserUpdateRequest;
import com.cedalanavi.project_ijva500_soa_users.Entities.User;
import com.cedalanavi.project_ijva500_soa_users.Services.UserService;

@RestController
@RequestMapping("manage-user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public List<User> getUsers() {
		return userService.getUsers();
	}
	
	@GetMapping("/user/{idUser}")
	public User getUser(@PathVariable String idUser) {
		return userService.getUserByIdUser(idUser);
	}
	
	@PostMapping("/create")
	public void createUser(@RequestBody UserCreateRequest userRequest, HttpServletResponse response) {
		if (userService.createUser(userRequest) != null) {
			response.setStatus(HttpServletResponse.SC_OK);
		}
		else {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}
	}
	
	@DeleteMapping(value = "/delete/{idUser}")
	public void deleteUser(@PathVariable String idUser) {
		userService.deleteUser(idUser);
	}
	
	@PutMapping("/update/{idUser}")
	public void updateUser(@RequestBody UserUpdateRequest userRequest, @PathVariable String idUser) {
		userService.updateUser(userRequest, idUser);
	}
	
}
