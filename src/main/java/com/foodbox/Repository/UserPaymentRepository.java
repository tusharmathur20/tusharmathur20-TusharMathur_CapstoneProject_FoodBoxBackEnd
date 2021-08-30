package com.foodbox.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodbox.entity.two.UserPayment;

public interface UserPaymentRepository extends JpaRepository<UserPayment, Long> {

}
