package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dao.UserRepository;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.UserService;



@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	
	
	@Mock
    private UserRepository userRepo;
	
    @Autowired
    @InjectMocks
    private UserService userService;
    private User user;
    private Role role;
    Set<Role> rolelist;
    
    @BeforeEach
    public void setUp() {
    rolelist = new HashSet<Role>();
    role = new Role("role1","description");
    rolelist.add(role);
    user = new User("name", "jam",rolelist);
 
    }
    @AfterEach
    public void tearDown() {
    	user = null; role = null;
    	rolelist = null;
    }

	
	@Test
	public void verifygetUser() {
		userService.getUsers();
		verify(userRepo).findAll();
	}
	
	@Test
	public void verifyaddUser() {
		userService.saveUser(user);
		ArgumentCaptor<User> stuArgCaptor = ArgumentCaptor.forClass(User.class);
		verify(userRepo).save(stuArgCaptor.capture());
		
		User userresult = stuArgCaptor.getValue();
		
		assertEquals(userresult,user);
		
	}
	
	@Test
	public void verifydeleteUser() {
		Long id =1l;
		userService.deleteUserById(id);
		ArgumentCaptor<Long> stuArgCaptor = ArgumentCaptor.forClass(Long.class);
		verify(userRepo).deleteById(stuArgCaptor.capture());
		assertEquals(stuArgCaptor.getValue(),id);
		
	}

}
