package com.vivek.pandey.orderservice.repository;

import com.vivek.pandey.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
