package org.rubychacko.SIM.service;

import org.rubychacko.SIM.model.Product;

import java.util.List;
import java.util.Optional;

/**
 * Contains service methods to manage the product resources.
 *
 * @author Ruby Chacko
 */
public interface ProductService {
    Product saveProductInfo(Product product);
    Optional<Product> findProductById(String productId);
    List<Product> findAllProducts();
    void deleteProduct(String productId);
}
