package com.foodbox;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import com.foodbox.Service.UserService;

import com.foodbox.entity.Role;
import com.foodbox.entity.User;
import com.foodbox.entity.UserRole;

@SpringBootApplication
public class FoodboxApplication implements CommandLineRunner {


	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	


	
	public static void main(String[] args) {
		SpringApplication.run(FoodboxApplication.class, args);
	
	
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Code Started");
	
//		
//		User user = new User();
//		user.setFirstName("Admin");
//		user.setLastName("admin	");
//		user.setUsername("Admin11");
//		user.setPassword(this.bCryptPasswordEncoder.encode("admin"));
//		user.setEmail("admin@gmail.com");
//	
//		
//		Role role1=new Role();
//		role1.setRoleId(44L);
//		role1.setRoleName("ADMIN");
//		
//		Set<UserRole> userRoleSet=new HashSet<>();
//		UserRole userRole = new UserRole();
//		userRole.setRole(role1);
//		userRole.setUser(user);
//		
//		userRoleSet.add(userRole);
//		
//		User createUser = this.userService.createUser(user, userRoleSet);
//		System.out.println(createUser.getUsername());

	}




    }
