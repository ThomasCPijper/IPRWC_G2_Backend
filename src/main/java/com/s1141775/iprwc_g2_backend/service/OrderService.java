package com.s1141775.iprwc_g2_backend.service;


import com.s1141775.iprwc_g2_backend.model.BackendOrder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OrderService {
    BackendOrder save(BackendOrder order);

    List<BackendOrder> findAll();

    Optional<BackendOrder> findById(String id);

    void delete(BackendOrder order);
}
