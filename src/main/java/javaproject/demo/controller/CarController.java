package javaproject.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javaproject.demo.dto.CarRequest;
import javaproject.demo.dto.CarResponse;
import javaproject.demo.service.car.impls.CarService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/cars")
public class CarController {
    private final CarService carService;
    @PostMapping
    public ResponseEntity<CarResponse> create(@RequestBody CarRequest request) {
        return ResponseEntity.ok(carService.create(request));
    }
    @GetMapping
    public ResponseEntity<List<CarResponse>> getAll(@RequestParam(required = false, defaultValue = "1") Integer page,
                                                    @RequestParam(required = false, defaultValue = "10") Integer size) {
        return ResponseEntity.ok(carService.getAll());
    }


    public ResponseEntity<List<CarResponse>> getCarBySupplier(@PathVariable Long supplierId){
        return ResponseEntity.ok(carService.getCarsBySupplierId(supplierId));
    }
    @GetMapping("/{id}")
    public ResponseEntity<CarResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(carService.getById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<CarResponse> update(@PathVariable Long id, @RequestBody CarRequest car){
        return ResponseEntity.ok(carService.update(id, car));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        carService.delete(id);
        return ResponseEntity.ok("Successfully deleted");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> exceptionHandling() {
        return ResponseEntity.badRequest().build();
    }
}