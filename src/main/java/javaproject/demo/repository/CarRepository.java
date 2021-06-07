package javaproject.demo.repository;
import javaproject.demo.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findCarsBySupplier_Id(Long id);
}
