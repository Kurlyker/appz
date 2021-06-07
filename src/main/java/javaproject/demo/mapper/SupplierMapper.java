package javaproject.demo.mapper;

import javaproject.demo.dto.SupplierRequest;
import javaproject.demo.entities.Supplier;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Random;

@Component
public class SupplierMapper {
    public Supplier fromRequest(SupplierRequest request) {
        return Supplier.builder()
                .id(new Random().nextLong())
                .description(request.getDescription())
                .number(request.getNumber())
                .address(request.getAddress())
                .businessBill(request.getBusinessBill())
                .businessName(request.getBusinessName())
                .director(request.getDirector())
                .startedWorkTogether(request.getStartedWorkTogether())
                .whatCarSupply(request.getWhatCarSupply())
                .car(new HashSet<>())
                .build();
    }
}
