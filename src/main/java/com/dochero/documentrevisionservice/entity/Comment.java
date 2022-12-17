package com.dochero.documentrevisionservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "comment")
public class Comment {
    @Id
    @Column(name = "comment_id")
    private String id;

    @Column(name = "revision_reference_id")
    private String revisionReferenceId;

    @Column(name = "content")
    private String content;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @ManyToOne
    @JoinColumn(name = "revision_reference_id", referencedColumnName = "doc_revision_id", insertable = false, updatable = false)
    @JsonIgnore
    private DocumentRevision documentRevision;

    @PrePersist
    private void initData() {
        this.id = UUID.randomUUID().toString();
        this.createdAt = Timestamp.from(Instant.now());
    }
}
