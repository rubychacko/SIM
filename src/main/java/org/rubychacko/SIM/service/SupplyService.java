package org.rubychacko.SIM.service;

import org.rubychacko.SIM.model.Supply;

import java.util.List;

public interface SupplyService {

    Supply saveSupply(Supply supply);

    List<Supply> findAllSupplies();

    List<Supply> findAllSuppliesByStoreId(String storeId);

    List<Supply> findAllSuppliesByStoreIdAndProductId(String storeId, String productId);
}
