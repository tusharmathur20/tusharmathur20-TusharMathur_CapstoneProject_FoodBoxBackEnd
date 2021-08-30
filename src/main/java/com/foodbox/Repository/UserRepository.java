package com.foodbox.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodbox.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUsername(String username);
	
	User findByEmail(String email);
	List<User> findAll();

}
