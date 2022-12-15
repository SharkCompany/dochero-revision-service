CREATE TABLE IF NOT EXISTS document_revision (
    doc_revision_id             VARCHAR(36) PRIMARY KEY,
    document_reference_id       VARCHAR(36),
    revision_data               JSON,
    created_at                  TIMESTAMP WITH TIME ZONE DEFAULT (NOW() AT TIME ZONE 'UTC')
);

CREATE TABLE IF NOT EXISTS comment (
    comment_id                  VARCHAR(36) PRIMARY KEY,
    content                     text,
    created_by                  VARCHAR(36),
    created_at                  TIMESTAMP WITH TIME ZONE DEFAULT (NOW() AT TIME ZONE 'UTC'),
    revision_reference_id       VARCHAR(36)
);

-- create foreign key constraints
ALTER TABLE comment ADD CONSTRAINT fk_comment_revision_reference_id FOREIGN KEY (revision_reference_id) REFERENCES document_revision (doc_revision_id);

