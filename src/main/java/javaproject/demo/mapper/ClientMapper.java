package javaproject.demo.mapper;


import javaproject.demo.dto.ClientRequest;
import javaproject.demo.entities.Client;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Random;

@Component
public class ClientMapper {
    public Client fromRequest(ClientRequest request) {
        return Client.builder()
                .id(new Random().nextLong())
                .name(request.getName())
                .surname(request.getSurname())
                .number(request.getNumber())
                .email(request.getEmail())
                .description(request.getDescription())
                .orders(new HashSet<>())
                .build();
    }
}
