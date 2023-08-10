package org.rubychacko.SIM.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rubychacko.SIM.model.Product;
import org.rubychacko.SIM.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Contains service methods to manage the product resources.
 *
 * @author Ruby Chacko
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    /**
     * To create/save a product resource in the system
     *
     * @param product product object from the request
     * @return - product resource saved in the system
     */
    public Product saveProductInfo(Product product) {
        productRepository.save(product);
        log.info("Saved product in the system with productInfo={}", product);
        return product;
    }

    /**
     * To retrieve product resource from the system by its id
     *
     * @param productId product object from the request
     * @return - Optional of product resource from the system
     */
    public Optional<Product> findProductById(String productId) {
        return productRepository.findById(productId);
    }

    /**
     * To retrieve all product resources from the system
     * @return - List of all the product resources from the system
     */
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    /**
     * To remove a product resource from the system by its id
     */
    public void deleteProduct(String productId) {
        productRepository.deleteByPid(productId);
    }
}
