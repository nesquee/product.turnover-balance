CREATE TABLE users (
                       id         SERIAL PRIMARY KEY,
                       username   VARCHAR(64)   NOT NULL UNIQUE,
                       password   VARCHAR(2048) NOT NULL,
                       role       VARCHAR(32)   NOT NULL,
                       first_name VARCHAR(64)   NOT NULL,
                       last_name  VARCHAR(64)   NOT NULL,
                       enabled    BOOLEAN       NOT NULL DEFAULT FALSE,
                       created_at TIMESTAMP,
                       updated_at TIMESTAMP
);

CREATE TABLE files
(
    id         SERIAL PRIMARY KEY,
    filename   VARCHAR(255),
    duplicates VARCHAR(255)
);