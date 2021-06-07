package javaproject.demo.service.supplier.interfaces;

import javaproject.demo.dto.SupplierRequest;
import javaproject.demo.dto.SupplierResponse;

import java.util.List;

public interface ISupplierService {
    List<SupplierResponse> getAll();
    SupplierResponse getById(Long id);
    SupplierResponse create(SupplierRequest supplierRequest);
    SupplierResponse update(Long id, SupplierRequest supplierRequest);
    void delete(Long id);
}
