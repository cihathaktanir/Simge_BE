package com.simge.backend.service.impl;

import com.simge.backend.model.SubCategory;
import com.simge.backend.repository.SubCategoryRepository;
import com.simge.backend.service.SubCategoryService;
import com.simge.backend.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubCategoryServiceImpl implements SubCategoryService {

    private final SubCategoryRepository repository;

    @Override
    public List<SubCategory> getAll() {
        log.info("Fetching all sub-categories");
        return repository.findAll();
    }

    @Override
    public SubCategory getById(Long id) {
        log.info("Fetching sub-category with ID: {}", id);
        return repository.findById(id).orElseThrow(() -> {
            log.warn("Sub-category not found with ID: {}", id);
            return new ResourceNotFoundException("Sub-category not found with ID: " + id);
        });
    }
}
