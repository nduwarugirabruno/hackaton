package io.sambell.product.utils;

import io.sambell.product.entity.app.Product;

public class Global {

    public static boolean invalidCreatedProduct(Product product) {
        if(product.getProductName() == null) return true;
        if(product.getDescription() == null) return true;
        if(product.getLinks() == null) return true;
        if(product.getPicture() == null) return true;
        return product.getProvenance() == null;
    }

}
