CREATE SEQUENCE IF NOT EXISTS filters_seq;
CREATE SEQUENCE IF NOT EXISTS criteria_seq;

CREATE TABLE IF NOT EXISTS filters (
    id BIGINT PRIMARY KEY DEFAULT NEXTVAL('filters_seq'),
    name VARCHAR,
    selection INT,
    created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_date TIMESTAMP
);

CREATE TABLE IF NOT EXISTS criteria (
    id BIGINT PRIMARY KEY DEFAULT NEXTVAL('criteria_seq'),
    filter_id BIGINT NOT NULL,
    criteria_type INT,
    operator VARCHAR,
    value VARCHAR,
    created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_date TIMESTAMP,
    FOREIGN KEY (filter_id) REFERENCES filters(id) ON DELETE CASCADE
);
