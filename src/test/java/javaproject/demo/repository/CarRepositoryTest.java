package javaproject.demo.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import javaproject.demo.entities.Car;
import javaproject.demo.stubs.ProductStub;
import javaproject.demo.stubs.SupplierStub;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class CarRepositoryTest {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private TestEntityManager entityManager;
    private Car expectedProduct;

    @BeforeEach
    void setUp() {
        expectedProduct = ProductStub.getRandomProduct();
        entityManager.persist(expectedProduct);
        entityManager.flush();
    }

    @Test
    void testFindProductsBySupplier() {
        var actualProducts = carRepository.findProductsBySupplier_Id(SupplierStub.ID);
        Assertions.assertThat(actualProducts).hasSize(1);
    }


}