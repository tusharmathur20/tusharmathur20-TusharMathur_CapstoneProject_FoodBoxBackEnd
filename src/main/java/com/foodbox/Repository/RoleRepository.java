package com.foodbox.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodbox.entity.Role;

public interface RoleRepository  extends JpaRepository<Role, Long>{

}
