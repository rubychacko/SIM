package org.rubychacko.SIM.service;

import lombok.RequiredArgsConstructor;
import org.rubychacko.SIM.model.StoreLocation;
import org.rubychacko.SIM.repository.StoreLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StoreLocationServiceImpl implements StoreLocationService{

    private final StoreLocationRepository storeLocationRepository;

    public StoreLocation saveStoreLocation(StoreLocation storeLocation) {
        storeLocationRepository.save(storeLocation);
        return storeLocation;
    }

    public Optional<StoreLocation> findStoreLocationById(Integer storeId) {
        return storeLocationRepository.findById(storeId);
    }

    public List<StoreLocation> findAllStores() {
        return storeLocationRepository.findAll();
    }

    public void deleteStoreLocation(Integer storeId) {
        storeLocationRepository.deleteById(storeId);
    }
}
