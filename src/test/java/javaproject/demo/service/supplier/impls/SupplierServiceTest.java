package javaproject.demo.service.supplier.impls;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import javaproject.demo.entities.Supplier;
import javaproject.demo.mapper.SupplierMapper;
import javaproject.demo.repository.SupplierRepository;
import javaproject.demo.stubs.OrderStub;
import javaproject.demo.stubs.SupplierStub;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SupplierServiceTest {
    private SupplierService service;
    @Mock
    private SupplierRepository supplierRepository;
    @Mock
    private SupplierMapper supplierMapper;

    @BeforeEach
    void setup() {
        service = new SupplierService(supplierRepository, supplierMapper);
    }

    @Test
    void getSuccessful() {
        List<Supplier> list = new ArrayList<Supplier>();
        var supplier = SupplierStub.getRandomSupplier();
        list.add(supplier);
        list.add(supplier);
        list.add(supplier);

        when(supplierRepository.findAll()).thenReturn(list);
        var getAll = service.getAll();
        assertEquals(list.size(), getAll.size());
    }

    @Test
    void getSuccessfulById() {
        var supplier = SupplierStub.getRandomSupplier();
        when(supplierRepository.findById(Mockito.any())).thenReturn(Optional.of(supplier));
        var byId = service.getById(SupplierStub.ID);

        assertAll(() -> assertEquals(byId.getId(), supplier.getId()),
                () -> assertEquals(byId.getBusinessName(), supplier.getBusinessName()),
                () -> assertEquals(byId.getBusinessBill(), supplier.getBusinessBill()),
                () -> assertEquals(byId.getDescription(), supplier.getDescription()));

    }

    @Test
    void createSuccessful() {
        var captor = ArgumentCaptor.forClass(Supplier.class);
        var supplier = SupplierStub.getRandomSupplier();
        when(supplierMapper.fromRequest(Mockito.any())).thenReturn(supplier);
        when(supplierRepository.save(Mockito.any())).thenReturn(SupplierStub.getRandomSupplier());
        var result = service.create(SupplierStub.getSupplierRequest());
        Mockito.verify(supplierRepository, atLeast(1)).save(captor.capture());
        assertEquals(supplier, captor.getValue());
        assertEquals(supplier.getId(), result.getId());
        assertEquals(supplier.getDescription(), result.getDescription());
    }

    @Test
    void updateSuccessful() {
        var captor = ArgumentCaptor.forClass(Supplier.class);
        var supplier = SupplierStub.getRandomSupplier();
        when(supplierRepository.findById(Mockito.any())).thenReturn(Optional.of(SupplierStub.getRandomSupplier()));
        when(supplierRepository.save(Mockito.any())).thenReturn(SupplierStub.getRandomSupplier());
        var result = service.update(SupplierStub.ID, SupplierStub.getSupplierRequest());
        Mockito.verify(supplierRepository, atLeast(1)).save(captor.capture());
        assertEquals(supplier, captor.getValue());
        assertEquals(supplier.getId(), result.getId());
        assertEquals(supplier.getDescription(), result.getDescription());
    }

    @Test
    void deleteSuccessful() {
        service.delete(SupplierStub.ID);
        var captor = ArgumentCaptor.forClass(Long.class);
        Mockito.verify(supplierRepository, atLeast(1)).deleteById(captor.capture());
        assertEquals(SupplierStub.ID, captor.getValue());
    }
}