package javaproject.demo.dto;

import javaproject.demo.entities.Client;
import lombok.Data;

@Data
public class ClientResponse {
    private Long id;
    private String name;
    private String surname;
    private String number;
    private String email;
    private String description;

    public static ClientResponse mapToClientResponse(Client client) {
        ClientResponse clientResponse = new ClientResponse();

        clientResponse.setId(client.getId());
        clientResponse.setDescription(client.getDescription());
        clientResponse.setName(client.getName());
        clientResponse.setSurname(client.getSurname());
        clientResponse.setEmail(client.getEmail());
        clientResponse.setNumber(client.getNumber());

        return clientResponse;
    }

}
