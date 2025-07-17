package com.simge.backend.service.impl;

import com.simge.backend.model.Category;
import com.simge.backend.model.CategorySpecifications;
import com.simge.backend.model.SubCategory;
import com.simge.backend.model.dto.category.CategoryDTO;
import com.simge.backend.model.dto.category.CategoryModelDTO;
import com.simge.backend.model.dto.core.LinkDTO;
import com.simge.backend.repository.CategoryRepository;
import com.simge.backend.service.CategoryService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryModelDTO getCategories(Map<String, String> params, Pageable pageable) {
        try {
            Page<Category> page = categoryRepository.findAll(CategorySpecifications.filterByParams(params), pageable);

            List<CategoryDTO> categoryDTOList = page.getContent().stream()
                    .map(this::convertToCategoryDTO)
                    .collect(Collectors.toList());

            CategoryModelDTO result = new CategoryModelDTO();
            result.setData(categoryDTOList);
            result.setTotal((int) page.getTotalElements());
            result.setCurrentPage(page.getNumber() + 1);
            result.setPerPage(page.getSize());
            result.setLastPage(page.getTotalPages());
            result.setFrom(page.getNumber() * page.getSize() + 1);
            result.setTo(result.getFrom() + page.getNumberOfElements() - 1);
            result.setPath("/api/getcategories");

            List<LinkDTO> links = generateLinks(result.getCurrentPage(), result.getLastPage());
            result.setLinks(links);

            return result;
        } catch (Exception e) {
            log.error("Error fetching categories: ", e);
            return new CategoryModelDTO(); // veya hata DTO'su
        }
    }

    // Helper method to convert Category entity to CategoryDTO
    private CategoryDTO convertToCategoryDTO(Category category) {
        // Create a new CategoryDTO
        CategoryDTO categoryDTO = new CategoryDTO();

        // Map category entity fields to DTO fields
        categoryDTO.setId(category.getRecno() != null ? category.getRecno().intValue() : null);
        categoryDTO.setName(category.getIsim());
        categoryDTO.setSlug(toKebabCase(category.getIsim()));
        categoryDTO.setDescription(category.getSpecial1()); // Assuming 'special1' is used for description
        categoryDTO.setType(category.getSpecial2()); // Assuming 'special2' is used for type
        categoryDTO.setCommission_rate(null);
        categoryDTO.setParent_id(null);
        categoryDTO.setCategory_icon_id(category.getRecno() != null ? category.getRecno().intValue() : null);

        categoryDTO.setSubcategories(
                category.getSubCategories().stream()
                        .map(this::convertToSubCategoryDTO)
                        .collect(Collectors.toList()));

        return categoryDTO; // Return the converted CategoryDTO
    }

    private CategoryDTO convertToSubCategoryDTO(SubCategory subCategory) {
        CategoryDTO categoryDTO = new CategoryDTO();

        // Assuming SubCategory has similar fields to Category, adjust accordingly
        categoryDTO.setId(subCategory.getRecno() != null ? subCategory.getRecno().intValue() : null);
        categoryDTO.setName(subCategory.getIsim()); // Adjust field name as needed

        // You can add more fields if necessary
        return categoryDTO;
    }

    private List<LinkDTO> generateLinks(int currentPage, int lastPage) {
        return java.util.stream.IntStream.rangeClosed(1, lastPage)
                .mapToObj(i -> {
                    LinkDTO link = new LinkDTO();
                    link.setLabel(String.valueOf(i));
                    link.setUrl("/api/getcategories?page=" + i);
                    link.setActive(i == currentPage ? 1 : 0);
                    return link;
                }).collect(Collectors.toList());
    }

    private String toKebabCase(String input) {
        if (input == null || input.isEmpty())
            return "";
        return input.toLowerCase()
                .replaceAll("[^a-z0-9\\s]", "") // remove special chars
                .replaceAll("\\s+", "-"); // spaces to hyphens
    }

}
