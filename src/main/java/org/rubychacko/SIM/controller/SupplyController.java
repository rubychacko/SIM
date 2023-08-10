package org.rubychacko.SIM.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.rubychacko.SIM.model.Supply;
import org.rubychacko.SIM.service.SupplyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import static org.rubychacko.SIM.util.SIMConstants.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SupplyController {

    private final SupplyServiceImpl supplyServiceImpl;

    @PostMapping(SUPPLY_CONTEXT_PATH)
    public String createSupply(Supply supply) {
        log.info("Received request to create supply record with supply={}", supply);
        val response = supplyServiceImpl.saveSupply(supply);
        return HTML_SUPPLY_REDIRECT;
    }

    @GetMapping(SUPPLY_CONTEXT_PATH + "/update/{storeId}/{productId}")
    public String internalUpdateSupply(@PathVariable("storeId") String storeId, @PathVariable("productId") String productId, Model model) {

        log.info("Received request to load supply records with storeId={} and productId={}", storeId, productId);
        val supplies = supplyServiceImpl.findAllSupplies();
        Supply supply = supplyServiceImpl.findAllSuppliesByStoreIdAndProductId(storeId, productId).get(0);

        model.addAttribute("supply", supply);
        model.addAttribute("supplies", supplies);

        return HTML_SUPPLY_VIEW;
    }

    @GetMapping( SUPPLY_CONTEXT_PATH + "/{storeId}/{productId}")
    public ResponseEntity<?> getSupplyByStoreAndProductIds(@PathVariable("storeId") String storeId, @PathVariable("productId") String productId) {

        log.info("Received request to retrieve supply records with storeId={} and productId={}", storeId, productId);
        List<Supply> supplies = supplyServiceImpl.findAllSuppliesByStoreIdAndProductId(storeId, productId);

        return supplies.isEmpty() ? ResponseEntity.ok()
                .body(supplies) : ResponseEntity.notFound().build();
    }

    @GetMapping(SUPPLY_CONTEXT_PATH + "/{storeId}")
    public ResponseEntity<?> getSupplyByStoreId(@PathVariable("storeId") String storeId) {

        log.info("Received request to retrieve all supply records with storeId={}", storeId);
        List<Supply> supplies = supplyServiceImpl.findAllSuppliesByStoreId(storeId);

        return supplies.isEmpty() ? ResponseEntity.ok()
                .body(supplies) : ResponseEntity.notFound().build();
    }

    @GetMapping(SUPPLY_CONTEXT_PATH)
    public String getStoreLocations(Model model) {
        log.info("Received request to retrieve all the supply records");
        val supplies = supplyServiceImpl.findAllSupplies();

        model.addAttribute("supply", new Supply());
        model.addAttribute("supplies", supplies);

        return HTML_SUPPLY_VIEW;
    }
}
