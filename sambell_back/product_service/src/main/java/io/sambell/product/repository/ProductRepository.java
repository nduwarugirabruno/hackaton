package io.sambell.product.repository;

import io.sambell.product.entity.app.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Query("select p from Product p where p.productName like %?1%")
    List<Product> findByProductName(String productName);

    @Query("select p from Product p where p.description like %?1%")
    List<Product> findByDescription(String description);

    @Query("select p from Product p where p.provenance.city like %?1% or p.provenance.firstLine like %?1% or p.provenance.secondLine like %?1% or p.provenance.country like %?1% ")
    List<Product> findByProvenance(String provenance);
}
