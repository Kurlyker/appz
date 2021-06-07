package javaproject.demo.dto;

import lombok.Data;

@Data
public class ClientRequest {
    private String name;
    private String surname;
    private String number;
    private String email;
    private String description;
}
