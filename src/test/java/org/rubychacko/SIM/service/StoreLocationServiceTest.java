package org.rubychacko.SIM.service;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StoreLocationServiceTest {

    @Autowired
    StoreLocationServiceImpl service;

    @Test
    public void testFindAllStores() {

        val response = service.findAllStores();

        Assertions.assertNotNull(response);
        Assertions.assertFalse(response.isEmpty());
    }

    @Test
    public void testFindStoresById() {

        val response = service.findStoreLocationById(1);
        Assertions.assertNotNull(response);
    }
}
