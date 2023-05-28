package com.demo.ejeciciobackend.service;

import com.demo.ejeciciobackend.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<List<Product>> getAllProducts();

    Optional<Product> getProductById(Long id);

    Optional<Product> createProduct(Product product);

    Optional<Product> updateProduct(Long id, Product product);

    void deleteProduct(Long id);
}

