package io.sambell.product.service.impl;

import io.sambell.product.entity.app.Product;
import io.sambell.product.repository.ProductRepository;
import io.sambell.product.service.ProductService;
import io.sambell.product.utils.FilesUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static io.sambell.product.utils.Global.invalidCreatedProduct;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private FilesUtils filesUtils;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, FilesUtils filesUtils) {
        this.productRepository = productRepository;
        this.filesUtils = filesUtils;
    }

    @Override
    public Product create(Product product, MultipartFile file) {
        if (invalidCreatedProduct(product))
            return null;
        else {
            ResponseEntity<String> response = filesUtils.upload(file, product.getCreator().getEmail(), "product_pictures");
            if (response.getStatusCode().is2xxSuccessful()) {
                product.setPicture(response.getBody());
                return productRepository.save(product);
            }
            else return null;
        }
    }

    @Override
    public Product update(UUID id, Product product) {
        Optional<Product> prods = getProductById(id);
        if (prods.isEmpty()) return null;

        if (product.getDescription() != null && !prods.get().getLinks().equals(product.getLinks()))
            prods.get().setLinks(product.getLinks());

        if (product.getDescription() != null && !prods.get().getPicture().equals(product.getPicture()))
            prods.get().setPicture(product.getPicture());

        if (product.getDescription() != null && !prods.get().getProvenance().equals(product.getProvenance()))
            prods.get().setProvenance(product.getProvenance());

        if (product.getDescription() != null && !prods.get().getDescription().equals(product.getDescription()))
            prods.get().setDescription(product.getDescription());

        return productRepository.save(prods.get()); // renvoie un code de statut 200
    }

    @Override
    public String delete(UUID id) {
        if (getProductById(id).isPresent()) {
            productRepository.deleteById(id);
            return "Product deleted";
        } else
            return "Product doesn't exist";
    }

    @Override
    public Optional<Product> getProductById(UUID id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByProductName(String productName) {
        return productRepository.findByProductName(productName);
    }

    @Override
    public List<Product> getProductsByDescription(String description) {
        return productRepository.findByDescription(description);
    }

    @Override
    public List<Product> getProductsByProvenance(String provenance) {
        return productRepository.findByProvenance(provenance);
    }

    @Override
    public ResponseEntity<String> addPicture(UUID productID, MultipartFile file) {
        Optional<Product> prods = getProductById(productID);
        if (prods.isPresent()) {
            Product product = prods.get();
            ResponseEntity<String> response = filesUtils.upload(file, product.getCreator().getEmail(), "product_pictures");
            if (response.getStatusCode().is2xxSuccessful()) {
                return response;
            } else return null;
        }
        return null;
    }
}
