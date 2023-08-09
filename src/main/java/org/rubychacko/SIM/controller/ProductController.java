package org.rubychacko.SIM.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.rubychacko.SIM.model.Product;
import org.rubychacko.SIM.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {

    private final ProductService productService;

    @PostMapping("/product")
    public String createProduct(Product product) {
        log.info("Received request to create a product with product={}", product);
        productService.saveProductInfo(product);
        return "redirect:/product";
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProductInfoById(@PathVariable("id") String productId) {

        log.info("Received request to retrieve product record with id={}", productId);
        Optional<Product> productOptional = productService.findProductById(productId);

        return productOptional.isPresent() ? ResponseEntity.ok()
                .body(productOptional.get()) : ResponseEntity.notFound().build();
    }

    @GetMapping("/product")
    public String getProducts(Model model) {
        log.info("Received request to retrieve all the product records");
        val products = productService.findAllProducts();

        model.addAttribute("product", new Product());
        model.addAttribute("products", products);

        return "product";
    }

    @GetMapping("/product/delete/{id}")
    public String deleteProductExternal(@PathVariable("id") String productId) {
        log.info("Received request to delete the record with id={}", productId);
        productService.deleteProduct(productId);
        return "redirect:/product";
    }

    @GetMapping("/product/update/{id}")
    public String updateProductExternal(@PathVariable("id") String productId, Model model) {
        log.info("Received request to update the record with id={}", productId);

        val products = productService.findAllProducts();
        Product product = productService.findProductById(productId).get();

        model.addAttribute("product", product);
        model.addAttribute("products", products);

        return "product";
    }

    @DeleteMapping("/product/{id}")
    public String deleteProduct(@PathVariable("id") String productId) {

        log.info("Received request to delete the product record with id={}", productId);
        productService.deleteProduct(productId);
        return "redirect:/product";
    }
}
