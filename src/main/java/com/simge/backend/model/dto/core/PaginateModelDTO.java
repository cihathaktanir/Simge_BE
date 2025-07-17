package com.simge.backend.model.dto.core;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PaginateModelDTO {
    private Integer currentPage;
    private String firstPageUrl;
    private Integer from;
    private Integer lastPage;
    private String lastPageUrl;
    private List<LinkDTO> links;
    private String nextPageUrl;
    private String path;
    private Integer perPage;
    private String prevPageUrl;
    private Integer to;
    private Integer total;
}

