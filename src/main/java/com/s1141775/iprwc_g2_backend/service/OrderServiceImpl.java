package com.s1141775.iprwc_g2_backend.service;

import com.s1141775.iprwc_g2_backend.dao.OrderRepository;
import com.s1141775.iprwc_g2_backend.model.BackendOrder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public BackendOrder save(BackendOrder order) {
        return this.orderRepository.save(order);
    }

    @Override
    public List<BackendOrder> findAll() {
        return (List<BackendOrder>) this.orderRepository.findAll();
    }

    @Override
    public Optional<BackendOrder> findById(String id) {
        return this.orderRepository.findById(id);
    }

    @Override
    public void delete(BackendOrder order) {
        this.orderRepository.delete(order);
    }
}
