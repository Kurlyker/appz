package javaproject.demo.service.client.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javaproject.demo.dto.ClientRequest;
import javaproject.demo.dto.ClientResponse;
import javaproject.demo.mapper.ClientMapper;
import javaproject.demo.repository.ClientRepository;
import javaproject.demo.service.client.interfaces.IClientService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ClientService implements IClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public List<ClientResponse> getAll() {
        var clients = clientRepository.findAll();
        return clients.stream().map(ClientResponse::mapToClientResponse).collect(Collectors.toList());
    }

    @Override
    public ClientResponse getById(Long id) {
        var client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No Category with this ID: " + id));
        return ClientResponse.mapToClientResponse(client);
    }

    @Override
    public ClientResponse create(ClientRequest client) {
        var newClient = clientMapper.fromRequest(client);
        return ClientResponse.mapToClientResponse(clientRepository.save(newClient));
    }

    @Override
    public ClientResponse update(Long id, ClientRequest request) {
        var client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No Category with this ID: " + id));
        client.setDescription(request.getDescription());
        client.setName(request.getName());
        client.setEmail(request.getEmail());
        client.setNumber(request.getNumber());
        client.setSurname(request.getSurname());
        return ClientResponse.mapToClientResponse(clientRepository.save(client));
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }
}
