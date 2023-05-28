package com.demo.ejeciciobackend.repository;

import com.demo.ejeciciobackend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);

    // Otros métodos de consulta personalizados se pueden agregar aquí

}
