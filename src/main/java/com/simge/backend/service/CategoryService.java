package com.simge.backend.service;

import com.simge.backend.model.dto.category.CategoryModelDTO;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface CategoryService {
    CategoryModelDTO getCategories(Map<String, String> params, Pageable pageable);
}
