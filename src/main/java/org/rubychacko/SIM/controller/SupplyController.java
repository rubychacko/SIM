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

/**
 * Contains controller methods to manage the supply resources.
 *
 * @author Ruby Chacko
 */
@Slf4j
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SupplyController {

    private final SupplyServiceImpl supplyServiceImpl;
    /**
     * To create/save a supply resource in the system
     *
     * @param supply supply object from the request
     * @return - Supply HTML view with details filled in for the user
     */
    @PostMapping(SUPPLY_CONTEXT_PATH)
    public String createSupply(Supply supply) {
        try {
            log.info("Received request to create supply record with supply={}", supply);

            if (supplyServiceImpl.validateRequest(supply)) {
                supplyServiceImpl.saveSupply(supply);
                log.info("Successfully updated supply in the system");
                return HTML_SUPPLY_REDIRECT;
            }
        } catch (Exception e) {
            log.error("Invalid request received", e);
            return HTML_SUPPLY_REDIRECT;
        }

        val response = supplyServiceImpl.saveSupply(supply);
        return HTML_SUPPLY_REDIRECT;
    }
    /**
     * To update a supply resource in the system by Id. Helper method to refresh the view with the edit form
     * Received request to load supply records with storeId and productId
     * @return - Supply update HTML view with updated details filled in for the user
     */
    @GetMapping(SUPPLY_CONTEXT_PATH + "/update/{storeId}/{productId}")
    public String internalUpdateSupply(@PathVariable("storeId") String storeId, @PathVariable("productId") String productId, Model model) {

        log.info("Received request to load supply records with storeId={} and productId={}", storeId, productId);
        val supplies = supplyServiceImpl.findAllSupplies();
        Supply supply = supplyServiceImpl.findAllSuppliesByStoreIdAndProductId(storeId, productId).get(0);

        model.addAttribute("supply", supply);
        model.addAttribute("supplies", supplies);

        return HTML_SUPPLY_VIEW;
    }

    /** To read a supply resource in the system by storeId and productId
     *
     * Received request to read supply resources with storeId and productId
     * @return
     */
    @GetMapping( SUPPLY_CONTEXT_PATH + "/{storeId}/{productId}")
    public ResponseEntity<?> getSupplyByStoreAndProductIds(@PathVariable("storeId") String storeId, @PathVariable("productId") String productId) {

        log.info("Received request to retrieve supply records with storeId={} and productId={}", storeId, productId);
        List<Supply> supplies = supplyServiceImpl.findAllSuppliesByStoreIdAndProductId(storeId, productId);

        return supplies.isEmpty() ? ResponseEntity.ok()
                .body(supplies) : ResponseEntity.notFound().build();
    }

    /**
     * To retrieve the supply resource from the system by its ID
     *
     * @param storeId id of the sore object
     * @return - Store object if present in the system or 404
     */
    @GetMapping(SUPPLY_CONTEXT_PATH + "/{storeId}")
    public ResponseEntity<?> getSupplyByStoreId(@PathVariable("storeId") String storeId) {

        log.info("Received request to retrieve all supply records with storeId={}", storeId);
        List<Supply> supplies = supplyServiceImpl.findAllSuppliesByStoreId(storeId);

        return supplies.isEmpty() ? ResponseEntity.ok()
                .body(supplies) : ResponseEntity.notFound().build();
    }

    /**
     * To retrieve the supply HTML view with the details to be presented to the user
     *
     * @param model model object for interacting with the UI
     * @return - Supply HTML view with details filled in for the user
     */

    @GetMapping(SUPPLY_CONTEXT_PATH)
    public String getStoreLocations(Model model) {
        log.info("Received request to retrieve all the supply records");
        val supplies = supplyServiceImpl.findAllSupplies();

        model.addAttribute("supply", new Supply());
        model.addAttribute("supplies", supplies);

        return HTML_SUPPLY_VIEW;
    }
}
