package com.dochero.documentrevisionservice.search;

import com.dochero.documentrevisionservice.entity.DocumentRevision;
import com.dochero.documentrevisionservice.utils.QueryParamUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class DocumentRevisionSpecification{

    public static Specification<DocumentRevision> hasDocumentId(String documentId) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createdAt")));
            return criteriaBuilder.equal(root.get("documentId"), documentId);
        };
    }

    public static Specification<DocumentRevision> orderByCreatedAt(String order) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (StringUtils.equalsIgnoreCase(order, "asc")) {
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get("createdAt")));
            } else {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createdAt")));
            }
            return criteriaBuilder.conjunction();
        };
    }
}
