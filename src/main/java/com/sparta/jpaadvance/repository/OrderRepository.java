package com.sparta.jpaadvance.repository;

import com.sparta.jpaadvance.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
