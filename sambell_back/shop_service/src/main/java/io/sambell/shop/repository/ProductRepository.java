package io.sambell.shop.repository;

import io.sambell.shop.entity.app.Product;
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

    @Query("select p from Product p where p.provenance like %?1%")
    List<Product> findByProvenance(String provenance);
}
