package javaproject.demo.dto;

import javaproject.demo.entities.Car;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CarResponse {
    private Long id;
    private String name;
    private String description;
    private String code;
    private String brand;
    private Integer countOfAvailability;
    private BigDecimal price;
    private SupplierResponse supplier;

    public static CarResponse mapToCarResponse(Car car) {
        CarResponse carResponse = new CarResponse();

        carResponse.setId(car.getId());
        carResponse.setDescription(car.getDescription());
        carResponse.setName(car.getName());
        carResponse.setPrice(car.getPrice());
        carResponse.setBrand(car.getBrand());
        carResponse.setCode(car.getCode());
        carResponse.setCountOfAvailability(car.getCountOfAvailability());
        carResponse.setSupplier(SupplierResponse.mapToSupplierResponse(car.getSupplier()));

        return carResponse;
    }

}
