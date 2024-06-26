
CREATE SEQUENCE IF NOT EXISTS filter_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS criteria_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS filters (
    id BIGINT PRIMARY KEY,
    name VARCHAR,
    selection VARCHAR,
    created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_date TIMESTAMP
);

CREATE TABLE IF NOT EXISTS criteria (
    id BIGINT PRIMARY KEY,
    filter_id BIGINT NOT NULL,
    criteria_type INT,
    operator VARCHAR,
    value VARCHAR,
    created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_date TIMESTAMP,
    FOREIGN KEY (filter_id) REFERENCES filters(id) ON DELETE CASCADE
);
