package org.rubychacko.SIM.service;

import org.rubychacko.SIM.model.StoreLocation;

import java.util.List;
import java.util.Optional;

public interface StoreLocationService {

    StoreLocation saveStoreLocation(StoreLocation storeLocation);

    Optional<StoreLocation> findStoreLocationById(Integer storeId);

    List<StoreLocation> findAllStores();

    void deleteStoreLocation(Integer storeId);
}
