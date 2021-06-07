package javaproject.demo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SupplierRequest {
    private String businessName;
    private String director;
    private String address;
    private String number;
    private String whatCarSupply;
    private String businessBill;
    private String description;
    private Date startedWorkTogether;
}
