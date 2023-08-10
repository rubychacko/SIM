package org.rubychacko.SIM.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.rubychacko.SIM.model.StoreLocation;
import org.rubychacko.SIM.service.StoreLocationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import static org.rubychacko.SIM.util.SIMConstants.*;

/**
 * Contains controller methods to manage the Store Location resources.
 *
 * @author Ruby Chacko
 */
@Slf4j
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StoreLocationController {

    private final StoreLocationServiceImpl storeLocationServiceImpl;

    /**
     * To create/save a storeLocation resource in the system
     *
     * @param storeLocation storeLocation object from the request
     * @return - storeLocation HTML view with details filled in for the user
     */
    @PostMapping(STORE_CONTEXT_PATH)
    public String createStoreLocation(StoreLocation storeLocation) {
        log.info("Received request to create a store location with storeLocation={}", storeLocation);
        val response = storeLocationServiceImpl.saveStoreLocation(storeLocation);
        return "redirect:/store_location";
    }

    /**
     * To retrieve the storeLocation resource from the system by its ID
     *
     * @param storeId id of the storeLocation object
     * @return - storeLocation object if present in the system or 404
     */
    @GetMapping(STORE_CONTEXT_PATH + "/{id}")
    public ResponseEntity<?> getStoreLocationById(@PathVariable("id") String storeId) {

        log.info("Received request to retrieve the store location record with id={}", storeId);

        Optional<StoreLocation> storeLocationOptional = storeLocationServiceImpl.findStoreLocationById(Integer.valueOf(storeId));

        return storeLocationOptional.isPresent() ? ResponseEntity.ok()
                .body(storeLocationOptional.get()) : ResponseEntity.notFound().build();
    }

    /**
     * To retrieve the product resource from the system by its ID
     *
     * @return - Product object if present in the system or 404
     */
    @GetMapping(STORE_CONTEXT_PATH + "/list")
    public ResponseEntity<?> getAllStoreLocations() {
        log.info("Received request to retrieve all the store location records");
        val storeLocations = storeLocationServiceImpl.findAllStores();
        return ResponseEntity.ok().body(storeLocations);
    }


    @GetMapping(STORE_CONTEXT_PATH)
    public String getStoreLocations(Model model) {
        log.info("Received request to return store location records page");
        val storeLocations = storeLocationServiceImpl.findAllStores();

        model.addAttribute("storeLocation", new StoreLocation());
        model.addAttribute("stores", storeLocations);
        return HTML_INDEX_VIEW;
    }

    @DeleteMapping(STORE_CONTEXT_PATH + "/{id}")
    public ResponseEntity<?> deleteStoreLocation(@PathVariable("id") String storeId) {

        log.info("Received request to delete the store location record with id={}", storeId);

        storeLocationServiceImpl.deleteStoreLocation(Integer.valueOf(storeId));

        return ResponseEntity.noContent().build();
    }

    @GetMapping(STORE_CONTEXT_PATH + "/update/{id}")
    public String updateStoreLocation(@PathVariable("id") String storeId, Model model) {

        log.info("Received request to load the page with update model for storeId={}", storeId);

        val storeLocations = storeLocationServiceImpl.findAllStores();
        StoreLocation storeLocation = storeLocationServiceImpl.findStoreLocationById(Integer.valueOf(storeId)).get();
        model.addAttribute("storeLocation", storeLocation);
        model.addAttribute("stores", storeLocations);
        return HTML_INDEX_VIEW;
    }


    @GetMapping(STORE_CONTEXT_PATH + "/delete/{id}")
    public String internalDeleteStoreLocation(@PathVariable("id") String storeId) {

        log.info("Received request to handle the delete store location record with id={}", storeId);
        storeLocationServiceImpl.deleteStoreLocation(Integer.valueOf(storeId));

        return HTML_STORE_REDIRECT;
    }
}
