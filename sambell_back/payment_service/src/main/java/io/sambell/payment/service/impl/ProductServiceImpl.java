package io.sambell.payment.service.impl;

import io.sambell.payment.repository.ProductRepository;
import io.sambell.payment.service.ProductService;
import io.sambell.payment.entity.app.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static io.sambell.payment.utils.Global.invalidCreatedProduct;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product create(Product product) {
        if (invalidCreatedProduct(product))
            return null;
        else
            return productRepository.save(product);
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
}
