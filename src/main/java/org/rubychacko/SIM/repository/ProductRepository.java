package org.rubychacko.SIM.repository;

import jakarta.transaction.Transactional;
import org.rubychacko.SIM.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, String> {

    @Modifying
    @Query("DELETE FROM Product p WHERE p.id = :id")
    void deleteByPid(@Param("id") String id);
}
