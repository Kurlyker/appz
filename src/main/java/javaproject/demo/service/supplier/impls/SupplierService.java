package javaproject.demo.service.supplier.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javaproject.demo.dto.SupplierRequest;
import javaproject.demo.dto.SupplierResponse;
import javaproject.demo.mapper.SupplierMapper;
import javaproject.demo.repository.SupplierRepository;
import javaproject.demo.service.supplier.interfaces.ISupplierService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SupplierService implements ISupplierService {
    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;
    @Override
    public List<SupplierResponse> getAll() {
        var suppliers = supplierRepository.findAll();
        return suppliers.stream().map(SupplierResponse::mapToSupplierResponse).collect(Collectors.toList());
    }

    @Override
    public SupplierResponse getById(Long id) {
        var supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No Category with this ID: " + id));
        return SupplierResponse.mapToSupplierResponse(supplier);
    }

    @Override
    public SupplierResponse create(SupplierRequest supplier) {
        var newsupplier = supplierMapper.fromRequest(supplier);

        return SupplierResponse.mapToSupplierResponse(supplierRepository.save(newsupplier));
    }

    @Override
    public SupplierResponse update(Long id, SupplierRequest request) {
        var supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No Category with this ID: " + id));
        supplier.setDescription(request.getDescription());
        supplier.setAddress(request.getAddress());
        supplier.setBusinessBill(request.getBusinessBill());
        supplier.setBusinessName(request.getBusinessName());
        supplier.setDirector(request.getDirector());
        supplier.setNumber(request.getNumber());
        supplier.setWhatCarSupply(request.getWhatCarSupply());
        supplier.setStartedWorkTogether(request.getStartedWorkTogether());
        return SupplierResponse.mapToSupplierResponse(supplierRepository.save(supplier));
    }

    @Override
    public void delete(Long id) {
        supplierRepository.deleteById(id);
    }
}
