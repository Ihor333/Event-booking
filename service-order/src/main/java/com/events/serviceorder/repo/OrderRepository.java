package com.events.serviceorder.repo;

import com.events.serviceorder.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByClientId(Long client_id);
    Optional<Order> findByEventId(Long event_id);
}
