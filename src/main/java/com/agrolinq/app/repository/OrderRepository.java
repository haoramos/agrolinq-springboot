package com.agrolinq.app.repository;

import com.agrolinq.app.models.Order;
import com.agrolinq.app.models.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    Optional<Order> findByConsumidor_Id(UUID consumidorId);

    Optional<Order> findByProdutor_Id(UUID produtorId);

    Optional<Order> findByStatus(OrderStatus status);
}
