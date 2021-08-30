package com.foodbox.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodbox.entity.two.BillingAddress;

public interface BillingAddressRepository extends JpaRepository<BillingAddress, Long> {

}
