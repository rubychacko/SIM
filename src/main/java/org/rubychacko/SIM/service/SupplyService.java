package org.rubychacko.SIM.service;

import lombok.RequiredArgsConstructor;
import org.rubychacko.SIM.model.Supply;
import org.rubychacko.SIM.repository.SupplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SupplyService {

    private final SupplyRepository supplyRepository;

    public Supply saveSupply(Supply supply) {
        supplyRepository.save(supply);
        return supply;
    }

    public List<Supply> findAllSupplies() {
        return supplyRepository.findAll();
    }

    public List<Supply> findAllSuppliesByStoreId(String storeId) {
        return supplyRepository.findAllSupplyByStoreId(storeId);
    }

    public List<Supply> findAllSuppliesByStoreIdAndProductId(String storeId, String productId) {
        return supplyRepository.findAllSupplyByStoreIdAndProductId(storeId, productId);
    }
}
