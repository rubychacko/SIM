package org.rubychacko.SIM.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.rubychacko.SIM.model.Supply;
import org.rubychacko.SIM.service.SupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SupplyController {

    private final SupplyService supplyService;

    @PostMapping("/supply")
    public ResponseEntity<?> createSupply(@RequestBody Supply supply) {
        log.info("Received request to create supply record with supply={}", supply);
        val response = supplyService.saveSupply(supply);
        return ResponseEntity.ok()
                .body(response);
    }

    @GetMapping("/product/{storeId}/{productId}")
    public ResponseEntity<?> getSupplyByStoreAndProductIds(@PathVariable("storeId") String storeId, @PathVariable("productId") String productId) {

        log.info("Received request to retrieve supply records with storeId={} and productId={}", storeId, productId);
        List<Supply> supplies = supplyService.findAllSuppliesByStoreIdAndProductId(storeId, productId);

        return supplies.isEmpty() ? ResponseEntity.ok()
                .body(supplies) : ResponseEntity.notFound().build();
    }

    @GetMapping("/product/{storeId}")
    public ResponseEntity<?> getSupplyByStoreId(@PathVariable("storeId") String storeId) {

        log.info("Received request to retrieve all supply records with storeId={}", storeId);
        List<Supply> supplies = supplyService.findAllSuppliesByStoreId(storeId);

        return supplies.isEmpty() ? ResponseEntity.ok()
                .body(supplies) : ResponseEntity.notFound().build();
    }

    @GetMapping("/supply")
    public ResponseEntity<?> getStoreLocations() {
        log.info("Received request to retrieve all the supply records");
        val supplies = supplyService.findAllSupplies();
        return ResponseEntity.ok().body(supplies);
    }
}
