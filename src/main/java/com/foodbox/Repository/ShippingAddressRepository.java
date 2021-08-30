package com.foodbox.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodbox.entity.two.ShippingAddress;

public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Long> {

}
