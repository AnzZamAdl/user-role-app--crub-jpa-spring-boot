package com.example.demo;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.example.demo.dao.UserRepository;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@DataJpaTest
class UserRepositoryTests {
	

	@Autowired UserRepository userrepo;
	
    @Autowired TestEntityManager testEntityManager;
	
   
	@Test
	void testaddUser() {
		Set <Role> roles = new HashSet<Role>();
		
		roles.add( new Role("Role 1", "description 1"));
		roles.add( new Role("Role 2", "description 2"));
		
        User user = new User("Ambrose", "Fernandes", roles);
        User saveduser= userrepo.save(user);

        User result = testEntityManager.find(User.class, saveduser.getUserId());
        assertTrue(result.getFirstname().equals(user.getFirstname()));
        assertTrue(result.getLastname().equals(user.getLastname()));
	}
	
	@Test
	void testupdateUser() {
		testaddUser();
		List<User> users = userrepo.findAll();
		User user = users.get(0);
		user.setLastname("Dsouza");
        User response = userrepo.save(user);
        assertTrue(response.getLastname().equals(user.getLastname()));
       
	}
	
	@Test
	void deleteUser() {
		testaddUser();
		List<User> users = userrepo.findAll();
		User user = users.get(0);
		user.setLastname("Dsouza");
		userrepo.deleteById(user.getUserId());
		User result = testEntityManager.find(User.class, user.getUserId());
		assertNull(result);
	}
}
