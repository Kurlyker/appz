package javaproject.demo.service.car.impls;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javaproject.demo.dto.CarRequest;
import javaproject.demo.dto.CarResponse;
import javaproject.demo.mapper.CarMapper;
import javaproject.demo.repository.CarRepository;
import javaproject.demo.service.car.interfaces.ICarService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CarService implements ICarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;
    @Override
    public List<CarResponse> getAll() {
        var cars = carRepository.findAll();
        return cars.stream().map(CarResponse::mapToCarResponse).collect(Collectors.toList());
    }

    @Override
    public List<CarResponse> getCarsBySupplierId(Long id) {
        var cars = carRepository.findCarsBySupplier_Id(id);
        return cars.stream().map(CarResponse::mapToCarResponse).collect(Collectors.toList());
    }

    @Override
    public CarResponse getById(Long id) {
        var car = carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No Category with this ID: " + id));
        return CarResponse.mapToCarResponse(car);
    }

    @Override
    public CarResponse create(CarRequest car) {
        var newcar = carMapper.fromRequest(car);
        return CarResponse.mapToCarResponse(carRepository.save(newcar));
    }

    @Override
    public CarResponse update(Long id, CarRequest request) {
        var car = carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No Category with this ID: " + id));
        car.setDescription(request.getDescription());
        car.setName(request.getName());
        car.setPrice(request.getPrice());
        car.setCountOfAvailability(request.getCountOfAvailability());
        car.setSupplier(request.getSupplier());
        return CarResponse.mapToCarResponse(carRepository.save(car));
    }

    @Override
    public void delete(Long id) {
        carRepository.deleteById(id);
    }
}
