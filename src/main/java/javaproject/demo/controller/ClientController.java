package javaproject.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javaproject.demo.dto.ClientRequest;
import javaproject.demo.dto.ClientResponse;
import javaproject.demo.service.client.impls.ClientService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/clients")
public class ClientController {
    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientResponse> create(@RequestBody ClientRequest request) {
        return ResponseEntity.ok(clientService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<ClientResponse>> getAll(@RequestParam(required = false, defaultValue = "1") Integer page,
                                                       @RequestParam(required = false, defaultValue = "10") Integer size) {
        return ResponseEntity.ok(clientService.getAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> getById(@PathVariable String id) {
        Long longId = Long.parseLong(id);
        return ResponseEntity.ok(clientService.getById(longId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponse> update(@PathVariable String  id, @RequestBody ClientRequest client){
        Long longId = Long.parseLong(id);
        return ResponseEntity.ok(clientService.update(longId, client));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        Long longId = Long.parseLong(id);
        clientService.delete(longId);
        return ResponseEntity.ok("Successfully deleted");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> exceptionHandling() {
        return ResponseEntity.badRequest().build();
    }


}
