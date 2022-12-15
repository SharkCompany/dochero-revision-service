package com.dochero.documentrevisionservice.entity;

import lombok.*;
import org.apache.logging.log4j.util.Strings;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DocumentRevision {
    @Id
    @Column(name = "doc_revision_id")
    private String id;

    @Column(name = "document_reference_id")
    private String documentId;

    @Column(name = "revision_data")
    private String revisionData;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @OneToMany(mappedBy = "revisionReferenceId", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @PrePersist
    private void initData() {
        this.id = UUID.randomUUID().toString();
        this.createdAt = Timestamp.from(Instant.now());
    }

}
