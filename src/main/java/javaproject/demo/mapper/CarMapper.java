package javaproject.demo.mapper;

import javaproject.demo.dto.CarRequest;
import javaproject.demo.entities.Car;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Random;

@Component
public class CarMapper {
    public Car fromRequest(CarRequest request) {
        return Car.builder()
                .id(new Random().nextLong())
                .description(request.getDescription())
                .name(request.getName())
                .countOfAvailability(request.getCountOfAvailability())
                .price(request.getPrice())
                .brand(request.getBrand())
                .code(request.getCode())
                .supplier(request.getSupplier())
                .orders(new HashSet<>())
                .build();
    }
}
