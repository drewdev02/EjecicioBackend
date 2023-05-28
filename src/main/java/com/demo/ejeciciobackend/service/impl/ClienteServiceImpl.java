package com.demo.ejeciciobackend.service.impl;

import com.demo.ejeciciobackend.models.Cliente;
import com.demo.ejeciciobackend.repository.ClienteRepository;
import com.demo.ejeciciobackend.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClienteServiceImpl implements ClienteService {
     private final ClienteRepository clienteRepository;

    @Override
    public Optional<List<Cliente>> getAllClients() {
        var clients = clienteRepository.findAll();
        return Optional.ofNullable(clients);
    }

    @Override
    public Optional<Cliente> createClient(Cliente client) {
        var createdClient = clienteRepository.save(client);
        return Optional.ofNullable(createdClient);
    }

    @Override
    public Optional<Cliente> updateClient(Long id, Cliente client) {
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
                    return Optional.ofNullable(updatedClient);
                })
                .orElse(Optional.empty());
    }

    @Override
    public void deleteClient(Long id) {
        clienteRepository.deleteById(id);
    }
}
