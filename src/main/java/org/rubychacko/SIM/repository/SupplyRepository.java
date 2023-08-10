package org.rubychacko.SIM.repository;

import jakarta.transaction.Transactional;
import org.rubychacko.SIM.model.Supply;
import org.rubychacko.SIM.model.SupplyPrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface SupplyRepository extends JpaRepository<Supply, SupplyPrimaryKey> {

    @Query("Select s FROM Supply s WHERE s.storeId = :storeId")
    List<Supply> findAllSupplyByStoreId(String storeId);

    @Query("Select s FROM Supply s WHERE s.storeId = :storeId AND s.productId = :productId")
    List<Supply> findSupplyByStoreAndProduct(String storeId, String productId);

    @Modifying
    @Query("DELETE FROM Supply s WHERE s.storeId = :storeId AND s.productId = :productId")
    void deleteSupplyByStoreAndProduct(String storeId, String productId);
}
