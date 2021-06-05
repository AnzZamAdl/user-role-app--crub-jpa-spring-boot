package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserRepository;
import com.example.demo.model.User;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo ;
	
	public List<User> getUsers() {
		return userRepo.findAll();
	}
	
	public User saveUser(User user) {
		return userRepo.save(user);
	}
	
	public void deleteUserById(Long id) {
		 userRepo.deleteById(id);
	}
	
}
