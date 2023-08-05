package org.rubychacko.SIM.service;

import lombok.RequiredArgsConstructor;
import org.rubychacko.SIM.model.Product;
import org.rubychacko.SIM.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductService {

    private final ProductRepository productRepository;

    public Product saveProductInfo(Product product) {
        productRepository.save(product);
        return product;
    }

    public Optional<Product> findProductById(String productId) {
        return productRepository.findById(productId);
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public void deleteProduct(String productId) {
        productRepository.deleteById(productId);
    }
}
