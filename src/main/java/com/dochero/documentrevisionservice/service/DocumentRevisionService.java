package com.dochero.documentrevisionservice.service;

import com.dochero.documentrevisionservice.dto.CommentDTO;
import com.dochero.documentrevisionservice.dto.request.UpdateRevisionRequest;
import com.dochero.documentrevisionservice.dto.response.DocumentRevisionResponse;
import com.dochero.documentrevisionservice.entity.DocumentRevision;

import java.util.List;

public interface DocumentRevisionService {

    DocumentRevision createBlankRevision(String documentId);
    DocumentRevision createRevisionForExistedDocument(String documentId, UpdateRevisionRequest updateRevisionRequest);
    DocumentRevision getRevisionDetail(String documentId, String revisionId);
    List<DocumentRevision> getAllRevisionsByDocumentId(String documentId);
    DocumentRevision revertToDocumentRevision(String documentId, String revisionId);
}
