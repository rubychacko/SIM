package org.rubychacko.SIM.service;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.rubychacko.SIM.model.Product;
import org.rubychacko.SIM.repository.ProductRepository;

import java.util.Optional;

public class ProductServiceTest {

    private ProductServiceImpl service;
    private ProductRepository repository;

    @BeforeEach
    public void init() {
        repository = Mockito.mock(ProductRepository.class);
        service = new ProductServiceImpl(repository);
    }

    @Test
    public void testSaveProductInfo() {

        Product response = service.saveProductInfo(newProduct("id"));
        Mockito.verify(repository, Mockito.times(1)).save(ArgumentMatchers.any(Product.class));
        Assertions.assertNotNull(response);
    }

    @ParameterizedTest
    @ValueSource(strings = { "TEST_PROD_1", "TEST_PROD_2", "TEST_PROD_3" })
    void testProductCreationWithParameters(String productId) {
        Mockito.doReturn(Optional.of(newProduct(productId))).when(repository).findById(ArgumentMatchers.anyString());

        service.saveProductInfo(newProduct(productId));

        val response = service.findProductById(productId);
        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.isPresent());
    }

    @Test
    public void testFindProductById() {

        Mockito.doReturn(Optional.of(newProduct("id1"))).when(repository).findById(ArgumentMatchers.anyString());

        val response = service.findProductById("id1");
        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.isPresent());
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
