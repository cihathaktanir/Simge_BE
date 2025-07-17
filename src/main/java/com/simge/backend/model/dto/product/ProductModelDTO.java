package com.simge.backend.model.dto.product;

import java.util.List;

import com.simge.backend.model.dto.core.PaginateModelDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductModelDTO extends PaginateModelDTO {
    private List<ProductDTO> data;
}
