package org.rubychacko.SIM.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.rubychacko.SIM.model.StoreLocation;
import org.rubychacko.SIM.service.StoreLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StoreLocationController {

    private final StoreLocationService storeLocationService;

    @PostMapping("/store_location")
    public String createStoreLocation(StoreLocation storeLocation) {
        log.info("Received request to create a store location with storeLocation={}", storeLocation);
        val response = storeLocationService.saveStoreLocation(storeLocation);
        return "redirect:/store_location";
    }

    @GetMapping("/store_location/{id}")
    public ResponseEntity<?> getStoreLocationById(@PathVariable("id") String storeId) {

        log.info("Received request to retrieve the store location record with id={}", storeId);

        Optional<StoreLocation> storeLocationOptional = storeLocationService.findStoreLocationById(Integer.valueOf(storeId));

        return storeLocationOptional.isPresent() ? ResponseEntity.ok()
                .body(storeLocationOptional.get()) : ResponseEntity.notFound().build();
    }

    @GetMapping("/store_location/list")
    public ResponseEntity<?> getAllStoreLocations() {
        log.info("Received request to retrieve all the store location records");
        val storeLocations = storeLocationService.findAllStores();
        return ResponseEntity.ok().body(storeLocations);
    }


    @GetMapping("/store_location")
    public String getStoreLocations(Model model) {
        log.info("Received request to return store location records page");
        val storeLocations = storeLocationService.findAllStores();

        model.addAttribute("storeLocation", new StoreLocation());
        model.addAttribute("stores", storeLocations);
        return "index";
    }

    @DeleteMapping("/store_location/{id}")
    public ResponseEntity<?> deleteStoreLocation(@PathVariable("id") String storeId) {

        log.info("Received request to delete the store location record with id={}", storeId);

        storeLocationService.deleteStoreLocation(Integer.valueOf(storeId));

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/store_location/update/{id}")
    public String updateStoreLocation(@PathVariable("id") String storeId, Model model) {

        log.info("Received request to load the page with update model for storeId={}", storeId);

        val storeLocations = storeLocationService.findAllStores();
        StoreLocation storeLocation = storeLocationService.findStoreLocationById(Integer.valueOf(storeId)).get();
        model.addAttribute("storeLocation", storeLocation);
        model.addAttribute("stores", storeLocations);
        return "index";
    }

    @GetMapping("/store_location/delete/{id}")
    public String internalDeleteStoreLocation(@PathVariable("id") String storeId) {

        log.info("Received request to handle the delete store location record with id={}", storeId);
        storeLocationService.deleteStoreLocation(Integer.valueOf(storeId));

        return "redirect:/store_location";
    }
}
