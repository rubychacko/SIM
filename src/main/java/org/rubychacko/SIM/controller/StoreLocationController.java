package org.rubychacko.SIM.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.rubychacko.SIM.model.StoreLocation;
import org.rubychacko.SIM.service.StoreLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StoreLocationController {

    private final StoreLocationService storeLocationService;

    @PostMapping("/store_location")
    public ResponseEntity<?> createStoreLocation(@RequestBody StoreLocation storeLocation) {
        log.info("Received request to create a store location with storeLocation={}", storeLocation);
        val response = storeLocationService.saveStoreLocation(storeLocation);
        return ResponseEntity.ok()
                .body(response);
    }

    @GetMapping("/store_location/{id}")
    public ResponseEntity<?> getStoreLocationById(@PathVariable("id") String storeId) {

        log.info("Received request to retrieve the store location record with id={}", storeId);

        Optional<StoreLocation> storeLocationOptional = storeLocationService.findStoreLocationById(Integer.valueOf(storeId));

        return storeLocationOptional.isPresent() ? ResponseEntity.ok()
                .body(storeLocationOptional.get()) : ResponseEntity.notFound().build();
    }

    @GetMapping("/store_location")
    public ResponseEntity<?> getStoreLocations() {
        log.info("Received request to retrieve all the store location records");
        val storeLocations = storeLocationService.findAllStores();
        return ResponseEntity.ok().body(storeLocations);
    }

    @DeleteMapping("/store_location/{id}")
    public ResponseEntity<?> deleteStoreLocation(@PathVariable("id") String storeId) {

        log.info("Received request to delete the store location record with id={}", storeId);

        storeLocationService.deleteStoreLocation(Integer.valueOf(storeId));

        return ResponseEntity.noContent().build();
    }
}
