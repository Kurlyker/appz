package javaproject.demo.repository;
import javaproject.demo.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findOrdersByClient_Id(Long id);
    List<Order> findOrdersByCar_Id(Long id);
}
