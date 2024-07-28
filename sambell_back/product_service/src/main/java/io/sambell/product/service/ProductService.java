package io.sambell.product.service;

import io.sambell.product.entity.app.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {
    Product create(Product product, MultipartFile file);
    Product update(UUID id, Product product);
    String delete(UUID id);
    Optional<Product> getProductById(UUID id);
    List<Product> getAllProducts();
    List<Product> getProductsByProductName(String productName);
    List<Product> getProductsByDescription(String description);
    List<Product> getProductsByProvenance(String provenance);

    ResponseEntity<String> addPicture(UUID productID, MultipartFile file);
}
