package io.sambell.shop.service;

import io.sambell.shop.entity.app.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {
    Product create(Product product);
    Product update(UUID id, Product product);
    String delete(UUID id);
    Optional<Product> getProductById(UUID id);
    List<Product> getAllProducts();
    List<Product> getProductsByProductName(String productName);
    List<Product> getProductsByDescription(String description);
    List<Product> getProductsByProvenance(String provenance);
}
