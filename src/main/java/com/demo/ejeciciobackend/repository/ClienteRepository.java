package com.demo.ejeciciobackend.repository;

import com.demo.ejeciciobackend.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // Aquí puedes agregar métodos personalizados de consulta si los necesitas

}
