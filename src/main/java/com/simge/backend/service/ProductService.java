package com.simge.backend.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.simge.backend.model.dto.product.ProductModelDTO;

public interface ProductService {

    // getProducts'a karşılık gelir, ProductModelDTO (sayfalama + data) döner
    ProductModelDTO getProducts(List<String> ids, Integer warehouseNumber, Pageable pageable);

    ProductModelDTO getProductById(String id);

}
