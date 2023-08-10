package org.rubychacko.SIM.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rubychacko.SIM.model.Supply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SupplyRepositoryTest {

    @Autowired
    SupplyRepository repository;

    private static final String STORE_ID = "1";
    private static final String PRODUCT_ID = "PID";

    @BeforeEach
    public void cleanUp() {
        // Delete the supply record from the system (if present from prev tests)
        repository.deleteSupplyByStoreAndProduct(STORE_ID, PRODUCT_ID);
    }

    @Test
    public void testSupply() {

        // Supply shouldn't be present in the system
        List<Supply> supplyList = repository.findSupplyByStoreAndProduct(STORE_ID, PRODUCT_ID);
        Assertions.assertTrue(supplyList.isEmpty());

        // Create supply record in the system
        repository.save(newSupply());

        // Get supply and ensure that the record is created in the system
        supplyList = repository.findSupplyByStoreAndProduct(STORE_ID, PRODUCT_ID);
        Assertions.assertFalse(supplyList.isEmpty());

        // Delete the supply record from the system
        repository.deleteSupplyByStoreAndProduct(STORE_ID, PRODUCT_ID);

        // Supply shouldn't be present in the system
        supplyList = repository.findSupplyByStoreAndProduct(STORE_ID, PRODUCT_ID);
        Assertions.assertTrue(supplyList.isEmpty());
    }

    private Supply newSupply() {
        return Supply.builder()
                .productId(PRODUCT_ID)
                .storeId(STORE_ID)
                .availableInventory(10)
                .totalInventory(10)
                .build();
    }

}
