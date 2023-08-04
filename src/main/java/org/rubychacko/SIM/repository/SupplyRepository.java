package org.rubychacko.SIM.repository;

import org.rubychacko.SIM.model.Product;
import org.rubychacko.SIM.model.Supply;
import org.rubychacko.SIM.model.SupplyPrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplyRepository extends JpaRepository<Supply, SupplyPrimaryKey> {

    List<Supply> findAllSupplyByStoreId(String storeId);
    List<Supply> findAllSupplyByStoreIdAndProductId(String storeId, String productId);
}
