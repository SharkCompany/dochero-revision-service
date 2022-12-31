package com.dochero.documentrevisionservice.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.apache.logging.log4j.util.Strings;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@Table(name = "document_revision")
public class DocumentRevision {
    @Id
    @Column(name = "doc_revision_id")
    private String id;

    @Column(name = "document_reference_id")
    private String documentId;

    @Column(name = "revision_data")
    private String revisionData;

    @Type(type = "jsonb")
    @Column(name = "comments", columnDefinition = "jsonb")
    private List<Comment> comments;

    @Column(name = "created_at")
    private Timestamp createdAt;


    @PrePersist
    private void initData() {
        this.id = UUID.randomUUID().toString();
        this.createdAt = Timestamp.from(Instant.now());
    }

}
