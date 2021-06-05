package com.example.demo;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.controller.UserController;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;


@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
	
		@Mock private UserService userService;
	   private User user;
	   private Role role;
	   private Set<Role> roleSet;
	   
	   @InjectMocks
	   private UserController userController;
	   
	   @Autowired
	   private MockMvc mockMvc;
	   
	   @BeforeEach
	   public void setup(){
		   roleSet = new HashSet<Role>();
		role = new Role("role1", "description");
		roleSet.add(role);
	   user = new User("bat","ball",roleSet);
	   mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	   }
	   
	   @AfterEach
	   void tearDown() {
	   user = null; role  = null; roleSet = null;
	   }
	   
	   @Test
	   public void deleteUserTest() throws Exception {
	         //when(userService.deleteUserById(user.getUserId())).thenReturn(user);
	         mockMvc.perform(delete("/api/v1/deleteUser/1")
	         .contentType(MediaType.APPLICATION_JSON)
	         .content(asJsonString(user)))
	         .andExpect(MockMvcResultMatchers.status().isOk()).
	         andDo(MockMvcResultHandlers.print());
	   }
	   
	   @Test
	   public void getAllUsersControllerTest() throws Exception {
		   List<User> userList =  new ArrayList<User>();
		   userList.add(user);
	       when(userService.getUsers()).thenReturn(userList);
	       mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/allUser")
	                      );
	       verify(userService).getUsers();
	       verify(userService,times(1)).getUsers();
	   }
	  
	   
	   @Test
	   public void addUserControllerTest() throws Exception{
	      //when(userService.saveUser(user)).thenReturn(user);
		   System.out.println(asJsonString(user));
	      mockMvc.perform(post("/api/v1/addUser").
	              contentType(MediaType.APPLICATION_JSON).
	              content(asJsonString(user))).
	              andExpect(status().isOk());
	      
	      ArgumentCaptor<User> stuArgCaptor = ArgumentCaptor.forClass(User.class);
	      verify(userService,times(1)).saveUser(stuArgCaptor.capture());
			
			User userresult = stuArgCaptor.getValue();
			
			assertEquals(userresult.getFirstname(),user.getFirstname());
	   }
	   
	   
	   public static String asJsonString(final Object obj) {
	        try {
	            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(obj);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }

}
