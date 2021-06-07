package javaproject.demo.stubs;

import javaproject.demo.dto.SupplierRequest;
import javaproject.demo.entities.Supplier;

import java.util.Date;
import java.util.HashSet;

public final class SupplierStub {
    public static final Long ID = 1L;
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String DIRECTOR = "DIRECTOR";
    public static final String BUSINESSBILL = "BUSINESSBILL";
    public static final String WHATSUPPLY = "WHATSUPPLY";
    public static final String NUMBER = "NUMBER";
    public static final String ADDRESS = "ADDRESS";
    public static final Date STARTEDWORK = new Date();
    public static Supplier getRandomSupplier() {
        return Supplier.builder()
                .id(ID)
                .businessName(NAME)
                .description(DESCRIPTION)
                .director(DIRECTOR)
                .whatProductSupply(WHATSUPPLY)
                .businessBill(BUSINESSBILL)
                .number(NUMBER)
                .address(ADDRESS)
                .startedWorkTogether(STARTEDWORK)
                .products(new HashSet<>())
                .build();
    }
    public static SupplierRequest getSupplierRequest() {
        var supplierRequest = new SupplierRequest();
        supplierRequest.setBusinessName(NAME);
        supplierRequest.setDescription(DESCRIPTION);
        supplierRequest.setAddress(ADDRESS);
        supplierRequest.setNumber(NUMBER);
        supplierRequest.setStartedWorkTogether(STARTEDWORK);
        supplierRequest.setDirector(DIRECTOR);
        supplierRequest.setWhatProductSupply(WHATSUPPLY);
        supplierRequest.setBusinessBill(BUSINESSBILL);
        return supplierRequest;
    }
}
