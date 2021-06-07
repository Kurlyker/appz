package javaproject.demo.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import javaproject.demo.entities.Order;
import javaproject.demo.stubs.ClientStub;
import javaproject.demo.stubs.OrderStub;
import javaproject.demo.stubs.ProductStub;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TestEntityManager entityManager;
    private Order expectedOrder;

    @BeforeEach
    void setUp() {
        expectedOrder = OrderStub.getRandomOrder();
        entityManager.persist(expectedOrder);
        entityManager.flush();
    }

    @Test
    void testFindOrdersByClient() {
        var actualProducts = orderRepository.findOrdersByClient_Id(ClientStub.ID);
        Assertions.assertThat(actualProducts).hasSize(1);
    }

    @Test
    void testFindOrdersByProduct() {
        var actualProducts = orderRepository.findOrdersByProduct_Id(ProductStub.ID);
        Assertions.assertThat(actualProducts).hasSize(1);
    }

}