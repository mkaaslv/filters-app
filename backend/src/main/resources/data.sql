-- Sample data for FILTER and CRITERIA table

INSERT INTO filters (id, name, selection, created_date)
VALUES (nextval('filters_seq'), 'Filter 1', 1, CURRENT_TIMESTAMP);

-- INSERT INTO filters (id, name, selection, created_date)
-- VALUES (nextval('filters_seq'), 'Filter 2', 2, CURRENT_TIMESTAMP);

INSERT INTO criteria (id, filter_id, criteria_type, operator, value, created_date)
VALUES (nextval('criteria_seq'), 1, 1, 'MORE_THAN', '20', CURRENT_TIMESTAMP);

INSERT INTO criteria (id, filter_id, criteria_type, operator, value, created_date)
VALUES (nextval('criteria_seq'), 1, 2, 'STARTS_WITH', 'test', CURRENT_TIMESTAMP);

-- INSERT INTO criteria (id, filter_id, criteria_type, operator, value, created_date)
-- VALUES (nextval('criteria_seq'), 2, 3, 'BEFORE', CURRENT_DATE, CURRENT_TIMESTAMP);
