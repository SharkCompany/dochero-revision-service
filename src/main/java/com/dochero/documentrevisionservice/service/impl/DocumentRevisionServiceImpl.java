package com.dochero.documentrevisionservice.service.impl;

import com.dochero.documentrevisionservice.constants.AppMessage;
import com.dochero.documentrevisionservice.dto.request.UpdateRevisionRequest;
import com.dochero.documentrevisionservice.dto.response.DocumentRevisionResponse;
import com.dochero.documentrevisionservice.entity.DocumentRevision;
import com.dochero.documentrevisionservice.exception.DocumentRevisionException;
import com.dochero.documentrevisionservice.repository.DocumentRevisionRepository;
import com.dochero.documentrevisionservice.search.DocumentRevisionSpecification;
import com.dochero.documentrevisionservice.service.DocumentRevisionService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentRevisionServiceImpl implements DocumentRevisionService {
    private final DocumentRevisionRepository documentRevisionRepository;

    @Autowired
    public DocumentRevisionServiceImpl(DocumentRevisionRepository documentRevisionRepository) {
        this.documentRevisionRepository = documentRevisionRepository;
    }

    @Override
    @Transactional
    public DocumentRevision createBlankRevision(String documentId) {
        // assume that documentId is valid
        Boolean isAnyRevisionExisted = documentRevisionRepository.existsByDocumentId(documentId);
        if (isAnyRevisionExisted) {
            throw new DocumentRevisionException(AppMessage.DOCUMENT_REVISION_ALREADY_EXISTED);
        }
        DocumentRevision revision = DocumentRevision.builder()
                .documentId(documentId)
                .revisionData(Strings.EMPTY)
                .build();
        return documentRevisionRepository.save(revision);
    }

    @Override
    @Transactional
    public DocumentRevision createRevisionForExistedDocument(String documentId, UpdateRevisionRequest request) {
        //assume that documentId is valid
        DocumentRevision revision = DocumentRevision.builder()
                .documentId(documentId)
                .revisionData(request.getRevisionData())
                .comments(request.getComments())
                .build();

        return documentRevisionRepository.save(revision);
    }

    @Override
    public DocumentRevision getRevisionDetail(String documentId, String revisionId) {
        //assume that documentId is valid
        Optional<DocumentRevision> revision = documentRevisionRepository.findByIdAndDocumentId(revisionId, documentId);
        if (revision.isPresent()) {
            return revision.get();
        } else {
            throw new DocumentRevisionException(AppMessage.REVISION_NOT_FOUND);
        }
    }

    @Override
    //Used for get document detail
    public List<DocumentRevision> getAllRevisionsByDocumentId(String documentId) {
        // assume that documentId is valid
        Specification<DocumentRevision> specs = DocumentRevisionSpecification.hasDocumentId(documentId)
                .and(DocumentRevisionSpecification.orderByCreatedAt("DESC"));
        return documentRevisionRepository.findAll(specs);
    }

    @Override
    @Transactional
    public DocumentRevisionResponse revertToDocumentRevision(String documentId, String revisionId) {
        DocumentRevision documentRevision = documentRevisionRepository.findByIdAndDocumentId(revisionId, documentId)
                .orElseThrow(() -> new DocumentRevisionException(AppMessage.REVISION_NOT_FOUND));
        DocumentRevision newRevision = cloneDocumentRevision(documentRevision);
        return new DocumentRevisionResponse(newRevision, "Revert to revision successfully");
    }

    private DocumentRevision cloneDocumentRevision(DocumentRevision revision) {
        DocumentRevision newRevision = DocumentRevision.builder()
                .documentId(revision.getDocumentId())
                .revisionData(revision.getRevisionData())
                .comments(revision.getComments())
                .build();

        return documentRevisionRepository.save(newRevision);
    }
}
