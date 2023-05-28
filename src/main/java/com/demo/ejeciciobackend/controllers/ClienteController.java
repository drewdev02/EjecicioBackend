package com.demo.ejeciciobackend.controllers;

import com.demo.ejeciciobackend.models.Cliente;
import com.demo.ejeciciobackend.service.ClienteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/client")
@AllArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;

    @GetMapping("/")
    public ResponseEntity<List<Cliente>> getAllClients() {
        log.info("Getting all clients");
        var clients = clienteService.getAllClients();
        return ResponseEntity.ok(clients.orElse(Collections.emptyList()));
    }

    @PostMapping("/")
    public ResponseEntity<Cliente> createClient(@RequestBody Cliente client) {
        log.debug("Creating client: {}", client);
        var createdClient = clienteService.createClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClient.orElse(null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateClient(@PathVariable Long id, @RequestBody Cliente client) {
        log.debug("Updating client with ID: {}", id);
        var updatedClient = clienteService.updateClient(id, client);
        return updatedClient.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        log.debug("Deleting client with ID: {}", id);
        clienteService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

}
