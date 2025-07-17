package com.simge.backend.model.dto.category;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttachmentDTO {
    private Integer id;
    private String fileName;
    private String fileType;
    private String url;
}
