package org.rubychacko.SIM.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rubychacko.SIM.exception.InvalidQuantityException;
import org.rubychacko.SIM.exception.ProductNotFoundException;
import org.rubychacko.SIM.exception.StoreNotFoundException;
import org.rubychacko.SIM.model.Supply;
import org.rubychacko.SIM.repository.StoreLocationRepository;
import org.rubychacko.SIM.repository.SupplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Contains service methods to manage the Supply Service resources.
 *
 * @author Ruby Chacko
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SupplyServiceImpl implements SupplyService {

    private final SupplyRepository supplyRepository;
    private final StoreLocationService storeLocationService;
    private final ProductService productService;

    /**
     * To create/save a supply resource in the system
     *
     * @param supply supply object from the request
     * @return - supply resource saved in the system
     */
    public Supply saveSupply(Supply supply) {
        supplyRepository.save(supply);
        return supply;
    }

    /**
     * To retrieve all supply resources from the system
     *
     * @return - List of all the supply resources from the system
     */
    public List<Supply> findAllSupplies() {
        return supplyRepository.findAll();
    }

    /**
     * To retrieve all supply resources from the system by storeId
     *
     * @return - List of all the supply resources from the system by StoreId
     */
    public List<Supply> findAllSuppliesByStoreId(String storeId) {
        return supplyRepository.findAllSupplyByStoreId(storeId);
    }

    /**
     * To retrieve all supply resources from the system by storeId and productId
     *
     * @return - List of all the supply resources from the system by StoreId and productId
     */
    public List<Supply> findAllSuppliesByStoreIdAndProductId(String storeId, String productId) {
        return supplyRepository.findSupplyByStoreAndProduct(storeId, productId);
    }

    /**
     * To delete supply resources from the system by storeId and productId
     */
    public void deleteSupplyByStoreIdAndProductId(String storeId, String productId) {
        supplyRepository.deleteSupplyByStoreAndProduct(storeId, productId);
    }

    /**
     * To handle exception handling
     */
    public boolean validateRequest(Supply supply) throws Exception {

        if (supply.getStoreId() == null || storeLocationService.findStoreLocationById(Integer.valueOf(supply.getStoreId())).isEmpty()) {
            log.error("Store id passed as empty or store record is not found the system storeID={}", supply.getStoreId());
            throw new StoreNotFoundException("Invalid Store");
        }

        if (supply.getProductId() == null || productService.findProductById(supply.getStoreId()).isEmpty()) {
            log.error("product id passed as empty or store record is not found the system productID={}", supply.getProductId());
            throw new ProductNotFoundException("Invalid Store");
        }

        if (supply.getAvailableInventory() > supply.getTotalInventory()) {
            log.error("available quantity is greater than total quantity");
            throw new InvalidQuantityException("Available Quantity is Greater Than Total Quantity");
        }
        return true;
    }
}
