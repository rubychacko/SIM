package org.rubychacko.SIM.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.rubychacko.SIM.model.Supply;
import org.rubychacko.SIM.service.SupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SupplyController {

    private final SupplyService supplyService;

    @PostMapping("/supply")
    public String createSupply(Supply supply) {
        log.info("Received request to create supply record with supply={}", supply);
        val response = supplyService.saveSupply(supply);
        return "redirect:/supply";
    }

    @GetMapping("/supply/update/{storeId}/{productId}")
    public String internalUpdateSupply(@PathVariable("storeId") String storeId, @PathVariable("productId") String productId, Model model) {

        log.info("Received request to load supply records with storeId={} and productId={}", storeId, productId);
        val supplies = supplyService.findAllSupplies();
        Supply supply = supplyService.findAllSuppliesByStoreIdAndProductId(storeId, productId).get(0);

        model.addAttribute("supply", supply);
        model.addAttribute("supplies", supplies);

        return "supply";
    }

    @GetMapping("/supply/{storeId}/{productId}")
    public ResponseEntity<?> getSupplyByStoreAndProductIds(@PathVariable("storeId") String storeId, @PathVariable("productId") String productId) {

        log.info("Received request to retrieve supply records with storeId={} and productId={}", storeId, productId);
        List<Supply> supplies = supplyService.findAllSuppliesByStoreIdAndProductId(storeId, productId);

        return supplies.isEmpty() ? ResponseEntity.ok()
                .body(supplies) : ResponseEntity.notFound().build();
    }

    @GetMapping("/supply/{storeId}")
    public ResponseEntity<?> getSupplyByStoreId(@PathVariable("storeId") String storeId) {

        log.info("Received request to retrieve all supply records with storeId={}", storeId);
        List<Supply> supplies = supplyService.findAllSuppliesByStoreId(storeId);

        return supplies.isEmpty() ? ResponseEntity.ok()
                .body(supplies) : ResponseEntity.notFound().build();
    }

    @GetMapping("/supply")
    public String getStoreLocations(Model model) {
        log.info("Received request to retrieve all the supply records");
        val supplies = supplyService.findAllSupplies();

        model.addAttribute("supply", new Supply());
        model.addAttribute("supplies", supplies);

        return "supply";
    }
}
