package com.cedalanavi.project_ijva500_soa_users.Controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cedalanavi.project_ijva500_soa_users.Data.UserCreateRequest;
import com.cedalanavi.project_ijva500_soa_users.Data.UserUpdateRequest;
import com.cedalanavi.project_ijva500_soa_users.Services.UserService;

@RestController
@RequestMapping("manage-user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/create")
	public void createUser(@RequestBody UserCreateRequest userRequest, HttpServletResponse response) {
		if (userService.createUser(userRequest) != null) {
			response.setStatus(HttpServletResponse.SC_OK);
		}
		else {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public void deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
	}
	
	@PutMapping("/update/{id}")
	public void updateUser(@RequestBody UserUpdateRequest userRequest, @PathVariable int id) {
		userService.updateUser(userRequest, id);
	}
	
}
