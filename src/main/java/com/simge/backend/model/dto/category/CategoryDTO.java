package com.simge.backend.model.dto.category;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {
    private Integer id;
    private String name;
    private String slug;
    private String description;
    private String type;
    private Integer parent_id;
    private AttachmentDTO category_image;
    private Integer category_image_id;
    private AttachmentDTO category_icon;
    private Integer category_icon_id;
    private Double commission_rate;
    private List<CategoryDTO> subcategories;
    private Integer products_count;
    private Integer category_meta_image_id;
    private AttachmentDTO category_meta_image;
    private String meta_title;
    private String meta_description;
    private Boolean status;
    private Integer created_by_id;
    private String created_at;
    private String updated_at;
    private String deleted_at;
}
