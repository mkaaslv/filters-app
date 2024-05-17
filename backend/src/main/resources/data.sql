-- Sample data for FILTER and CRITERIA table

INSERT INTO filters (id, name, selection, created_date, modified_date)
VALUES (1, 'Filter 1', 'a', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO filters (id, name, selection, created_date, modified_date)
VALUES (2, 'Filter 2', 'b', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

SELECT setval('filter_id_seq', (SELECT MAX(id) FROM filters));

INSERT INTO criteria (id, filter_id, criteria_type, operator, value, created_date)
VALUES (1, 1, 1, 'MORE_THAN', '20', CURRENT_TIMESTAMP);

INSERT INTO criteria (id, filter_id, criteria_type, operator, value, created_date)
VALUES (2, 1, 2, 'STARTS_WITH', 'test', CURRENT_TIMESTAMP);

INSERT INTO criteria (id, filter_id, criteria_type, operator, value, created_date)
VALUES (3, 2, 3, 'BEFORE', CURRENT_DATE, CURRENT_TIMESTAMP);

SELECT setval('criteria_id_seq', (SELECT MAX(id) FROM criteria));