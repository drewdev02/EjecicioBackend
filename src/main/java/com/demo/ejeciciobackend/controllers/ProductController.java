package com.demo.ejeciciobackend.controllers;

import com.demo.ejeciciobackend.models.Product;
import com.demo.ejeciciobackend.repository.ProductRepository;
import com.demo.ejeciciobackend.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {
   private final ProductService productService;

    @GetMapping("/")
    public ResponseEntity<Optional<List<Product>>> getAllProducts() {
        log.info("Fetching all products");
        var products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        log.info("Fetching product with ID: {}", id);
        var optionalProduct = productService.getProductById(id);
        return optionalProduct.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<Optional<Product>> createProduct(@RequestBody Product product) {
        log.info("Creating a new product: {}", product);
        var createdProduct = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        log.info("Updating product with ID: {}", id);
        var optionalProduct = productService.updateProduct(id, product);
        return optionalProduct.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        log.info("Deleting product with ID: {}", id);
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
