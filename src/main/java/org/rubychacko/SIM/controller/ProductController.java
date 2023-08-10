package org.rubychacko.SIM.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.rubychacko.SIM.model.Product;
import org.rubychacko.SIM.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

import static org.rubychacko.SIM.util.SIMConstants.*;

/**
 * Contains controller methods to manage the product resources.
 *
 * @author Ruby Chacko
 */
@Slf4j
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {

    private final ProductServiceImpl productServiceImpl;
    /**
     * To create/save a product resource in the system
     *
     * @param product product object from the request
     * @return - Product HTML view with details filled in for the user
     */
    @PostMapping(PRODUCT_CONTEXT_PATH)
    public String createProduct(Product product) {
        log.info("Received request to create a product with product={}", product);
        productServiceImpl.saveProductInfo(product);
        return HTML_PRODUCT_REDIRECT;
    }

    /**
     * To retrieve the product resource from the system by its ID
     *
     * @param productId id of the product object
     * @return - Product object if present in the system or 404
     */
    @GetMapping(PRODUCT_CONTEXT_PATH + "/{id}")
    public ResponseEntity<?> getProductInfoById(@PathVariable("id") String productId) {

        log.info("Received request to retrieve product record with id={}", productId);
        Optional<Product> productOptional = productServiceImpl.findProductById(productId);

        return productOptional.isPresent() ? ResponseEntity.ok()
                .body(productOptional.get()) : ResponseEntity.notFound().build();
    }

    /**
     * To retrieve the product HTML view with the details to be presented to the user
     *
     * @param model model object for interacting with the UI
     * @return - Product HTML view with details filled in for the user
     */
    @GetMapping(PRODUCT_CONTEXT_PATH)
    public String getProducts(Model model) {
        log.info("Received request to retrieve all the product records");
        val products = productServiceImpl.findAllProducts();

        model.addAttribute("product", new Product());
        model.addAttribute("products", products);

        return HTML_PRODUCT_VIEW;
    }

    /**
     * To delete a product resource in the system by its ID
     *
     * @param productId id of the product object to be deleted
     * @return - Product HTML view with updated details filled in for the user
     */
    @GetMapping(PRODUCT_CONTEXT_PATH + "/delete/{id}")
    public String deleteProductExternal(@PathVariable("id") String productId) {
        log.info("Received request to delete the record with id={}", productId);
        productServiceImpl.deleteProduct(productId);
        return HTML_PRODUCT_REDIRECT;
    }

    /**
     * To update a product resource in the system by its Id. Helper method to refresh the view with the edit form
     *
     * @param productId id of the product object to be deleted
     * @param model model object for interacting with the UI
     * @return - Product update HTML view with updated details filled in for the user
     */
    @GetMapping(PRODUCT_CONTEXT_PATH + "/update/{id}")
    public String updateProductExternal(@PathVariable("id") String productId, Model model) {
        log.info("Received request to update the record with id={}", productId);

        val products = productServiceImpl.findAllProducts();
        Product product = productServiceImpl.findProductById(productId).get();

        model.addAttribute("product", product);
        model.addAttribute("products", products);

        return HTML_PRODUCT_VIEW;
    }

    /**
     * To delete a product resource in the system by its ID
     *
     * @param productId id of the product object to be deleted
     * @return -  status indicating whether the removal was successful.
     */
    @DeleteMapping(PRODUCT_CONTEXT_PATH + "/{id}")
    public String deleteProduct(@PathVariable("id") String productId) {

        log.info("Received request to delete the product record with id={}", productId);
        productServiceImpl.deleteProduct(productId);
        return HTML_PRODUCT_REDIRECT;
    }
}
