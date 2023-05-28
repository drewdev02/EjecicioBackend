package com.demo.ejeciciobackend.service;

import com.demo.ejeciciobackend.models.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface ClienteService {
    Optional<List<Cliente>> getAllClients();

    Optional<Cliente> createClient(Cliente client);

    Optional<Cliente> updateClient(Long id, Cliente client);

    void deleteClient(Long id);
}
