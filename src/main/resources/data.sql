INSERT INTO exam.locations (name, location_type)
VALUES ('Austria', 'COUNTRY');
INSERT INTO exam.locations (name, location_type)
VALUES ('Vienna', 'CITY');

INSERT INTO exam.statistics
(population, time, location_id, created_at, updated_at)
VALUES
(1850000, '2015-01-01 00:00:00', 2, now(), now()),
(1865000, '2016-01-01 00:00:00', 2, now(), now()),
(1880000, '2017-01-01 00:00:00', 2, now(), now()),
(1890000, '2018-01-01 00:00:00', 2, now(), now()),
(1900000, '2019-01-01 00:00:00', 2, now(), now()),
(1910000, '2020-01-01 00:00:00', 2, now(), now()),
(1920000, '2021-01-01 00:00:00', 2, now(), now()),
(1930000, '2022-01-01 00:00:00', 2, now(), now()),
(1940000, '2023-01-01 00:00:00', 2, now(), now()),
(1950000, '2024-01-01 00:00:00', 2, now(), now());


INSERT INTO exam.statistics
(population, time, location_id, created_at, updated_at)
VALUES
(8000000, '2015-01-01 00:00:00', 1, now(), now()),
(8050000, '2016-01-01 00:00:00', 1, now(), now()),
(8100000, '2017-01-01 00:00:00', 1, now(), now()),
(8150000, '2018-01-01 00:00:00', 1, now(), now()),
(8200000, '2019-01-01 00:00:00', 1, now(), now()),
(8250000, '2020-01-01 00:00:00', 1, now(), now()),
(8300000, '2021-01-01 00:00:00', 1, now(), now()),
(8350000, '2022-01-01 00:00:00', 1, now(), now()),
(8400000, '2023-01-01 00:00:00', 1, now(), now()),
(8450000, '2024-01-01 00:00:00', 1, now(), now());

-- Surveys
INSERT INTO exam.surveys (id, title, description, start_date, end_date, status, created_at, updated_at)
VALUES
(1000, 'Arbeitszufriedenheit 2024','beschreibung', '2024-01-01', '2024-12-31', 'ACTIVE', now(),now()),
(2000, 'Digitalisierung in Österreich', 'beschreibung', '2024-03-01', '2024-09-30', 'DRAFT', now(), now());

-- Questions
INSERT INTO exam.questions (id, survey_id, text, type, req, position)
VALUES
(1000, 1000, 'Wie zufrieden sind Sie mit Ihrer Arbeit?', 'SCALE', false, 1),
(2000, 1000, 'Wie viele Stunden arbeiten Sie pro Woche?', 'NUMERIC', true, 2),
(3000, 2000, 'Nutzen Sie digitale Amtswege?', 'BOOLEAN', true, 3),
(4000, 2000, 'Welche digitalen Services nutzen Sie?', 'TEXT', false, 4);

