package com.dochero.documentrevisionservice.controller;

import com.dochero.documentrevisionservice.dto.request.UpdateRevisionRequest;
import com.dochero.documentrevisionservice.dto.response.DocumentRevisionResponse;
import com.dochero.documentrevisionservice.service.DocumentRevisionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/document-revision")
public class DocumentRevisionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentRevisionController.class);
    private final DocumentRevisionService documentRevisionService;

    @Autowired
    public DocumentRevisionController(DocumentRevisionService documentRevisionService) {
        this.documentRevisionService = documentRevisionService;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        DocumentRevisionResponse body = new DocumentRevisionResponse(Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.badRequest().body(body);
    }

    @GetMapping("/{documentId}")
    public ResponseEntity<?> getAllDocumentRevision(@PathVariable("documentId") String documentId) {
        try {
            return ResponseEntity.ok().body(documentRevisionService.getAllRevisionsByDocumentId(documentId));
        } catch (Exception e) {
            LOGGER.error("Failed to get document revision. " + e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{documentId}/initial-document")
    public ResponseEntity<?> createEmptyRevision(@PathVariable("documentId") String documentId) {
        try {
            return ResponseEntity.ok().body(documentRevisionService.createBlankRevision(documentId));
        } catch (Exception e) {
            LOGGER.error("Failed to create empty revision. " + e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{documentId}/save-document")
    public ResponseEntity<?> createRevisionForExistedDocument(@PathVariable("documentId") String documentId, @RequestBody UpdateRevisionRequest revision) {
        try {
            return ResponseEntity.ok().body(documentRevisionService.createRevisionForExistedDocument(documentId, revision));
        } catch (Exception e) {
            LOGGER.error("Failed to save document revision. " + e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{documentId}/revert/{documentRevisionId}")
    public ResponseEntity<?> revertToDocumentRevision(@PathVariable("documentId") String documentId,
                                                     @PathVariable("documentRevisionId") String documentRevisionId) {
        try {
            return ResponseEntity.ok().body(documentRevisionService.revertToDocumentRevision(documentId, documentRevisionId));
        } catch (Exception e) {
            LOGGER.error("Failed to revert to document revision. " + e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
