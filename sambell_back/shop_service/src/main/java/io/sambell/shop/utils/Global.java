package io.sambell.shop.utils;

import io.sambell.shop.entity.app.Product;
import io.sambell.shop.entity.app.enums.PoolState;

public class Global {

    public static boolean invalidCreatedProduct(Product product) {
        if(product.getProductName() == null) return true;
        if(product.getDescription() == null) return true;
        if(product.getLinks() == null) return true;
        if(product.getPicture() == null) return true;
        return product.getProvenance() == null;
    }

    public static boolean invalidCreatedPool(Product product, Long quantity, PoolState state) {
        if (product == null) return true;
        if (quantity == null) return true;
        return state == null;
    }

}
