CREATE TABLE IF NOT EXISTS document_revision (
    doc_revision_id             VARCHAR(36) PRIMARY KEY,
    document_reference_id       VARCHAR(36),
    revision_data               text,
    comments                    jsonb,
    created_at                  TIMESTAMP WITH TIME ZONE DEFAULT (NOW() AT TIME ZONE 'UTC')
);

