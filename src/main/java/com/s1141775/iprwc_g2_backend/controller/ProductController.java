package com.s1141775.iprwc_g2_backend.controller;

import com.s1141775.iprwc_g2_backend.model.BackendProduct;
import com.s1141775.iprwc_g2_backend.model.FrontendProduct;
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
    private ResponseEntity<String> addProduct(@RequestBody FrontendProduct frontendProduct){
        try{
            System.out.println("image: " + frontendProduct.getImage());
            BackendProduct backendProduct = this.mapToBackend(frontendProduct);
            this.productService.save(backendProduct);
            return ResponseEntity.ok().body("Product saved succesfully");
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Error while saving product");
        }
    }

    @GetMapping("/products")
    private ResponseEntity<List<FrontendProduct>> getProducts(){
        List<BackendProduct> backendProductList = this.productService.findAll();
        List<FrontendProduct> frontendProductList = new ArrayList<>();
        for(BackendProduct product : backendProductList){
            frontendProductList.add(mapToFrontend(product));
        }
        return ResponseEntity.ok().body(frontendProductList);
    }

    private BackendProduct mapToBackend(FrontendProduct frontendProduct){
        return new BackendProduct(frontendProduct.getName(), frontendProduct.getImage(), frontendProduct.getCost());
    }

    private FrontendProduct mapToFrontend(BackendProduct backendProduct){
        return new FrontendProduct(backendProduct.getName(), backendProduct.getImage(), backendProduct.getCost());
    }
}
