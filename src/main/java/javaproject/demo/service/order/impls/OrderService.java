package javaproject.demo.service.order.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javaproject.demo.dto.OrderResponse;
import javaproject.demo.entities.Order;
import javaproject.demo.repository.OrderRepository;
import javaproject.demo.service.order.interfaces.IOrderService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    @Override
    public List<OrderResponse> getAll() {
        var orders = orderRepository.findAll();
        return orders.stream().map(OrderResponse::mapToOrderResponse).collect(Collectors.toList());
    }

    @Override
    public List<OrderResponse> getOrdersByClientId(Long id) {
        var orders = orderRepository.findOrdersByClient_Id(id);
        return orders.stream().map(OrderResponse::mapToOrderResponse).collect(Collectors.toList());
    }

    @Override
    public List<OrderResponse> getOrdersByCarId(Long id) {
        var orders = orderRepository.findOrdersByCar_Id(id);
        return orders.stream().map(OrderResponse::mapToOrderResponse).collect(Collectors.toList());
    }

    @Override
    public OrderResponse getById(Long id) {
        var order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No Category with this ID: " + id));
        return OrderResponse.mapToOrderResponse(order);
    }

    @Override
    public OrderResponse create(Order order) {
        return OrderResponse.mapToOrderResponse(orderRepository.save(order));
    }

    @Override
    public OrderResponse update(Long id, Order order) {
        var orderToUpdate = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No Category with this ID: " + id));
        return OrderResponse.mapToOrderResponse(orderRepository.save(orderToUpdate));
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

}
