package com.foodbox.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodbox.entity.two.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
