package com.s1141775.iprwc_g2_backend.controller;

import com.s1141775.iprwc_g2_backend.model.BackendOrder;
import com.s1141775.iprwc_g2_backend.model.FrontendOrder;
import com.s1141775.iprwc_g2_backend.model.Product;
import com.s1141775.iprwc_g2_backend.model.ProductInShoppingCart;
import com.s1141775.iprwc_g2_backend.service.OrderService;
import com.s1141775.iprwc_g2_backend.service.ProductInShoppingCartService;
import com.s1141775.iprwc_g2_backend.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class OrderController {
    private final OrderService orderService;
    private final ProductInShoppingCartController productInShoppingCartController;

    public OrderController(OrderService orderService, ProductInShoppingCartController productInShoppingCartController) {
        this.orderService = orderService;
        this.productInShoppingCartController = productInShoppingCartController;
    }

    @GetMapping("/orders")
    private ResponseEntity<List<FrontendOrder>> getAll(){
        ArrayList<FrontendOrder> orders = new ArrayList<>();
        List<BackendOrder> backendOrders = this.orderService.findAll();
        for(BackendOrder backendOrder : backendOrders){
            FrontendOrder frontendOrder = mapToFrontend(backendOrder);
            orders.add(frontendOrder);
            System.out.println(Arrays.toString(frontendOrder.getProducts()));
        }
        return ResponseEntity.ok().body(orders);
    }

    @PostMapping("/addOrder")
    private ResponseEntity<String> addOrder(@RequestBody FrontendOrder order){

        BackendOrder backendOrder = mapToBackend(order);
        BackendOrder savedOrder = orderService.save(backendOrder);

        this.productInShoppingCartController.saveProducts(backendOrder, order);

        if(savedOrder != null){
            return ResponseEntity.ok().body("Order saved successfully");
        }

        return ResponseEntity.badRequest().body("Error in saving Order");
    }


    private BackendOrder mapToBackend(FrontendOrder frontendOrder){
        return new BackendOrder(frontendOrder.getName(), frontendOrder.getCustomer(), frontendOrder.getEmail(), frontendOrder.getAddress());
    }

    private FrontendOrder mapToFrontend(BackendOrder backendOrder){
        String id = backendOrder.getId();
        String name = backendOrder.getName();
        String customer = backendOrder.getCustomer();
        String email = backendOrder.getEmail();
        String address = backendOrder.getAddress();
        ProductInShoppingCart[] products;

        products = findProductsByOrder(id);

        return new FrontendOrder(id, name, customer, products, email, address);
    }

    private ProductInShoppingCart[] findProductsByOrder(String id){
        return this.productInShoppingCartController.getProductsByOrderId(id);
    }
}
