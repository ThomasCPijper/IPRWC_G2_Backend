package com.s1141775.iprwc_g2_backend.controller;

import com.s1141775.iprwc_g2_backend.model.Product;
import com.s1141775.iprwc_g2_backend.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/addProduct")
    private ResponseEntity<String> addProduct(@RequestBody Product product){
        try{
            this.productService.save(product);
            return ResponseEntity.ok().body("Product saved succesfully");
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Error while saving product");
        }
    }

    @GetMapping("/products")
    private ResponseEntity<List<Product>> getProducts(){
        List<Product> products = this.productService.findAll();
        return ResponseEntity.ok().body(products);
    }

}
