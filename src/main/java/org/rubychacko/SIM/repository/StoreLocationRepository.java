package org.rubychacko.SIM.repository;

import org.rubychacko.SIM.model.StoreLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreLocationRepository extends JpaRepository<StoreLocation, Integer> {

}
