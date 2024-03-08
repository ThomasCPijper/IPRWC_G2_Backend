package com.s1141775.iprwc_g2_backend.controller;

import com.s1141775.iprwc_g2_backend.model.BackendOrder;
import com.s1141775.iprwc_g2_backend.model.FrontendOrder;
import com.s1141775.iprwc_g2_backend.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/addOrder")
    private ResponseEntity<String> addOrder(@RequestBody FrontendOrder frontendOrder){
        BackendOrder order = mapToBackend(frontendOrder);
        BackendOrder savedOrder = this.orderService.save(order);
        if(savedOrder != null){
            return ResponseEntity.ok().body("Order saved succesfully");
        }

        return ResponseEntity.badRequest().body("Error in adding Order");
    }

    private BackendOrder mapToBackend(FrontendOrder frontendOrder){
        return new BackendOrder(frontendOrder.getName(), frontendOrder.getCustomer(), frontendOrder.getEmail(), frontendOrder.getAddress());
    }
}
