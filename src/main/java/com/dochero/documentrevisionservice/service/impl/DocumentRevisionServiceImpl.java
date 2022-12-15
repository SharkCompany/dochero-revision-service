package com.dochero.documentrevisionservice.service.impl;

import com.dochero.documentrevisionservice.constants.AppMessage;
import com.dochero.documentrevisionservice.dto.CommentDTO;
import com.dochero.documentrevisionservice.dto.request.UpdateRevisionRequest;
import com.dochero.documentrevisionservice.dto.response.DocumentRevisionResponse;
import com.dochero.documentrevisionservice.entity.Comment;
import com.dochero.documentrevisionservice.entity.DocumentRevision;
import com.dochero.documentrevisionservice.exception.DocumentRevisionException;
import com.dochero.documentrevisionservice.repository.CommentRepository;
import com.dochero.documentrevisionservice.repository.DocumentRevisionRepository;
import com.dochero.documentrevisionservice.service.DocumentRevisionService;
import com.dochero.documentrevisionservice.utils.CommentMapperUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocumentRevisionServiceImpl implements DocumentRevisionService {
    private final DocumentRevisionRepository documentRevisionRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public DocumentRevisionServiceImpl(DocumentRevisionRepository documentRevisionRepository, CommentRepository commentRepository) {
        this.documentRevisionRepository = documentRevisionRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    @Transactional
    public DocumentRevision createBlankRevision(String documentId) {
        // assume that documentId is valid
        return DocumentRevision.builder()
                .documentId(documentId)
                .revisionData(Strings.EMPTY)
                .build();
    }

    @Override
    @Transactional
    public DocumentRevision createRevisionForExistedDocument(String documentId, UpdateRevisionRequest request) {
        //assume that documentId is valid

        DocumentRevision revision = DocumentRevision.builder()
                .documentId(documentId)
                .revisionData(request.getRevisionData())
                .build();
        DocumentRevision saveRevision = documentRevisionRepository.save(revision);

        List<Comment> comments = CommentMapperUtils.mapListCommentDTOsToListComments(request.getComments());
        commentRepository.saveAll(comments);

        return saveRevision;
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

        return documentRevisionRepository.findByDocumentIdOrderByCreatedAtDesc(documentId);
    }

    @Override
    @Transactional
    public DocumentRevision revertToDocumentRevision(String documentId, String revisionId) {
        DocumentRevision documentRevision = documentRevisionRepository.findByIdAndDocumentId(revisionId, documentId)
                .orElseThrow(() -> new DocumentRevisionException(AppMessage.REVISION_NOT_FOUND));
        return cloneDocumentRevision(documentRevision);
    }

    private DocumentRevision cloneDocumentRevision(DocumentRevision revision) {
        DocumentRevision newRevision = DocumentRevision.builder()
                .documentId(revision.getDocumentId())
                .revisionData(revision.getRevisionData())
                .build();
        DocumentRevision saveRevision = documentRevisionRepository.save(newRevision);

        List<Comment> comments = revision.getComments().stream().map(comment -> Comment.builder()
                .revisionReferenceId(saveRevision.getId())
                .content(comment.getContent())
                .build()).collect(Collectors.toList());
        commentRepository.saveAll(comments);

        return saveRevision;
    }
}
