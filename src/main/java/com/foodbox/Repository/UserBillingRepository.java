package com.foodbox.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodbox.entity.two.UserBilling;

public interface UserBillingRepository extends JpaRepository<UserBilling, Long> {

}
