package com.simge.backend.controller;

import com.simge.backend.model.SubCategory;
import com.simge.backend.service.SubCategoryService;
import com.simge.backend.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subcategories")
@RequiredArgsConstructor
@Slf4j
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    // Get all subcategories
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SubCategory> getAllSubCategories() {
        log.info("Getting all subcategories");
        return subCategoryService.getAll();
    }

    // Get subcategory by id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SubCategory getSubCategoryById(@PathVariable Long id) {
        log.info("Getting subcategory with ID: {}", id);
        return subCategoryService.getById(id);
    }

    // Exception handler for ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
        log.error("Resource not found: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
