package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {
	
	@Autowired
	UserService userservice ;
	
	@GetMapping("/home")
	public String home() {
		return "home page";
	}
	
	@PostMapping("/addUser")
	public User addUser(@RequestBody User user) {
		return userservice.saveUser(user);
	}
	
	@PutMapping("/updateUser")
	public User updateUser(@RequestBody User user) {
		return userservice.saveUser(user);
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable String id) {
		userservice.deleteUserById(Long.parseLong(id));
		 return "deleted";
	}
	
	@GetMapping("/allUser")
	public List <User> allUser() {
		return userservice.getUsers();
	}

}
