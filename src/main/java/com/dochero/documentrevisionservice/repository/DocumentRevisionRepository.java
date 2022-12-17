package com.dochero.documentrevisionservice.repository;

import com.dochero.documentrevisionservice.entity.DocumentRevision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRevisionRepository extends JpaRepository<DocumentRevision, String>, JpaSpecificationExecutor<DocumentRevision> {

    Optional<DocumentRevision> findByIdAndDocumentId(String revisionId, String documentId);

    Boolean existsByDocumentId(String documentId);
}
