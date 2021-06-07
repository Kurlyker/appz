package javaproject.demo.service.car.interfaces;

import javaproject.demo.dto.CarRequest;
import javaproject.demo.dto.CarResponse;

import java.util.List;

public interface ICarService {
    List<CarResponse> getAll();
    List<CarResponse> getCarsBySupplierId(Long id);
    CarResponse getById(Long id);
    CarResponse create(CarRequest car);
    CarResponse update(Long id, CarRequest car);
    void delete(Long id);
}
