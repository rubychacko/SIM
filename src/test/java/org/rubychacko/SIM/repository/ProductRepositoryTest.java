package org.rubychacko.SIM.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.rubychacko.SIM.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    public void testCreateAndDeleteProducts() {
        String productId = "TEST_O1";
        productRepository.save(newProduct(productId));
        Assertions.assertTrue(productRepository.findById(productId).isPresent());

        productRepository.deleteByPid(productId);
        Assertions.assertFalse(productRepository.findById(productId).isPresent());
    }

    private Product newProduct(String id) {
        return Product.builder()
                .id(id)
                .name("name")
                .color("color")
                .price(10.0)
                .brand("brand")
                .build();
    }
}
