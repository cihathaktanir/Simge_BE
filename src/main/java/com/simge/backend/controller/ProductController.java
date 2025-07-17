package com.simge.backend.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simge.backend.model.dto.product.ProductModelDTO;
import com.simge.backend.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ProductModelDTO> getProducts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int paginate,
            @RequestParam(defaultValue = "desc") String sortBy, // Sorting direction ("asc" or "desc")
            @RequestParam(defaultValue = "0") Integer warehouse,
            @RequestParam(required = false) String ids) {

        int springPage = page > 0 ? page - 1 : 0;
        int pageSize = paginate;

        // Determine sorting direction based on sortBy request parameter
        Sort.Direction direction;
        if ("low-high".equalsIgnoreCase(sortBy)) {
            direction = Sort.Direction.ASC;
        } else {
            direction = Sort.Direction.DESC;
        }

        // Set the actual sort field to "pr.price" as used in your JPQL query
        String sortField = "pr.price";

        // Create the Sort object with the determined direction and field
        Sort sort = Sort.by(direction, sortField);
        // Create the Pageable object, including the sorting information
        Pageable pageable = PageRequest.of(springPage, pageSize, sort);

        List<String> idStrings = null;
        if (ids != null && !ids.isEmpty()) {
            idStrings = Arrays.asList(ids.split(","));
        }

        ProductModelDTO response = productService.getProducts(idStrings, warehouse, pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") String id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }
}