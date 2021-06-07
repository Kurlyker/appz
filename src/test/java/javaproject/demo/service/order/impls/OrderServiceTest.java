package javaproject.demo.service.order.impls;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import javaproject.demo.entities.Order;
import javaproject.demo.repository.OrderRepository;
import javaproject.demo.stubs.ClientStub;
import javaproject.demo.stubs.OrderStub;
import javaproject.demo.stubs.ProductStub;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    private OrderService service;
    @Mock
    private OrderRepository orderRepository;

    @BeforeEach
    void setup() {
        service = new OrderService(orderRepository);
    }

    @Test
    void getSuccessful() {
        List<Order> list = new ArrayList<Order>();
        var order = OrderStub.getRandomOrder();
        list.add(order);
        list.add(order);
        list.add(order);

        when(orderRepository.findAll()).thenReturn(list);
        var getAll = service.getAll();
        assertEquals(list.size(), getAll.size());
    }


    @Test
    void getSuccessfulByClient() {
        List<Order> list = new ArrayList<Order>();
        var order = OrderStub.getRandomOrder();
        list.add(order);
        list.add(order);
        list.add(order);

        when(orderRepository.findOrdersByClient_Id(ClientStub.ID)).thenReturn(list);
        var getAll = service.getOrdersByClientId(ClientStub.ID);
        assertEquals(list.size(), getAll.size());
    }

    @Test
    void getSuccessfulByProduct() {
        List<Order> list = new ArrayList<Order>();
        var order = OrderStub.getRandomOrder();
        list.add(order);
        list.add(order);
        list.add(order);

        when(orderRepository.findOrdersByProduct_Id(ProductStub.ID)).thenReturn(list);
        var getAll = service.getOrdersByProductId(ProductStub.ID);
        assertEquals(list.size(), getAll.size());
    }

    @Test
    void getSuccessfulById() {
        var order = OrderStub.getRandomOrder();
        when(orderRepository.findById(Mockito.any())).thenReturn(Optional.of(order));
        var byId = service.getById(OrderStub.ID);

        assertAll(() -> assertEquals(byId.getId(), order.getId()),
                () -> assertEquals(byId.getCreatedDate(), order.getCreatedDate()),
                () -> assertEquals(byId.getDescription(), order.getDescription()),
                () -> assertEquals(byId.getClient_id(), order.getClient().getId()),
                () -> assertEquals(byId.getProduct_id(), order.getProduct().getId()));
    }


    @Test
    void createSuccessful() {
        var captor = ArgumentCaptor.forClass(Order.class);
        var personnel = OrderStub.getRandomOrder();
        when(orderRepository.save(Mockito.any())).thenReturn(OrderStub.getRandomOrder());
        var result = service.create(OrderStub.getRandomOrder());
        Mockito.verify(orderRepository, atLeast(1)).save(captor.capture());
        assertEquals(personnel, captor.getValue());
        assertEquals(personnel.getId(), result.getId());
        assertEquals(personnel.getDescription(), result.getDescription());
        assertEquals(personnel.getCreatedDate(), result.getCreatedDate());
    }


    @Test
    void updateSuccessful() {
        var captor = ArgumentCaptor.forClass(Order.class);
        var personnel = OrderStub.getRandomOrder();
        when(orderRepository.findById(Mockito.any())).thenReturn(Optional.of(OrderStub.getRandomOrder()));
        when(orderRepository.save(Mockito.any())).thenReturn(OrderStub.getRandomOrder());
        var result = service.update(OrderStub.ID, OrderStub.getRandomOrder());
        Mockito.verify(orderRepository, atLeast(1)).save(captor.capture());
        assertEquals(personnel, captor.getValue());
        assertEquals(personnel.getId(), result.getId());
        assertEquals(personnel.getDescription(), result.getDescription());
        assertEquals(personnel.getCreatedDate(), result.getCreatedDate());
    }

    @Test
    void deleteSuccessful() {
        service.delete(OrderStub.ID);
        var captor = ArgumentCaptor.forClass(Long.class);
        Mockito.verify(orderRepository, atLeast(1)).deleteById(captor.capture());
        assertEquals(OrderStub.ID, captor.getValue());
    }

}