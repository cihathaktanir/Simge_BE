package com.simge.backend.controller;

import com.simge.backend.exception.ResourceNotFoundException;
import com.simge.backend.model.dto.category.CategoryModelDTO;
import com.simge.backend.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CategoryModelDTO> getCategories(@RequestParam Map<String, String> params) {
        log.info("Received Params: " + params);

        int page = Integer.parseInt(params.getOrDefault("current_page", "0"));
        int size = Integer.parseInt(params.getOrDefault("per_page", "10"));

        // Handle 'paginate' parameter if it's provided
        if (params.containsKey("paginate")) {
            size = Integer.parseInt(params.get("paginate"));
        }

        // Pageable nesnesini oluştur
        Pageable pageable = PageRequest.of(page, size);

        try {
            // Servis katmanına filtreleme ve sayfalama parametrelerini gönder
            CategoryModelDTO result = categoryService.getCategories(params, pageable);

            // Başarılı dönüş
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("Error occurred while fetching categories: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Exception handler
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
        log.error("Resource not found: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
