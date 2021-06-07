package javaproject.demo.dto;

import javaproject.demo.entities.Supplier;
import lombok.Data;
import java.util.Date;

@Data
public class SupplierResponse {
    private Long id;
    private String businessName;
    private String director;
    private String address;
    private String number;
    private String whatCarSupply;
    private String businessBill;
    private String description;
    private Date startedWorkTogether;

    public static SupplierResponse mapToSupplierResponse(Supplier supplier) {
        SupplierResponse supplierResponse = new SupplierResponse();

        supplierResponse.setId(supplier.getId());
        supplierResponse.setDescription(supplier.getDescription());
        supplierResponse.setAddress(supplier.getAddress());
        supplierResponse.setBusinessBill(supplier.getBusinessBill());
        supplierResponse.setBusinessName(supplier.getBusinessName());
        supplierResponse.setDirector(supplier.getNumber());
        supplierResponse.setStartedWorkTogether(supplier.getStartedWorkTogether());
        supplierResponse.setWhatCarSupply(supplier.getWhatCarSupply());

        return supplierResponse;
    }

}
