package io.sambell.product.controller;

import io.sambell.product.entity.app.Product;
import io.sambell.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private ProductService productService;

    @PostMapping("create")
    public ResponseEntity<Product> create(@RequestParam("file") MultipartFile file, @RequestParam("product") Product product) {
        try {
            Product product1 = productService.create(product, file);
            if (product1 != null)
                return ResponseEntity.ok(product1);
            else return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE); // renvoie un code de statut 406
        } catch (Exception e) {
            System.out.println("Error Cause -> " + e.getCause());
            System.out.println("Error Message -> " + e.getMessage());
            return ResponseEntity.internalServerError().build(); // renvoie un code de statut 500
        }
    }

    @PostMapping("add/picture/{id}")
    public ResponseEntity<String> create(@RequestParam("file") MultipartFile file, @PathVariable UUID id) {
        try {
            ResponseEntity<String> product1 = productService.addPicture(id, file);
            // renvoie un code de statut 204
            return Objects.requireNonNullElseGet(product1, () -> ResponseEntity.noContent().build());
        } catch (Exception e) {
            System.out.println("Error Cause -> " + e.getCause());
            System.out.println("Error Message -> " + e.getMessage());
            return ResponseEntity.internalServerError().build(); // renvoie un code de statut 500
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Product> update(@PathVariable UUID id, @RequestBody Product product) {
        try {
            Product product1 = productService.update(id, product);
            if (product1 != null)
                return ResponseEntity.ok(product1);
            else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println("Error Cause -> " + e.getCause());
            System.out.println("Error Message -> " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // renvoie un code de statut 500
        }
    }

    @PutMapping("delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        try {
            String response = productService.delete(id);
            if (response.equals("Product deleted"))
                return ResponseEntity.ok(response);
            else return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println("Error Cause -> " + e.getCause());
            System.out.println("Error Message -> " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // renvoie un code de statut 500
        }
    }

    @GetMapping("get/id/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable UUID id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("get/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        if (!products.isEmpty())
            return ResponseEntity.ok(products);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("get/productName/{productName}")
    public ResponseEntity<List<Product>> getProductsByFirstName(@PathVariable String productName) {
        List<Product> products = productService.getProductsByProductName(productName);
        if (!products.isEmpty())
            return ResponseEntity.ok(products);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("get/description/{description}")
    public ResponseEntity<List<Product>> getProductsByLastName(@PathVariable String description) {
        List<Product> products = productService.getProductsByDescription(description);
        if (!products.isEmpty())
            return ResponseEntity.ok(products);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("get/provenance/{provenance}")
    public ResponseEntity<List<Product>> getProductsByPhone(@PathVariable String provenance) {
        List<Product> products = productService.getProductsByProvenance(provenance);
        if (!products.isEmpty())
            return ResponseEntity.ok(products);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
