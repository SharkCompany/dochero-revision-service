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

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentRevisionSpecification{

    public static Specification<DocumentRevision> documentRevisionHasDocumentId(String documentId) {
        return new Specification<DocumentRevision>() {
            @Override
            public Predicate toPredicate(Root<DocumentRevision> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createdAt")));
                return criteriaBuilder.equal(root.get("documentId"), documentId);
            }
        };
    }
}
