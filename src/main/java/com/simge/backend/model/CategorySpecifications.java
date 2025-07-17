package com.simge.backend.model;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CategorySpecifications {

    public static Specification<Category> filterByParams(Map<String, String> params) {
        return new Specification<Category>() {
            @Override
            public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                // Handling 'status' parameter
                if (params.containsKey("status")) {
                    Boolean status = Boolean.valueOf(params.get("status"));
                    predicates.add(criteriaBuilder.equal(root.get("iptal"), status));
                }

                // Handling 'ids' parameter (comma separated)
                if (params.containsKey("ids")) {
                    String ids = (String) params.get("ids");
                    String[] idArray = ids.split(",");
                    List<Long> idList = new ArrayList<>();
                    for (String id : idArray) {
                        idList.add(Long.parseLong(id));
                    }
                    predicates.add(root.get("recno").in(idList));
                }

                // Handle 'paginate' parameter for pagination logic
                // Pagination should be handled outside this class in a service class or
                // repository

                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        };
    }
}
