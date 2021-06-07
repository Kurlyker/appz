package javaproject.demo.service.client.interfaces;

import javaproject.demo.dto.ClientRequest;
import javaproject.demo.dto.ClientResponse;

import java.util.List;

public interface IClientService {
    List<ClientResponse> getAll();
    ClientResponse getById(Long id);
    ClientResponse create(ClientRequest client);
    ClientResponse update(Long id, ClientRequest client);
    void delete(Long id);
}
