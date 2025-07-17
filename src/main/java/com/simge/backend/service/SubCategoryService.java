package com.simge.backend.service;

import com.simge.backend.model.SubCategory;

import java.util.List;

public interface SubCategoryService {

    List<SubCategory> getAll(); 

    SubCategory getById(Long id);
}
