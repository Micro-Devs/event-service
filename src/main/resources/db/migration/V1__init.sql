CREATE SCHEMA event;

CREATE SEQUENCE common_sequence
    START 1
    INCREMENT 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE event.event
(
    id                BIGINT PRIMARY KEY DEFAULT nextval('common_sequence'),
    organization_name VARCHAR(50)  NOT NULL,
    event_name        VARCHAR(30)  NOT NULL,
    location          VARCHAR(100) NOT NULL,
    status            VARCHAR(10)  NOT NULL,
    event_date        DATE,
    updated_at        DATE,
    created_at        DATE,
    CONSTRAINT unique_event UNIQUE (organization_name, event_name, event_date)
);

CREATE TABLE event.event_detail
(
    guid       VARCHAR(36) NOT NULL PRIMARY KEY,
    event_id   BIGINT      NOT NULL,
    user_id    BIGINT      NOT NULL,
    updated_at DATE,
    created_at DATE
);
