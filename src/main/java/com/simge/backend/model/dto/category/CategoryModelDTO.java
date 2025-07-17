package com.simge.backend.model.dto.category;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import com.simge.backend.model.dto.core.PaginateModelDTO;

@Getter
@Setter
public class CategoryModelDTO extends PaginateModelDTO {
    private List<CategoryDTO> data;
}
