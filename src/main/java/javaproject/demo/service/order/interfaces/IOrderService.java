package javaproject.demo.service.order.interfaces;

import javaproject.demo.dto.OrderResponse;
import javaproject.demo.entities.Order;

import java.util.List;

public interface IOrderService {
    List<OrderResponse> getAll();
    List<OrderResponse> getOrdersByClientId(Long id);
    List<OrderResponse> getOrdersByCarId(Long id);
    OrderResponse getById(Long id);
    OrderResponse create(Order booking);
    OrderResponse update(Long id, Order booking);
    void delete(Long id);
}
