package com.demo.ejeciciobackend.controllers;

import com.demo.ejeciciobackend.models.Cliente;
import com.demo.ejeciciobackend.repository.ClienteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/client")
public class ClienteController {
    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping("/")
    public ResponseEntity<List<Cliente>> getAllClients() {
        log.info("Getting all clients");
        var clients = clienteRepository.findAll();
        return ResponseEntity.ok(clients);
    }

    @PostMapping("/")
    public ResponseEntity<Cliente> createClient(@RequestBody Cliente client) {
        log.debug("Creating client: {}", client);
        Cliente createdClient = clienteRepository.save(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateClient(@PathVariable Long id, @RequestBody Cliente client) {
        log.debug("Updating client with ID: {}", id);
        return clienteRepository.findById(id)
                .map(existingClient -> {
                    var updatedClient = Cliente.builder()
                            .id(existingClient.getId())
                            .name(client.getName())
                            .lastName(client.getLastName())
                            .dni(client.getDni())
                            .phone(client.getPhone())
                            .email(client.getEmail())
                            .build();
                    updatedClient = clienteRepository.save(updatedClient);
                    return ResponseEntity.ok(updatedClient);
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        log.debug("Deleting client with ID: {}", id);
        return clienteRepository.findById(id)
                .map(existingClient -> {
                    clienteRepository.deleteById(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(ResponseEntity.notFound().build());
    }


}
