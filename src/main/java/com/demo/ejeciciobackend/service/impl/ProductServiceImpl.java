package com.demo.ejeciciobackend.service.impl;

import com.demo.ejeciciobackend.models.Product;
import com.demo.ejeciciobackend.repository.ProductRepository;
import com.demo.ejeciciobackend.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Optional<List<Product>> getAllProducts() {
        var products = productRepository.findAll();
        return Optional.ofNullable(products);
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> createProduct(Product product) {
        var createdProduct = productRepository.save(product);
        return Optional.ofNullable(createdProduct);
    }

    @Override
    public Optional<Product> updateProduct(Long id, Product product) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    var updatedProduct = Product.builder()
                            .id(existingProduct.getId())
                            .name(product.getName())
                            .price(product.getPrice())
                            .build();
                    updatedProduct = productRepository.save(updatedProduct);
                    return Optional.ofNullable(updatedProduct);
                })
                .orElse(Optional.empty());
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
