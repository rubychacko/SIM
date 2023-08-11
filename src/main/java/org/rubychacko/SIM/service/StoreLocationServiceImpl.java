package org.rubychacko.SIM.service;

import lombok.RequiredArgsConstructor;
import org.rubychacko.SIM.model.StoreLocation;
import org.rubychacko.SIM.repository.StoreLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Contains service methods to manage the StoreLocation resources.
 *
 * @author Ruby Chacko
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StoreLocationServiceImpl implements StoreLocationService {

    private final StoreLocationRepository storeLocationRepository;

    /**
     * To create/save a StoreLocation resource in the system
     * StoreLocation object from the request
     *
     * @return - StoreLocation resource saved in the system
     */
    public StoreLocation saveStoreLocation(StoreLocation storeLocation) {
        storeLocationRepository.save(storeLocation);
        return storeLocation;
    }

    /**
     * To retrieve StoreLocation resource from the system by its id
     * <p>
     * StoreLocation object from the request
     *
     * @return - Optional of StoreLocation resource from the system
     */
    public Optional<StoreLocation> findStoreLocationById(Integer storeId) {
        return storeLocationRepository.findById(storeId);
    }

    /**
     * To remove a StoreLocation resource from the system by its id
     */
    public List<StoreLocation> findAllStores() {
        return storeLocationRepository.findAll();
    }

    public void deleteStoreLocation(Integer storeId) {
        storeLocationRepository.deleteById(storeId);
    }
}
