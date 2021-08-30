package com.foodbox.Controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodbox.Service.UserService;
import com.foodbox.entity.Role;
import com.foodbox.entity.User;
import com.foodbox.entity.UserRole;


@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	@Autowired
private UserService uService;	
	
@Autowired
	private BCryptPasswordEncoder bcryptencoder;
	
	
	//Creating user
	@PostMapping("/")
	public User createUser(@RequestBody User user) throws Exception {
		

		
//		Encoding Password with bcrypt password incoder
		user.setPassword(this.bcryptencoder.encode(user.getPassword()));
		
		
		
		Set<UserRole>roles=new HashSet<>();
		Role role=new Role();
		role.setRoleId(45L);
		role.setRoleName("NORMAL");
		
		UserRole userrole=new UserRole();
		userrole.setUser(user);
		userrole.setRole(role);
		
		roles.add(userrole);
		return this.uService.createUser(user, roles);
		 
	}
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username")String username) {
	return	this.uService.getUser(username);
	     
	}

	//Delete by id
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId")long userId) {
		this.uService.deleteUser(userId);
	}
	
	
	
//	
//	   //update api
//
//
//    @ExceptionHandler(UserFoundException.class)
//    public ResponseEntity<?> exceptionHandler(UserFoundException ex) {
//        return ResponseEntity.ok(ex.getMessage());
//    }
}
